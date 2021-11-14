package com.sunyoring.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunyoring.blog.model.RoleType;
import com.sunyoring.blog.model.User;
import com.sunyoring.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired  //UserRepository의 타입으로 스프링 컨테이너에 등록 된 것이 있으면 가져와서 넣어준다.  -> 의존성 주입 ( DI ) **스프링의 대표 특징
	private UserRepository userRepository;
	
	//{id} 주소로 파라미터를 전달받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4  를 찾을 때 DB에 존재하지 않으면 user = null 이기 때문에 return 할 때 null을 return 하면 문제가 되기때문에 
		//반환타입이 findById의 자료형은 Optional 이다. 
		// 방법 1)      Optional의 메소드중 null이 절대 없을 경우에는 get() 을 사용하는데  무작정 get()으로 뽑아오는 것은 위험하다. 
		// 방법 2 )     orElseGet(   );  -> Supplier 라는 인터페이스를 생성 -  동시에 함수를 오버라이딩 해줘야만 new가 가능.  왜? 인터페이스니까
		// 만약 DB 검색결과가 null 이라면 익명의 User객체를 만들어서 return 해주는 것이다.  빈 User객체는 null은 아니니까
/* 	User user = userRepository.findById(id).orElseGet(new Supplier<User>() {

			@Override
			public User get() {

				return new User();
			}
			
		}); */
		
		// 선호하는 방법 3) 
		/*	 * 
		 * findById() 를 자세히  들여다 보면 이런 설명이 있다. 데이터를 찾지 못하면 IllegalArgumentException 을 날리라는 것 - 잘못된 인수가 들어왔다는 것
		 * @throws IllegalArgumentException if {@literal id} is {@literal null}.
		 * 
		 * 유저가 존재하지 않을 경우 빈 유저 객체가 아니라 IllegalArgumentException 을 날린다. 
		 * 
		 * 
		//   [   람다식   ] : 일단 참고만 하자 !
		User user = userRepository.findById(id).orElseThrow(()->{  //어떤 타입이 들어가야하는 지 신경써줄 필요가 없다. 
			return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id : " + id);
		});
		*/
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				//이런 에러가 발생할 때 스프링에서 요청을 가로채서 에러페이지로 보여주는 것이 있다. 그게 바로 Interceptor 
				return new IllegalArgumentException("해당 유저는 존재하지 않습니다. id : " + id);
			}
		});
		
		// 요청 : 웹 브라우저  //@RestController : 데이터를 리턴
		//user 객체는 자바 Object이므로 user객체를 웹브라우저가 이해할 수 있는 데이터(JSON)으로 변환 -> (스프링 :  Gson 라이브러리 사용)
		// but ! 스프링 부트에서는 MessageConverter가 응답 시 자동으로 작동하여 Jackson 라이브러리를 호출하여 json으로 변환해서 브라우저게 던져준다.
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http의 body에 username, password, email 데이터를 가지고 요청
	//@RequestParam("username") 과 같이 받아올 키 값을 명시해주면 다른 이름의 변수로도 받을 수 있고 굳이 명시하지 않고 키 값과 변수명을 맞춰주기만 해도 된다.
	@PostMapping("/dummy/join")
	public String join(@RequestParam("username") String name, String password, String email) { //key - value 형태의 데이터를 받는 것이다. (약속된 규칙) -스프링이 제공
		
		System.out.println("username : " + name);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		
		return "회원가입이 완료되었습니다. ";
	}
	
	@PostMapping("/dummy/join2")
	public String join2(User user) { 	//오브젝트로 받기 

		System.out.println("id : " + user.getId());  //auto_increment 적용
		System.out.println("role : " + user.getRole());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER);  //실수를 방지하기 위해 RoleType이라는 enum을 정의해서 사용한다.  넣을 수 있는 값을 한정하여 강제하는 것 -> 데이터의 도메인(범위,기본값)을 만들 때 
		userRepository.save(user);
		return "회원가입이 완료되었습니다. ";
	}
}
