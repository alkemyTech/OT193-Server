package com.alkemy.somosmas.services;


import javax.validation.Valid;

import com.alkemy.somosmas.dto.NewsDTO;

public interface NewsService {

	NewsDTO save(@Valid NewsDTO news);

	void delete(@Valid Long id);

	NewsDTO getDetailsById(@Valid Long id);
	

}
