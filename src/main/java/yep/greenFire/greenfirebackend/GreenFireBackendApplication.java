package yep.greenFire.greenfirebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GreenFireBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(GreenFireBackendApplication.class, args);
    }

}
