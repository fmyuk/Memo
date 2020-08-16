package com.example.memo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * メモのレスポンスを保持するDTO
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    /**
     * ID
     */
    private int id;

    /**
     * タイトル
     */
    private String title;

    /**
     * ボディ
     */
    private String body;

    /**
     * 日付
     */
    private Date date;

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
