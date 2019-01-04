package se.beatit.hsh.raspberry.io;

import com.pi4j.io.gpio.RaspiPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.ui.Graphics;

import java.util.List;

/**
 * Created by stefan on 1/1/19.
 */
@Component
@ConditionalOnProperty(value = "running-on-pi", havingValue = "true", matchIfMissing = true)
public class HWButton3 extends AbstractHWButton {

    private Logger logger;
    private List<ButtonListener> listeners;

    @Autowired
    public HWButton3( List<ButtonListener> listeners) {
        super(RaspiPin.GPIO_04);
        this.listeners = listeners;

        logger = LoggerFactory.getLogger(HWButton3.class);
        logger.info("HW Button 3 created!");
    }

    @Override
    public void buttonEvent(ButtonListener.Event event) {
        logger.info("Button 3 event {}!", event);
        listeners.forEach(l -> l.buttonEvent(ButtonListener.Button.BUTTON3, event));
    }
}
