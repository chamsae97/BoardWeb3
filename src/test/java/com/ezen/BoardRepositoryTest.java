package com.ezen;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ezen.board.domain.Board;
import com.ezen.board.domain.Member;
import com.ezen.board.domain.Role;
import com.ezen.board.persistence.BoardRepository;
import com.ezen.board.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Ignore
	public void testInsert() {
		Member member1 = new Member();
		
		member1.setId("member1");
		member1.setPassword("1111");
		member1.setName("사용자");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		
		for (int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setMember(member1);
			board.setTitle("사용자 Test 게시글" + i);
			board.setContent("Test Content 내용" + i);

		}
		memberRepo.save(member1); // 멤버1의 정보 및 테스트 게시글3건 저장
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("2222");
		member2.setName("관리자");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		
		for (int i=1; i<=3; i++) {
			Board board = new Board();
			
			board.setMember(member2);
			board.setTitle("사이트 공지사항 Test 제목 " + i);
			board.setContent("공지사항 Test Content 내용 " + i);

		}
		memberRepo.save(member2);
	}
	
	@Test // 게시글 상세 조회
	@Ignore
	public void testGetBoard() {
		// x번 게시글 조회하여 내용 출력 
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}
	
	@Test // 목록 검색 테스트
	public void testGetBoardList() {
		// member가 등록한 게시글 목록 조회 (member1:사용자 2:관리자)
		Member member = memberRepo.findById("member1").get();

		for (Board board : member.getBoardList()) {
			System.out.println(board);
		}
		
	}
}
