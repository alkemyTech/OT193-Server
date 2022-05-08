package com.alkemy.somosmas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dto.NewsDTO;
import com.alkemy.somosmas.mapper.NewsMapper;
import com.alkemy.somosmas.models.News;
import com.alkemy.somosmas.repositories.NewsRepository;
import com.alkemy.somosmas.services.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public NewsDTO save(NewsDTO dto) {
		if (validateDTO(dto)) {
			throw new RuntimeException("DTO invalid");
		}
		News newsEntity = this.newsMapper.newsDTO2Entity(dto);
		News newsEntitySaved = this.newsRepository.save(newsEntity);
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntitySaved);
		return result;
	}

	private boolean validateDTO(NewsDTO dto) {
		return dto.getCategory() == null || dto.getImage() == null || dto.getName() == null || dto.getContent() == null
				|| dto.getImage().isBlank() || dto.getName().isBlank() || dto.getContent().isBlank();
	}

	@Override
	public void delete(Long id) {
		this.newsRepository.deleteById(id);
	}

}
