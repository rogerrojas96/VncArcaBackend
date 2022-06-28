package com.vncarca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:pruebas.properties")
@Profile("pruebas")
public class PropertiesSourcePruebas {
    
}
