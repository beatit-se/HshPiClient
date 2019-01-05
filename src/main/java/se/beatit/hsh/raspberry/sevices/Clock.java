package se.beatit.hsh.raspberry.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.io.ButtonListener;
import se.beatit.hsh.raspberry.ui.Graphics;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by stefan on 1/2/19.
 */
@Component
@Order(1)
public class Clock implements DisplayService {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private boolean focused = true;

    private Graphics graphics;
    private Alarm alarm;

    @Autowired
    public Clock(se.beatit.hsh.raspberry.ui.Graphics graphics, Alarm alarm) {
        this.graphics = graphics;
        this.alarm = alarm;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(focused) {
                    showTime();
                }
            }
        };

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    private void showTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Stockholm"));
        graphics.setText(now.format(dateTimeFormatter));

        switch (alarm.getState()) {
            case ALARMING:
                graphics.setSubText("Wake up!!!");
                break;
            case ON:
                graphics.setSubText("Alarm " + alarm.getAlarmTime());
                break;
            case SNOOZED:
                graphics.setSubText("Snoozzzed ...");
                break;
            default:
                graphics.setSubText("");
                break;
        }
    }

    @Override
    public void setFocused(boolean focused) {
        this.focused = focused;

        if(focused) {
            showTime();
        } else {
            if(alarm.getState() == Alarm.State.ALARMING) {
                alarm.turnOff();
            }
        }
    }

    @Override
    public void handleButtonEvent(ButtonListener.Button button, ButtonListener.Event event) {
        if(alarm.getState() == Alarm.State.ALARMING) {
            switch (button) {
                case BUTTON1:
                case BUTTON2:
                    alarm.snooze();
                    break;
                case BUTTON3:
                    alarm.turnOff();
                    break;
            }
        }
    }
}
