package se.beatit.hsh.raspberry.io;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by stefan on 1/1/19.
 */
public abstract class AbstractHWButton extends AbstractButton implements GpioPinListenerDigital {

    private PinState previousPinState = PinState.LOW;

    private static Logger logger = LoggerFactory.getLogger(AbstractHWButton.class);

    public AbstractHWButton(Pin pin) {

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalInput button = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_UP);

        // create and register gpio pin listener
        button.addListener(this);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        logger.info("Event {}", event.getState());

        if(event.getState() != previousPinState) {
            previousPinState = event.getState();
            if (event.getState() != PinState.HIGH) {
                buttonEvent(ButtonListener.Event.PUSHED);
            } else {
                buttonEvent(ButtonListener.Event.RELEASED);
            }
        }
    }
}
