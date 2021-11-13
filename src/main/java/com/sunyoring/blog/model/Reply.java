package com.sunyoring.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따른다.
	private int id;  //시퀀스, auto_increment
	
	@Column(nullable = false, length=200)
	private String content;
	
	@ManyToOne  //여러 개의 댓글은 하나의 게시물에 존재한다.(연관관계)
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne  //여러 개의 댓글은 하나의 유저가 쓸 수 있다.(연관관계)
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
