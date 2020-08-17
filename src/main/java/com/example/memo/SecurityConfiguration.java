package com.example.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, JdbcTemplate jdbcTemplate) throws Exception {
        // アカウントの設定（パスコード）
        auth.inMemoryAuthentication().withUser("admin").password("adminpassword").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user1").password("user1password").roles("USER");
        // アカウントの設定（データベース）、利用するパスワードエンコーダの設定
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource()).passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/suppl01/secret/**").authenticated()
                .antMatchers("/suppl01/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        // ログイン
        http.formLogin().loginPage("/suppl01/login").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/suppl01/loginProcess").defaultSuccessUrl("/suppl01/secret/").failureUrl("/suppl01/login?error");

        // ログアウト
        http.logout().logoutUrl("/suppl01/logout").logoutSuccessUrl("/suppl01/");
    }
}
