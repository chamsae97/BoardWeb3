package com.ezen.board.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="member")
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	private String content;
	
	@Temporal(value=TemporalType.TIMESTAMP) //  TIMESTAMP : 시분초
	@Column(updatable=false) // 업데이트 문 실행 시 새로운 날짜로 업데이트 되는 것을 막음
	private Date createDate = new Date();
	
	@Column(updatable=false)
	private Long cnt = 0L;
	
	// 게시글 = 작성자 조인
	@ManyToOne // Board는 N에 해당하니 ManyToOne, nullable은 내부 조인으로 변경하는 것
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) // 관계 엔티티의 기본키 컬럼 지정, 값이 있는 경우만 조회
	private Member member; // board 엔티티(주인)에서 member를 포함한다.  
	
	public void setMember(Member member) {
		this.member = member; // 멤버가 추가되면
		member.getBoardList().add(this); // 보드 리스트에 있는 멤버도 추가한다.
	}
}