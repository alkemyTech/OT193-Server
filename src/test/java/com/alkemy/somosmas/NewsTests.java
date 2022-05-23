package com.alkemy.somosmas;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import com.alkemy.somosmas.controllers.NewsController;
import com.alkemy.somosmas.dto.NewsDTO;
import com.alkemy.somosmas.models.News;
import com.alkemy.somosmas.repositories.NewsRepository;
import com.alkemy.somosmas.services.NewsService;

//@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class NewsTests {

//	@Autowired
//	private NewsRepository newsRepository; 
	@InjectMocks
	private NewsController newsController; 
	@Mock
	private NewsService newsService; 
	
	@Test
	public void getDetailsById(Long id) {
		NewsDTO newsMock = new NewsDTO();
		
		newsMock.setCategoryId(1L);
		newsMock.setContent("sdad");
		newsMock.setImage("gfdgdf");
		newsMock.setName("fdas");
		
		newsService.save(newsMock);
		
		when(newsService.getDetailsById(1L)).thenReturn(newsMock);
		
	//	ResponseEntity<NewsDTO> result = newsController.getDetailsById(1L);
		
		verify(newsService).getDetailsById(1L);
			
	}
}
