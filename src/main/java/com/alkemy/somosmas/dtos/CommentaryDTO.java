package com.alkemy.somosmas.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentaryDTO {
	private Long id;
	@NotNull(message = "User is mandatory")
	private Long user_id;
	@NotNull(message = "News is mandatory")
	private Long news_id;
	@NotBlank(message = "Body is mandatory")
	private String body;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getNews_id() {
		return news_id;
	}
	public void setNews_id(Long news_id) {
		this.news_id = news_id;
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
