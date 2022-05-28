package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

public interface NewsService {

	NewsDTO save(NewsDTO news);

	void delete(Long id) throws ModelNotFoundException;

	NewsDTO getDetailsById(Long id) throws ModelNotFoundException;

	NewsDTO update(Long id, NewsDTO dto) throws ModelNotFoundException;

}
