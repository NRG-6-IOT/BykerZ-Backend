package nrg.inc.bykerz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BykerzApplication {

    public static void main(String[] args) {
        SpringApplication.run(BykerzApplication.class, args);
    }

}
