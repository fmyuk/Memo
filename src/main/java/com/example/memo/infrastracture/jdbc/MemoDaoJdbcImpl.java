package com.example.memo.infrastracture.jdbc;

import com.example.memo.infrastracture.MemoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemoDaoJdbcImpl implements MemoDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 新着順にメモを取得
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectList() {
        // 新着順でnoteテーブルから取得
        return jdbcTemplate.queryForList("SELECT * FROM note ORDER BY date DESC");
    }

    /**
     * IDと一致するメモ取得
     *
     * @return List
     */
    @Override
    public List<Map<String, Object>> selectById(int id) {
        return jdbcTemplate.queryForList("SELECT * FROM note WHERE id = ?", id);
    }

    /**
     * 新規メモ作成
     *
     * @param title タイトル
     * @param body  ボディ
     * @param date  日付
     * @return int
     */
    @Override
    public int insert(String title, String body, Date date) {
        return jdbcTemplate.update("INSERT INTO note(title, body, date) VALUES(?, ?, ?)", title, body, date);
    }

    /**
     * メモ編集
     *
     * @param title タイトル
     * @param body  ボディ
     * @param date  日付
     * @param id    ID
     * @return int
     */
    @Override
    public int update(String title, String body, Date date, int id) {
        return jdbcTemplate.update("UPDATE note set title = ?, body = ?, date = ? WHERE id = ?", title, body, date, id);
    }

    /**
     * メモ削除
     *
     * @param id ID
     * @return int
     */
    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM note WHERE id = ?", id);
    }
}
