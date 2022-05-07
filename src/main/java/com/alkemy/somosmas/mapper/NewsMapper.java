package com.alkemy.somosmas.mapper;

import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dto.NewsDTO;
import com.alkemy.somosmas.models.News;

@Component
public class NewsMapper {

	public News newsDTO2Entity(NewsDTO dto) {
		News news = new News();
		news.setName(dto.getName());
		news.setImage(dto.getImage());
		news.setContent(dto.getContent());
		news.setCategory(dto.getCategory());
		return news;
	}

	public NewsDTO newsEntity2DTO(News entity) {
		NewsDTO dto = new NewsDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setContent(entity.getContent());
		dto.setCategory(entity.getCategory());
		return dto;
	}

}
