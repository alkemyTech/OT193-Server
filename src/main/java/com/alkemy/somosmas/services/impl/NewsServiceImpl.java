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
		News newsEntity = newsMapper.newsDTO2Entity(dto);
		News newsEntitySaved = this.newsRepository.save(newsEntity);
		NewsDTO result = newsMapper.newsEntity2DTO(newsEntitySaved);
		return result;
	}

}
