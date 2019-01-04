package se.beatit.hsh.raspberry.io;

import com.pi4j.io.gpio.RaspiPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.sound.SoundPlayer;
import se.beatit.hsh.raspberry.ui.Graphics;

import java.util.List;

/**
 * Created by stefan on 1/1/19.
 */
@Component
@ConditionalOnProperty(value = "running-on-pi", havingValue = "true", matchIfMissing = true)
public class HWButton4 extends AbstractHWButton {

    private List<ButtonListener> listeners;
    private Logger logger;

    @Autowired
    public HWButton4(List<ButtonListener> listeners) {
        super(RaspiPin.GPIO_02);
        this.listeners = listeners;

        logger = LoggerFactory.getLogger(HWButton4.class);
        logger.info("HW Button 4 created!");
    }

    @Override
    public void buttonEvent(ButtonListener.Event event) {
        logger.info("Button 4 event {}!", event);
        listeners.forEach(l -> l.buttonEvent(ButtonListener.Button.BUTTON4, event));
    }
}
