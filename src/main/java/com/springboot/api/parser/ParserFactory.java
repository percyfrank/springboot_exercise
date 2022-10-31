package com.springboot.api.parser;

import com.springboot.api.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ParserFactory {

    @Bean
    public ReadLineContext<Hospital> hospitalReadLineContext() throws IOException {
        return new ReadLineContext<Hospital>(new HospitalParser());
    }
 }
