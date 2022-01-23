package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// 사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	// 인터넷 브라우저 요청은 get 요청만 할수있다.
	// http://localhost:8080/http/get (SELECT)
	@GetMapping("/http/get")
	public String getTest(Member m) { // ?id=1&username=ssar&password=1234&email=ssar@nate.com 알아서 넣어준다.
		return "Get 요청: " + m.getId() +", " + m.getUsername()+ ", " + m.getPassword() + "," + m.getEmail() ;
	}
	
	//http://localhost:8080/http/post (INSERT)
	@PostMapping("/http/post") //raw -> text/plain   // application /json
	public String postTest(@RequestBody Member m) { // MessageCpoverter (스프링부트)
		return "Post : " + m.getId() +", " + m.getUsername()+ ", " + m.getPassword() + "," + m.getEmail() ;
		//return "Post : " + text;
	}
	//http://localhost:8080/http/put (UPDATE)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put : " + m.getId() +", " + m.getUsername()+ ", " + m.getPassword() + "," + m.getEmail() ;
		//return "put 요청";
	}

	//http://localhost:8080/http/delete (DELETE)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
