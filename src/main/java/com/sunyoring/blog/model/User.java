package com.sunyoring.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/* 실행했을 때 console에 실행 된 sql문, 실제로 db확인해보면 테이블이 추가되어 있음!
 * 
 * Hibernate: 
    
    drop table if exists User
Hibernate: 
    
    create table User (
       id integer not null auto_increment,
        createDate datetime(6),
        email varchar(50) not null,
        password varchar(100) not null,
        role varchar(255) default 'user',
        username varchar(30) not null,
        primary key (id)
    ) engine=InnoDB
 * 
 * */

//ORM -> JAVA(다른언어 포함) OBJECT를 테이블로 매핑하는 기술이다. 변수명 바꾸고 다시 실행하면 바뀐 컬럼명으로 테이블이 다시 생성된다. 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌더패턴
@Entity  // User클래스가 MySQL에 테이블이 생성된다. (제일 아래에 위치하는 것이 좋음)
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //프로젝트에서 연결된 DB의 넘버링 전략을 따른다. (ex : 오라클이라면 시퀀스, mySQL이라면 auto_increment )
	private int id;  //시퀀스(오라클), auto_increment (mySql)
	
	@Column(nullable = false, length = 30)   // NOT NULL, 길이 제한 
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //암호화를 위해 넉넉하게 
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")  //기본값 user인데 " " 안에  ' '  로 넣어야함
 	private String role;  // admin , user , manager 등 등의 권한을 부여할 수 있도록 하는 것.   // 나중에는 String이 아니라 도메인을 정의할 수 있는  Enum을 쓰는 것이 좋다. (admin, user, manager)라는 값만 들어갈 수 있도록  
	
	@CreationTimestamp  // 시간이 자동으로 입력
	private Timestamp createDate;   //가입한 시간 SYSDATE 를 쿼리로 적을 수 있지만 어노테이션으로도 가능
	
}
