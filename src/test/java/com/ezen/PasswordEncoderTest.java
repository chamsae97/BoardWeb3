package com.ezen;

import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.board.domain.Member;
import com.ezen.board.domain.Role;
import com.ezen.board.persistence.MemberRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	@Ignore
	public void testMemberInsert() {
		Member member = new Member();
		
		member.setId("member2");
		member.setPassword(encoder.encode("2222")); // 3333가 암호화되어 저장된다
		member.setName("관리자");
		member.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);
		
		memberRepo.save(member);
	}
}
