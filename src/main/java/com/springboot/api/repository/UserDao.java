package com.springboot.api.repository;

import com.springboot.api.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
        }
    };

    public User add(User user) {
        jdbcTemplate.update("insert into users(id,name,password) values (?,?,?)",
                user.getId(), user.getName(), user.getPassword());
        return user;
    }

    public List<User> getAll() {
        String sql = "select * from users order by id";
        return jdbcTemplate.query(sql, rowMapper);
    }


    public User findById(String id) {
        String sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int deleteById(String id) {
        return jdbcTemplate.update("delete from users where id = ?", id);
    }


    public int deleteAll() {
        return jdbcTemplate.update("delete from users");
    }

}
