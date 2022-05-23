package com.alkemy.somosmas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.alkemy.somosmas.dto.NewsDTO;
import com.alkemy.somosmas.models.Category;
import com.alkemy.somosmas.models.News;
import com.alkemy.somosmas.repositories.CategoryRepository;
import com.alkemy.somosmas.repositories.NewsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsControllerTest {

	private static final Long NEWS_MOCK_1_ID = 1L;
	private static final String NEWS_MOCK_1_NAME = "Nombre";
	private static final String NEWS_MOCK_1_CONTENT = "Hola soy una news";
	private static final String NEWS_MOCK_1_IMAGE = "base64aasdasdsaDASDASdasd";
	private static final Long NEWS_MOCK_1_CATEGORY = 1L;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private boolean dataInserted = Boolean.FALSE;

	@BeforeAll
	public static void setUp() {
		System.out.println("before one shot");
	}

	@BeforeEach
	public void setUpEach() {
		System.out.println("before each test method ");

		if (!dataInserted) {
			Category category = new Category();
			category.setId(NEWS_MOCK_1_CATEGORY);
			category.setName("ASDASDSA");
			category.setDescription("asASDASD");

			categoryRepository.save(category);

			News newsToPersist = new News();
			newsToPersist.setId(NEWS_MOCK_1_ID);
			newsToPersist.setName(NEWS_MOCK_1_NAME);
			newsToPersist.setCategoryId(NEWS_MOCK_1_CATEGORY);
			newsToPersist.setContent(NEWS_MOCK_1_CONTENT);
			newsToPersist.setImage(NEWS_MOCK_1_IMAGE);

			newsRepository.save(newsToPersist);
			dataInserted = Boolean.TRUE;
		}
	}

	@Test
//	testHTTPMETHOD_testFeature() 
	public void testResponseOk_getNews() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<NewsDTO> entityRequest = new HttpEntity<NewsDTO>(new NewsDTO(), headers);

		headers.add("Authorization", "Bearer asdasdasASDASdasd");

		ResponseEntity<NewsDTO> response = testRestTemplate.exchange("/news/{id}", HttpMethod.GET, entityRequest,
				new ParameterizedTypeReference<NewsDTO>() {
				}, Map.of("id", NEWS_MOCK_1_ID));

		assertNotNull(response.getBody());
	}

	@Test
	public void testResponseError_getNews() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<NewsDTO> entityRequest = new HttpEntity<NewsDTO>(new NewsDTO(), headers);

		headers.add("Authorization", "Bearer asdasdasASDASdasd");

		ResponseEntity<NewsDTO> response = testRestTemplate.exchange("/news/{id}", HttpMethod.GET, entityRequest,
				new ParameterizedTypeReference<NewsDTO>() {
				}, Map.of("id", NEWS_MOCK_1_ID));

		assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
	}

}
