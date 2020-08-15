package com.example.memo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.Map;

/**
 * メモのレスポンスを保持するDTO
 */
@Getter
@AllArgsConstructor
public class Note {
    /**
     * ID
     */
    public int id;

    /**
     * タイトル
     */
    public String title;

    /**
     * ボディ
     */
    public String body;

    /**
     * 日付
     */
    public Date date;

    /**
     * Noteクラスのof
     *
     * @param data メモ
     * @return Note
     */
    public static Note of(Map<String, Object> data) {
        return new Note(
                (int) data.get("id"),
                (String) data.get("title"),
                (String) data.get("body"),
                (Date) data.get("date")
        );
    }
}
