package com.sunyoring.blog.test;

public class People {

	private int hugryState = 50; //배고픔 : 최대치가 100
	
	
	public static void main(String[] args) {
		People p = new People();
		p.hugryState = 100;  //배고픔이 없는 상태 변수에 직접 접근해서 값을 수정한다.  객체 지향이 아님
		p.eat(); //이와 같이 접근해야 한다. 
	}
	
	
	public void eat() {  //eat 함수를 실행시킴으로써 배고픔을 증가시켜야 한다. 
		hugryState += 10;
	}


	public int getHugryState() {
		return hugryState;
	}
	
	
 
}
