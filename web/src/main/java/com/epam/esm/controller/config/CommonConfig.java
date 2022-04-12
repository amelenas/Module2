package com.epam.esm.controller.config;

import com.epam.esm.logging.LoggingConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.epam.esm")
@Configuration
@EnableWebMvc
@Import({DBConfigMain.class, LoggingConfig.class})
public class CommonConfig {

}
