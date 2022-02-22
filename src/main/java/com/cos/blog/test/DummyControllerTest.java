package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestContorller
@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 (DI) 
	private UserRepository userRepository;
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달했을 경우 해당 id에 대한 데이터가 있으면 update를 해주고
	//save 함수는 id를 전달했을 경우 해당 id에 대한 데이터가 없으면 insert를 한다.
	// email.password  변경
	@Transactional		// 함수 종료시에 자동 commit이 된다.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object (MessageConverter의 Jackson 라이브러리가 변환해서 받아줘요.
		System.out.println("id"+ id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정을 실패하였습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//requestUser.setId(id);
		//requestUser.setUsername("ssar");
		
		//@Transactional : save 함수를 사용하지 않고 어노테이션을 사용하면 save 함수를 사용하지않아도 업데이트가 진행된다.
		//userRepository.save(user);
		
		// 더티 체킹
		return null;
	}
	
	//http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리번탇아 볼 예정
	@GetMapping("/dummy/user")						// size= 2 : 한페이지에 2개만 , sort ="id" 아이디로 sort한다, direction : 내림차순, 오름차순 정하기
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pageingUser = userRepository.findAll(pageable); // 요렇게만 하면 사용하지 않는 정보까지 불러온다.
		
		List<User> users = pageingUser.getContent(); // 요렇게 하면 Content(내용)만 가져와서 사용할 수 있다.
		
		return users;
	}
	
	// {id} 주소로 파마미터를 전달 받을 수 있음
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴되자나. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 판단해서 return해
		
		/* ex 1) null 아닌 경우 findById를 실행 , null일경우 orElseGet > get() 실행해서 빈객체 리턴해서 null이 아닌 빈 객체가 되도록 실행한다.
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() { 
				// TODO Auto-generated method stub
				return new User();
			}
		});
		*/
		
		// ex 2) null 일경우 에러메세지 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		/*
			 요청 : 웹브라우저
			 user 객체 = 자바 오브젝트
			 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
			 스브링부트  = MessageConverter라는 애가 응답시 자동 작동
			 만약에 자바오브렉트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출해서
			 user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.,
		 */
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("Role : " + user.getRole());
		System.out.println("getCreateDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}

