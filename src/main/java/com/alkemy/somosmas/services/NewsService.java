package com.alkemy.somosmas.services;




import com.alkemy.somosmas.dto.NewsDTO;

public interface NewsService {

	NewsDTO save(NewsDTO news);

	void delete(Long id);

	NewsDTO getDetailsById(Long id);

	NewsDTO update(Long id, NewsDTO dto);
	

}