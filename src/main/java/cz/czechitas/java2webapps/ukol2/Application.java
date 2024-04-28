package cz.czechitas.java2webapps.ukol2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static cz.czechitas.java2webapps.ukol2.controller.MainController.readAllLines;

/**
 * Hlavní třída, která spouští celou aplikaci pomocí Spring Boot.
 */
@SpringBootApplication
public class Application {
        private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        logger.info("Aplikace běží na adrese: http://localhost:{}", applicationContext.getEnvironment().getProperty("local.server.port"));
    }
}
