package com.alkemy.somosmas.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentaryDTO {
	private Long id;
	@NotNull(message = "User is mandatory")
	private Long userId;
	@NotNull(message = "News is mandatory")
	private Long newsId;
	@NotBlank(message = "Body is mandatory")
	private String body;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
