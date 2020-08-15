package com.example.memo.infrastracture;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * メモアプリケーションのDAOインターフェース
 */
public interface MemoDao {

    /**
     * 新着順にメモを取得
     *
     * @return List
     */
    public List<Map<String, Object>> selectList();

    /**
     * IDと一致するメモ取得
     *
     * @return List
     */
    public List<Map<String, Object>> selectById(int id);

    /**
     * 新規メモ作成
     *
     * @param title タイトル
     * @param body  ボディ
     * @param date  日付
     * @return int
     */
    public int insert(String title, String body, Date date);

    /**
     * メモ編集
     *
     * @param title タイトル
     * @param body  ボディ
     * @param date  日付
     * @param id    ID
     * @return int
     */
    public int update(String title, String body, Date date, int id);

    /**
     * メモ削除
     *
     * @param id ID
     * @return int
     */
    public int delete(int id);
}
