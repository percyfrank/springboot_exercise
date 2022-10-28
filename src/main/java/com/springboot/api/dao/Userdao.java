package com.springboot.api.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Userdao {

    private final JdbcTemplate jdbcTemplate;

    public Userdao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }

}
