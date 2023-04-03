package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들 disable
                .and()
                .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대상 지정 옵션
                .anyRequest().authenticated() //설정된 값들 이외 나머지 URL 나타냄
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃 성공 시 / 주소로 이동
                .and()
                .oauth2Login()//OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할  UserService 인터페이스의 구현체 등록
    }
}
