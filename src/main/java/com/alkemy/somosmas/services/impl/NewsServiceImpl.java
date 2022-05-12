package com.alkemy.somosmas.services.impl;

import java.util.Optional;

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
			throw new RuntimeException("Invalid DTO");
		}
		News newsEntity = this.newsMapper.newsDTO2Entity(dto);
		News newsEntitySaved = this.newsRepository.save(newsEntity);
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntitySaved);
		return result;
	}

	private boolean validateDTO(NewsDTO dto) {
		return dto.getCategoryId() == null || dto.getImage() == null || dto.getName() == null
				|| dto.getContent() == null || dto.getImage().isBlank() || dto.getName().isBlank()
				|| dto.getContent().isBlank();
	}

	@Override
	public void delete(Long id) {
		if (!this.newsRepository.existsById(id)) {
			throw new RuntimeException("Invalid ID");
		}
		this.newsRepository.deleteById(id);
	}

	@Override
	public NewsDTO getDetailsById(Long id) {
		Optional<News> newsEntity = this.newsRepository.findById(id); 
		if (!newsEntity.isPresent()) {
			throw new RuntimeException("Invalid ID");
		}
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntity.get());
		return result;
	}

}
