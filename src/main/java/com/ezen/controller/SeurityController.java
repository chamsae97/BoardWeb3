package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeurityController {

	// 로그인 화면 요청
	@GetMapping("/system/login")
	public void login() {
	
	}
	
	// 접근권한 없음 페이지 표시
	@GetMapping("/system/accessDenied")
	public void accessDenied() {
		
	}
	
	// 로그아웃
	@GetMapping("/system/logout")
	public void logout() {
	
	}
	
	// 관리자 페이지
	@GetMapping("/admin/adminPage")
	public void forAdmin() {
	}
	

}
