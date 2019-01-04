package se.beatit.hsh.raspberry.sevices;

import se.beatit.hsh.raspberry.io.ButtonListener;

/**
 * Created by stefan on 1/3/19.
 */
public interface DisplayService {

    void setFocused(boolean focused);

    void handleButtonEvent(ButtonListener.Button button, ButtonListener.Event event);
}
