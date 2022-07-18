package com.ezen.board.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ezen.board.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	// 게시글 목록 조회하는 추상 메소드 
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable paging);
}
