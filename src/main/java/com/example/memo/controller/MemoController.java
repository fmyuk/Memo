package com.example.memo.controller;

import com.example.memo.controller.response.Note;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * メモアプリケーションのコントローラー
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    /**
     * トップページ
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Note> noteList = memoService.findNote();
        model.addAttribute("noteList", noteList);
        return "memo/index";
    }

    /**
     * 新規メモ作成ページ
     */
    @GetMapping("/register")
    public String registerGet() {
        return "memo/register";
    }

    /**
     * 新規メモ作成登録
     */
    @PostMapping("/register")
    public String registerPost(@RequestParam("title") String title, @RequestParam("body") String body) {
        memoService.registerNote(title, body);
        return "redirect:/memo/";
    }

    /**
     * メモ編集ページ
     */
    @GetMapping("/edit")
    public String updateGet(@RequestParam("id") int id, Model model) {
        Note note = memoService.findFirstNote(id);
        model.addAttribute("note", note);
        return "memo/edit";
    }

    /**
     * メモ編集更新
     */
    @PostMapping("/edit")
    public String updatePost(@RequestParam("id") int id,
                             @RequestParam("title") String title,
                             @RequestParam("body") String body) {
        memoService.updateNote(body, title, id);
        return "redirect:/memo/";
    }

    /**
     * メモ削除
     */
    @GetMapping("/delete")
    public String deleteGet(@RequestParam("id") int id) {
        memoService.deleteNote(id);
        return "redirect:/memo/";
    }

    /**
     * 1件表示
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") int id, Model model) {
        Note note = memoService.findFirstNote(id);
        model.addAttribute("note", note);
        return "memo/view";
    }
}
