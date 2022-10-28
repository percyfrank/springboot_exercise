package com.springboot.api.dao;

import com.springboot.api.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class Userdao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public Userdao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        }
    };

    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id,name,password) values (?,?,?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User findById(String id) {
        String sql = "select * from users where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }

}
