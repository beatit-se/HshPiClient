package se.beatit.hsh.raspberry.io;

import com.pi4j.io.gpio.RaspiPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by stefan on 1/1/19.
 */
@Component
@ConditionalOnProperty(value = "running-on-pi", havingValue = "true", matchIfMissing = true)
public class HWButton2 extends AbstractHWButton {

    private Logger logger;
    private List<ButtonListener> listeners;

    @Autowired
    public HWButton2(List<ButtonListener> listeners) {
        super(RaspiPin.GPIO_03);
        this.listeners = listeners;

        logger = LoggerFactory.getLogger(HWButton2.class);
        logger.info("HW Button 2 created!");
    }

    @Override
    public void buttonEvent(ButtonListener.Event event) {
        logger.info("Button 2 event {}!", event);
        listeners.forEach(l -> l.buttonEvent(ButtonListener.Button.BUTTON2, event));
    }
}
