package com.ezen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.board.domain.Member;
import com.ezen.board.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	/*
	 * 사용자 조회 메소드
	 * id, password를 매개변수로 받는다
	 */
	@Override
	public Member getMember(Member member) { 
		// (1) Repo에서 id로 회원 검색
		Optional<Member> user = memberRepo.findById(member.getId());
		
		// (2) 회원이 존재하는지 확인
		// Optional의 isEmpty, isPresent()
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
}
