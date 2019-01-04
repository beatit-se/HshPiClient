package se.beatit.hsh.raspberry.sevices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import se.beatit.hsh.raspberry.io.ButtonListener;
import se.beatit.hsh.raspberry.sound.SoundPlayer;
import se.beatit.hsh.raspberry.ui.Graphics;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stefan on 1/1/19.
 */
@Component
@Order(2)
public class Alarm implements DisplayService {

    private final static Logger logger = LoggerFactory.getLogger(Alarm.class);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final static long SNOOZE_TIME_MIN = 5;

    private boolean focused = false;

    private Graphics graphics;
    private SoundPlayer soundPlayer;

    private Timer alarmTimer;
    private ZonedDateTime alarmTime;


    enum State { ON, OFF, ALARMING, SNOOZED }
    private State state = State.OFF;

    @Autowired
    public Alarm(Graphics graphics, SoundPlayer soundPlayer) {
        this.graphics = graphics;
        this.soundPlayer = soundPlayer;

        alarmTime = ZonedDateTime.now(ZoneId.of("Europe/Stockholm"));
        alarmTime = alarmTime.withSecond(0);
    }

    private void showAlarm() {
        graphics.setText(alarmTime.format(dateTimeFormatter));
        graphics.setSubText("Alarm: " + (state == State.ON ? "on" : "off"));
    }

    @Override
    public void setFocused(boolean focused) {
        if(this.focused && !focused) {
            setAlarm(alarmTime);
        }

        this.focused = focused;
        if(this.focused) {
            showAlarm();
        }
    }

    private void setAlarm(ZonedDateTime alarm) {
        if(alarmTimer != null) {
            alarmTimer.cancel();
            alarmTimer.purge();
        }

        if(state == State.ON) {
            alarmTimer = new Timer();

            TimerTask t  = new TimerTask() {
                @Override
                public void run() {
                    logger.info("A L A R M !!!!!!");
                    state = State.ALARMING;
                    soundPlayer.playSound("src/main/resource/wardrobe_bongobongo.wav");
                }
            };

            if(alarm.isBefore(ZonedDateTime.now())) {
                alarm = alarm.plusDays(1);
            }

            logger.info("Alarm set to " + alarm);
            alarmTimer.schedule(t, Date.from(alarm.toInstant()));
        }
    }

    @Override
    public void handleButtonEvent(ButtonListener.Button button, ButtonListener.Event event) {
        if(event == ButtonListener.Event.PUSHED) {
            switch (button) {
                case BUTTON1:
                    alarmTime = alarmTime.plusHours(1);
                    break;
                case BUTTON2:
                    alarmTime = alarmTime.plusMinutes(1);
                    break;
                case BUTTON3:
                    if(state != State.ON) {
                        state = State.ON;
                    } else {
                        state = State.OFF;
                    }
                    break;
            }
            showAlarm();
        }
    }

    public String getAlarmTime() {
        return alarmTime.format(dateTimeFormatter);
    }

    public State getState() {
        return state;
    }

    public void snooze() {
        soundPlayer.stop();
        state = State.SNOOZED;
        setAlarm(ZonedDateTime.now().plusMinutes(SNOOZE_TIME_MIN));
    }

    public void turnOff() {
        soundPlayer.stop();
        state = State.OFF;
    }
}
