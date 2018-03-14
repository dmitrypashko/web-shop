package com.gmail.dmitrypashko.dmitry.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.gmail.dmitrypashko.dmitry"})
@PropertySource(value = {"classpath:hibernate.properties"})
@Import(value = {HibernateConfig.class, WebConfig.class, SecurityConfig.class})
public class AppConfig {

}
