package se.beatit.hsh.raspberry.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.io.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Created by stefan on 1/3/19.
 */
public class HWButtonMock implements KeyListener {
    private Controller controller;

    public HWButtonMock(Controller controller) {
        this.controller = controller;

    }
    /**
     * Impl KeyListener for running on dev machine
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        final char key = e.getKeyChar();

        switch (key) {
            case '1':
                controller.buttonEvent(ButtonListener.Button.BUTTON1, ButtonListener.Event.PUSHED);
                break;
            case '2':
                controller.buttonEvent(ButtonListener.Button.BUTTON2, ButtonListener.Event.PUSHED);
                break;
            case '3':
                controller.buttonEvent(ButtonListener.Button.BUTTON3, ButtonListener.Event.PUSHED);
                break;
            case '4':
                controller.buttonEvent(ButtonListener.Button.BUTTON4, ButtonListener.Event.PUSHED);
                break;

        }
    }
}
