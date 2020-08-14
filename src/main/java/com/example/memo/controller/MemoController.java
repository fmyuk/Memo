package com.example.memo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * メモアプリケーションのコントローラー
 */
@Controller
@RequestMapping("/memo")
public class MemoController {

    private final JdbcTemplate jdbcTemplate;

    /**
     * コンストラクタ
     */
    @Autowired
    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        // noteテーブルが存在していない場合に作成する
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS note (\n" +
                        " id INT AUTO_INCREMENT PRIMARY KEY, \n" +
                        " title TEXT,\n" +
                        " body TEXT,\n" +
                        " date TIMESTAMP\n" +
                        ")"
        );
    }


}
