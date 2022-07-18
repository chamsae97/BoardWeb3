package com.ezen.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezen.board.domain.Member;
import com.ezen.board.domain.SecurityUser;
import com.ezen.board.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	
	@Autowired
	private MemberRepository memberRepo;
	
	/*
	 * MemberRepository를 통해 회원정보를 조회하여
	 * 스프링에서 인증관리를 하기 위해 UserDetails 객체에 저장하여 리턴하다.
	 */
	
	@Override // SecurityUser에서 이 생성자를 호출해서 객체를 넘겨받는다.
	public UserDetails loadUserByUsername(String username) 
						throws UsernameNotFoundException {
		// MemberRepository로 회원 정보를 조회한 뒤
		Optional<Member> optionalUser = memberRepo.findById(username);
		
		if(optionalUser.isPresent()) {
			// UserDetails 타입의 객체로 변환해서 리턴한다.
			Member member = optionalUser.get(); // 멤버 타입의 객체로 반는다.
			return new SecurityUser(member);	// 생성자 호출해서 보내줌
		} else {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		}
		
	}
}
