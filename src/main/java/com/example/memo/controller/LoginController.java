package com.example.memo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/log")
public class LoginController {

    private final JdbcTemplate jdbcTemplate;

    /**
     * トップページ
     */
    @GetMapping("/")
    public String index() {
        return "log/index";
    }

    /**
     * ログインフォームを表示する
     */
    @GetMapping("/login")
    public String login() {
        return "log/login";
    }

    /**
     * ログイン後に遷移するページ
     */
    @GetMapping("/secret/")
    public String secretIndex() {
        return "log/secret/index";
    }

    /**
     * ログイン中のユーザの情報を表示するページ
     */
    @GetMapping("/secret/info")
    public String secretInfo(@AuthenticationPrincipal UserDetails userDetails, ModelMap modelMap) {
        modelMap.addAttribute("username", userDetails.getUsername());
        return "log/secret/info";
    }

    /**
     * 管理者用トップページ
     */
    @GetMapping("/admin/")
    public String adminIndex() {
        return "log/admin/index";
    }

    /**
     * ユーザの追加（GET、フォームを表示）
     */
    @GetMapping("/admin/add_user")
    public String adminAddUserGet() {
        return "log/admin/add_user";
    }

    /**
     * ユーザの追加（POST、データベースを操作）
     */
    @PostMapping("/admin/add_user")
    public String adminAddUserPost(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("role") String role) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        jdbcTemplate.update("INSERT INTO users ( username, password , enabled ) VALUES (?, ?, ?)", username, passwordEncoder.encode(password), true);
        jdbcTemplate.update("INSERT INTO authorities ( username, authority) VALUES (?, ?)", username, role);
        return "redirect:/log/admin/";
    }
}
