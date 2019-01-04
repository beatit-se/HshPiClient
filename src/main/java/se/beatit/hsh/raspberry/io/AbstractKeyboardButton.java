package se.beatit.hsh.raspberry.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by stefan on 1/4/19.
 */
public abstract class AbstractKeyboardButton extends AbstractButton implements KeyListener {
    private char keyboardKey;

    private static Logger logger = LoggerFactory.getLogger(AbstractKeyboardButton.class);

    public AbstractKeyboardButton(char keyboardKey) {
        this.keyboardKey = keyboardKey;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == keyboardKey) {
            buttonEvent(ButtonListener.Event.PUSHED);
        }
    }
}
