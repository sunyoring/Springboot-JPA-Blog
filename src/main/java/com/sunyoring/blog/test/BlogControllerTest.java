package com.sunyoring.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.sunyoring.blog 패키지 이하를 스캔하여 특정 어노테이션이 붙어있는 클래스파일을 new() 해서 스프링 컨테이너에서 관리해준다. 
@RestController
public class BlogControllerTest {

	
	/*
	 * 스프링 -> IOC (제어의 역전)
	 * 
	 *  개발자가 직접 new로 생성하지 않고, 스프링이 레퍼런스 변수를 관리해준다. 
	 *  
	 *  계속 new로 생성하면 heap공간에 계속 자리가 마련되기 때문에 메모리도 낭비일 뿐더러 번거롭기 때문에 스프링이 컴포넌트 스캔을 통해 
	 *  한번 만들어둔 객체를 로드하여 필요한 곳에 넣어주는 것이다. -> 싱글톤 패턴
	 *  
	 *  처음 프로젝트를 생성할 때 com.sunyoring.blog라고 만들어둔 패키지 아래로 스캔을 하기 때문에 com.sunyoring.test 라는 식의 패키지를 임의로 
	 *  만들어서 사용하면 안되고 com.sunyoring.blog.test라는 패키지로 만들어 주는 것이다. 
	 * 
	 * 
	 * */
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1> hello spring boot</h1>";
	}
	
}
