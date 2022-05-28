package com.alkemy.somosmas.services.impl;


import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.mappers.NewsMapper;
import com.alkemy.somosmas.models.News;
import com.alkemy.somosmas.repositories.NewsRepository;
import com.alkemy.somosmas.services.NewsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public NewsDTO save(NewsDTO dto) {
		News newsEntity = this.newsMapper.newsDTO2Entity(dto);
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

	@Override
	public Map<String, Object> getAllNewsByPage(int pageNo) throws NotAcceptableArgumentException, PageEmptyException {

		if(pageNo<0){
			throw new NotAcceptableArgumentException("The page number must be positive");
		}
		Pageable pageable = PageRequest.of(pageNo,10);

		Page<News> allNewsPage= newsRepository.findAll(pageable);

		if(allNewsPage.isEmpty()){
			throw new PageEmptyException(pageNo, "news");
		}

		List<News> newsModel = allNewsPage.getContent();

		List<NewsDTO> newsDtoReturned = newsModel
				.stream()
				.map(i->newsMapper.newsEntity2DTO(i))
				.collect(Collectors.toList());

		Map<String, Object> returnedMap = new HashMap<>();
		returnedMap.put("News", newsDtoReturned);
		returnedMap.put("currentPage",allNewsPage.getNumber());
		returnedMap.put("totalItems",allNewsPage.getTotalElements());
		returnedMap.put("totalPages",allNewsPage.getTotalPages());

		if (allNewsPage.hasNext()){
			returnedMap.put("nextPage","localhost:8080/news?page="+(pageNo+1));
		}
		if (pageNo!=0){
			returnedMap.put("previousPage","localhost:8080/news?page="+(pageNo-1));
		}

		return returnedMap;

	}

}
