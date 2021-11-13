package com.sunyoring.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더패턴
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량데이터
	private String content; // 섬머노트 라이브러리  <html> 태그가 같이 db에 올라가기 때문에 용량이 커짐

	@ColumnDefault("0")
	private int count; //조회수
	
	@CreationTimestamp   //데이터가 입력될 때 현재 날짜가 같이 들어감
	private Timestamp createDate;
	
	@ManyToOne // Many = Board, One = User : 한 명의 유저가 여러 개의 게시글을 쓸 수 있다.  연관관계!
	@JoinColumn(name="userId")  //userId라는 필드값으로 테이블이 생성된다.
	private User user; //작성자  -> User의 id와 연결된다. (JPA)ORM방식에서는 외래키로 찾지 않는다.
	// 연관관계에 맞춰 User객체가 만들어진다. 
}
