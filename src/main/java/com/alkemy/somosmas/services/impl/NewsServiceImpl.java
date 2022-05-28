package com.alkemy.somosmas.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.mappers.NewsMapper;
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
	public NewsDTO save(NewsDTO dto) throws ModelNotFoundException {
		News newsEntity = null;
		if (dto.getId() != null && dto.getId() != 0) {
			newsEntity = this.newsRepository.findById(dto.getId()).orElse(null);
			if (newsEntity == null) {
				throw new ModelNotFoundException(dto.getId(), "News");
			}
		}
		newsEntity = this.newsMapper.newsDTO2Entity(dto);
		News newsEntitySaved = this.newsRepository.save(newsEntity);
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntitySaved);
		return result;
	}

		
	

	@Override
	public void delete(Long id) throws ModelNotFoundException {
		if (!this.newsRepository.existsById(id)) {
			throw new ModelNotFoundException(id,"News");
		}
		this.newsRepository.deleteById(id);
	}


	@Override
	public NewsDTO getDetailsById(Long id) throws ModelNotFoundException {
		News newsEntity = this.newsRepository.findById(id).orElse(null); 
		if (newsEntity == null) {
			throw new ModelNotFoundException(id,"News");
		}
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntity);
		return result;
	}

	@Override
	public NewsDTO update(Long id, NewsDTO dto) throws ModelNotFoundException {
		News newsEntity = this.newsRepository.findById(id).orElse(null);
		if (newsEntity == null) {
			throw new ModelNotFoundException(id,"News");
		}
		this.newsMapper.newsEntityRefreshValues(newsEntity, dto);
		News newsEntityModified = this.newsRepository.save(newsEntity);
		NewsDTO result = this.newsMapper.newsEntity2DTO(newsEntityModified);
		
		return result;
	}

}
