package se.beatit.hsh.raspberry.io;

/**
 * Created by stefan on 1/2/19.
 */
public interface ButtonListener {

    enum Button {
        BUTTON1, BUTTON2, BUTTON3, BUTTON4
    }

    enum Event {
        PUSHED, RELEASED
    }

    void buttonEvent(Button button, Event event);
}
