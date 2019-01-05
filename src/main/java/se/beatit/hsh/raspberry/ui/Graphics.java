package se.beatit.hsh.raspberry.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ThaiBuddhistChronology;

/**
 * Created by stefan on 12/27/18.
 */
@Component
public class Graphics  extends JFrame implements Runnable {

    private JLabel mainLabel;
    private JLabel subLabel;

    private final static Logger logger = LoggerFactory.getLogger(Graphics.class);

    @Autowired
    public Graphics() {
        super("Home Sweet Home");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setForeground(Color.LIGHT_GRAY);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Stockholm"));

        mainLabel = new JLabel("Starting ...", JLabel.CENTER);
        mainLabel.setBackground(Color.BLACK);
        mainLabel.setForeground(Color.LIGHT_GRAY);
        mainLabel.setFont(new Font(mainLabel.getFont().getName(), Font.BOLD, 128));

        subLabel = new JLabel("sub info ...", JLabel.CENTER);
        subLabel.setBackground(Color.BLACK);
        subLabel.setForeground(Color.LIGHT_GRAY);
        subLabel.setFont(new Font(mainLabel.getFont().getName(), Font.BOLD, 64));

        add(mainLabel, BorderLayout.CENTER);
        add(subLabel, BorderLayout.AFTER_LAST_LINE);

        setSize(320,240);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setLocationRelativeTo(null);

        hidePointer();
        keepPiAlive();
    }

    private void keepPiAlive() {
        new Thread(this).start();
    }

    private void hidePointer() {
        setCursor( getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );
    }

    public void setText(String text) {
        mainLabel.setText(text);
    }

    public void setSubText(String text) {
        subLabel.setText(text);
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(60000L);
                keepAlive();
            } catch(Exception e) {}
        }
    }


    private void keepAlive() {
        try {
            Robot robot = new Robot();
            robot.keyPress(65);
        } catch (AWTException e) {
            logger.warn("Failed to keep alive {}", e.getMessage());
        }
    }
}
