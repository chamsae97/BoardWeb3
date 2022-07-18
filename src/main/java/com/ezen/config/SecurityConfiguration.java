package com.ezen.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserDetailsService securityService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeRequests()
				.antMatchers("/", "/system/**")
				.permitAll(); // 모든 사용자 접근가능
		
		security.authorizeRequests()
				.antMatchers("/board/**")
				.hasAnyRole("ADMIN", "MEMBER"); // 로그인 성공한 사용자 접근 가능
		
		security.authorizeRequests()
				.antMatchers("/admin/**")
				.hasRole("ADMIN"); // ADMIN 권한이 있어야 접근 가능
		
		security.csrf().disable();
		
		// 로그인 성공 페이지: "/board/getBoardList.html"
		security.formLogin()
				.loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList");
		
		// 인증되지 않은 사용자에게 제공하는 URL (Whitelabel 예외처리 html 필요)
		// 접근 권한 제한 페이지: "/system/accessDenied.html"
		security.exceptionHandling()
				.accessDeniedPage("/system/accessDenied");
		
		// 로그아웃 처리 -> 세션 정보 삭제 -> 로그인 페이지로 이동
		// 로그아웃 성공 페이지 "/"
		security.logout()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/");
		
		// 테이블의 사용자 정보를 UserDetails 타입으로 변환
		// SecurityUserDetailsService의 변수가 들어감
		security.userDetailsService(securityService);
		
		return security.build();
	}
	/*
	 * 암호화 수행을 위한 객체 리턴
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}





