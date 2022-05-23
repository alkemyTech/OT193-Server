package com.alkemy.somosmas.mappers;


import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.models.News;

@Component
public class NewsMapper {

	public News newsDTO2Entity(NewsDTO dto) {
		News news = new News();
		news.setName(dto.getName());
		news.setImage(dto.getImage());
		news.setContent(dto.getContent());
		news.setCategoryId(dto.getCategoryId());
		return news;
	}

	public NewsDTO newsEntity2DTO(News entity) {
		NewsDTO dto = new NewsDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		dto.setContent(entity.getContent());
		dto.setCategory(entity.getCategoryId());
		return dto;
	}

	public void newsEntityRefreshValues(News newsEntity, NewsDTO dto) {
		if (dto.getCategoryId() != null) {
			newsEntity.setCategoryId(dto.getCategoryId());
		}
		if (dto.getContent() != null && !dto.getContent().isBlank()) {
			newsEntity.setContent(dto.getContent());			
		}
		if (dto.getImage() != null && !dto.getImage().isBlank()) {
			newsEntity.setImage(dto.getImage());
		}
		if (dto.getName() != null && !dto.getName().isBlank()) {
			newsEntity.setName(dto.getName());
		}
	}
	
	

}
