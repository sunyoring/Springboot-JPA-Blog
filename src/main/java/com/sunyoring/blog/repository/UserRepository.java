package com.sunyoring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunyoring.blog.model.User;

// Spring 레거시에서 DAO
//JpaRepository : 기본적인 CRUD 를 제공하고 있기 때문에 상속받은 메소드들을 오버라이드한 구현체에서 로직을 작성하면 된다.
//bean등록이 되어야 스프링에서 관리할 수 있는데 이렇게 하면 자동으로 bean으로 등록되기 때문에 @Repository 어노테이션을 생략할 수 있다. 
public interface UserRepository extends JpaRepository<User, Integer> {  //해당 JpaRepository 는 User 를 관리하고 이 테이블의 기본키는 Integer형이다.

	
}
