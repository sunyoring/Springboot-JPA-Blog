package com.sunyoring.blog.test;

public class TestApp {

	public static void main(String[] args) {
		People  홍길동 = new People();
		
//		홍길동.hungryState : 바로 접근 불가  ->  변수를 private으로 만들고 직접 접근을 못하게 하기 때문에 getter , setter함수를 통해 접근을 할 수 있게 한다. 
		홍길동.eat();
	}
}
