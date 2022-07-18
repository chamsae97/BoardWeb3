package com.ezen.service;

import com.ezen.board.domain.Member;

public interface MemberService {

	/*
	 * 사용자 조회 메소드
	 * id, password를 매개변수로 받는다
	 */
	Member getMember(Member member);

}