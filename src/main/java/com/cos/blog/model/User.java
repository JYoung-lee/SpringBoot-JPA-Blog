package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	// 빌더 패턴
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User클래스가 자동으로 MySQL에 테이블이 생성이 된다.
//@DynamicInsert //insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id  //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	//yml > JPA 셋팅 상 use-new-id-generator-mappings JPA에서 사용하는 넘버링 전략을 따라가지 않는다 . ture는 따라간다.
	private int id; // (Oracle) 시퀀스, (MySQL) auto..increment
	
	@Column(nullable = false, length = 30)  // nullable = false (Notnull), length = 길이
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")  // ' ' 안에 문자열을 적어야한다.
	//DB는 RoleType이 라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;  // Enum을 쓰는게 좋다. // admin, user, manager      // Enum = 도메인을 설정할수 있다.  특정 권한을 줄수있다.          
	
	@CreationTimestamp //타임 자동 입력
	private Timestamp createDate; 
}
