package se.beatit.hsh.raspberry.sound;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.*;

/**
 * Created by stefan on 1/1/19.
 */
@Component
public class SoundPlayer {

    private Clip myClip;

    public void playSound(String fileName) {

            try {
                File file = new File(fileName);
                if (file.exists()) {
                    myClip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);

                    myClip.setFramePosition(0);  // Must always rewind!
                    myClip.loop(3);
                    myClip.start();
                }
                else {
                    throw new RuntimeException("Sound: file not found: " + fileName);
                }
            }
            catch (MalformedURLException e) {
                throw new RuntimeException("Sound: Malformed URL: " + e);
            }
            catch (UnsupportedAudioFileException e) {
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            }
            catch (IOException e) {
                throw new RuntimeException("Sound: Input/Output Error: " + e);
            }
            catch (LineUnavailableException e) {
                throw new RuntimeException("Sound: Line Unavailable: " + e);
            }
        }

        public void stop(){
            myClip.stop();
        }

}
