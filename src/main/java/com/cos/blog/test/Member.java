package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter		
//@Setter
//@Data										//getter/setter 항방에 만들어준다.
//@AllArgsConstructor	 		// lombok 생성자를 만들어준다.
//@RequiredArgsConstructor // 파이널 붙은 변수에 생성자를 만들어준다.

@Data
//@AllArgsConstructor  	// 올 생성자
@NoArgsConstructor  	// 빈 생성자
public class Member {
		private int id;
		private String username;
		private String password;
		private String email;
		
		@Builder							
		public Member(int id, String username, String password, String email) {
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
		}
		
		
		
}
