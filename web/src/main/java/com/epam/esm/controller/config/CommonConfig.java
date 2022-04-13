package com.epam.esm.controller.config;

import com.epam.esm.logging.LoggingConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({ConfigProd.class, ConfigDev.class, LoggingConfig.class})
public class CommonConfig {
}
