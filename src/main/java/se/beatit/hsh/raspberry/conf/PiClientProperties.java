package se.beatit.hsh.raspberry.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by stefan on 1/5/19.
 */
@Configuration
@ConfigurationProperties
public class PiClientProperties {
    private String soundFile;

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }
}
