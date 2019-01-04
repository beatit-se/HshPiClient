


package se.beatit.hsh.raspberry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 *
 * @author Stefan Nilsson
 */
@SpringBootApplication
public class HshPiClient {

    public static void main(String args[]) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(HshPiClient.class);
        builder.headless(false).run(args);
    }

}
