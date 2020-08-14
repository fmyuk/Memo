package com.example.memo.service.jdbc;

import com.example.memo.service.MemoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemoDaoJdbcImpl implements MemoDao {

    private final JdbcTemplate jdbcTemplate;

}
