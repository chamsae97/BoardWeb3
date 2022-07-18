package com.ezen.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="boardList")
@Entity
public class Member {
	@Id
	@Column(name="MEMBER_ID") // 1:N 관계 설정을 위한 name 지정
	private String id;
	
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
	// 양방향 관계 설정을 위해 OneToMany 관계를 추가한다.
	// mappedBy는 외래키가 있는 곳의 필드명. EAGER=열정적인, LAZY=게으른
	// cascade=CascadeType.ALL 영속성 전이, 회원탈퇴 시 작성한 게시글도 함께 삭제
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
}
