package com.example.memo.service;

import com.example.memo.controller.response.Note;
import com.example.memo.infrastracture.MemoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoDao memoDao;

    /**
     * 取得したメモをNoteクラスに整形して返却
     *
     * @return List<Note>
     */
    public List<Note> findNote() {
        List<Map<String, Object>> dataList = memoDao.selectList();
        return dataList.stream()
                .map(Note::of)
                .collect(Collectors.toList());
    }

    /**
     * 取得したIDと一致するメモをNoteクラスに整形して返却
     *
     * @return List<Note>
     */
    public List<Note> findNoteById(int id) {
        List<Map<String, Object>> dataList = memoDao.selectById(id);
        return dataList.stream()
                .map(Note::of)
                .collect(Collectors.toList());
    }

    /**
     * IDと一致するメモの最初の一件のみを取得して返却
     */
    public Note findFirstNote(int id) {
        List<Note> noteList = findNoteById(id);
        return noteList.get(0);
    }

    /**
     * 新規メモ作成
     *
     * @param title タイトル
     * @param body  ボディ
     * @return int
     */
    public int registerNote(String title, String body) {
        Date date = new Date();
        return memoDao.insert(title, body, date);
    }

    /**
     * メモ編集
     *
     * @param title タイトル
     * @param body  ボディ
     * @param id    ID
     * @return int
     */
    public int updateNote(String title, String body, int id) {
        Date date = new Date();
        return memoDao.update(title, body, date, id);
    }

    /**
     * メモ削除
     *
     * @param id ID
     * @return int
     */
    public int deleteNote(int id) {
        return memoDao.delete(id);
    }
}
