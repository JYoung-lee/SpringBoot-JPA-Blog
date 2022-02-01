package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	@Id  //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // (Oracle) 시퀀스, (MySQL) auto..increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne	// 연관관계  : 하나의 글에는 여러 댓글이 달수있다. 
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne    // 연관관계 : 한명의 유저는 여러 댓글을 달수있다.
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
