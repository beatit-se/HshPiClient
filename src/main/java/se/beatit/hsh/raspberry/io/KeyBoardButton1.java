package se.beatit.hsh.raspberry.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.ui.Graphics;

import java.util.List;

/**
 * Created by stefan on 1/4/19.
 */
@Component
public class KeyBoardButton1 extends AbstractKeyboardButton {

    private Logger logger;
    private List<ButtonListener> listeners;

    @Autowired
    public KeyBoardButton1(List<ButtonListener> listeners, Graphics graphics) {
        super('1');
        this.listeners = listeners;

        graphics.addKeyListener(this);

        logger = LoggerFactory.getLogger(KeyBoardButton1.class);
        logger.info("Keyboard Button 1 created!");
    }

    @Override
    public void buttonEvent(ButtonListener.Event event) {
        logger.trace("Keyboard Button 1 event {}!", event);
        listeners.forEach(l -> l.buttonEvent(ButtonListener.Button.BUTTON1, event));
    }
}
