package se.beatit.hsh.raspberry.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.io.ButtonListener;
import se.beatit.hsh.raspberry.sevices.DisplayService;

import java.util.Iterator;
import java.util.List;

/**
 * Created by stefan on 1/2/19.
 */
@Component
public class Controller implements ButtonListener {

    private List<DisplayService> displayServices;

    private DisplayService current;

    @Autowired
    public Controller(List<DisplayService> displayServices) {
        this.displayServices = displayServices;
        this.current = displayServices.get(0);
    }

    @Override
    public void buttonEvent(Button button, Event event) {
        switch (button) {
            case BUTTON4:
                if(event == Event.PUSHED) {
                    current.setFocused(false);
                    changeCurrentDisplayService();
                    current.setFocused(true);
                }
                break;
            default:
                current.handleButtonEvent(button, event);
        }
    }



    private void changeCurrentDisplayService() {
        Iterator<DisplayService> di = displayServices.iterator();
        while (di.hasNext()) {
            if(di.next() == current) {
                if(di.hasNext()) {
                    current = di.next();
                    return;
                }
            }
        }
        current = displayServices.get(0);
    }

}
