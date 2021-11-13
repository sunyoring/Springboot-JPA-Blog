package com.sunyoring.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller :  사용자가 요청 -> 그에 대한 응답(HTML 파일)
//@RestController : 사용자가 요청 -> 그에 대한 응답 (Data)
@RestController   
public class HttpControllerTest {

	// http://localhost:8080/http/get  (SELECT)
	@GetMapping("/http/get")
	public String getTest(Member m) {  //@RequestParam int id,@RequestParam String userName
		
		//?id=1&userName=ssar&password=1234&email=ssar@nate.com
		//물음표 뒤에 있는 값들을 스프링이 멤버 m의 값에 넣어준다.  
		
		return "get 요청 : " + m.getId()+ ", " + m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/post  (INSERT)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {  //데이터를 주소에 붙여 보내지 않고 body에 붙여 보낸다.
		
	/*대표적인 방법들
	 * 
	 * 1.  <form> 태그 사용  
	 * 2. json  data :  application/json
	 * 3. text : text/plain
	 * */
				
		return "post 요청 : " + m.getId()+ ", " + m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/put  (UPDATE)
	@PutMapping("/http/put")
	public String putTest(@RequestBody  Member m) {
		
		
		/*
		 * {
			    "id" :  1,
			    "password" : 5555
			}
		 * 
		 * */
//		put 요청 : 1, null, 5555, null
		return "put 요청 : " + m.getId()+ ", " + m.getUserName() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/delete (DELETE)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		
		return "delete 요청";
	}
	
	
	// Console 
	//Tomcat started on port(s): 8080 (http) with context path '' : 톰캣실행 포트번호 8080 context path 없음!
}
