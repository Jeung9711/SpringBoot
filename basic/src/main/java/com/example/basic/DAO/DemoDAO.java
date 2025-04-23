package com.example.basic.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.basic.controller.model.Demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

@Repository
public class DemoDAO {
    @Autowired JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> select1() {
        String sql = "select seq, user from demo";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public List<Demo> select2() {
        String sql = "select seq, user from demo";
        List<Demo> list = jdbcTemplate.query(sql, new RowMapper() {

            @Override
            public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long seq = rs.getLong("seq");
                String user = rs.getString("user");
                Demo demo = new Demo();
                demo.setSeq(seq);
                demo.setUser(user);
                return demo;
            }});
        return list;
    }

    public int insert(Demo demo) {
        String sql = "insert into demo (seq, user) values (?,?)";
        int result = jdbcTemplate.update(sql, demo.getSeq(), demo.getUser());

        return result;
    }

    public int delete(Demo demo) {
        String sql = "delete from demo where seq = ?";
        int result = jdbcTemplate.update(sql, demo.getSeq());

        return result;
    }
}
