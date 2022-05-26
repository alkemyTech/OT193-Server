package com.alkemy.somosmas.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.CommentaryBasicDTO;
import com.alkemy.somosmas.dtos.CommentaryDTO;
import com.alkemy.somosmas.models.Commentary;
import com.alkemy.somosmas.repositories.NewsRepository;
import com.alkemy.somosmas.repositories.UserRepository;

@Component
public class CommentaryMapper {

	@Autowired
	NewsRepository newsRepo;
	@Autowired
	UserRepository userRepo;

	public CommentaryBasicDTO modelToDTO(Commentary commentary) {
		CommentaryBasicDTO cDTO = new CommentaryBasicDTO();
		cDTO.setBody(commentary.getBody());
		return cDTO;
	}

	public List<CommentaryBasicDTO> listCommentaryDTO(List<Commentary> commentaryList) {
		List<CommentaryBasicDTO> dtoList = commentaryList
                .stream()
                .map(i->this.modelToDTO(i))
                .collect(Collectors.toList());
        return dtoList;
	}

	public CommentaryDTO commentaryToDTO(Commentary commentary) {
		CommentaryDTO dto = new CommentaryDTO();
		dto.setBody(commentary.getBody());
		dto.setNews_id(commentary.getNewsId());
		dto.setUser_id(commentary.getUserId());
		dto.setId(commentary.getId());
		return dto;
	}

	public Commentary dtoToModel(CommentaryDTO dto) {
		Commentary com = new Commentary();
		com.setBody(dto.getBody());
		com.setNewsId(dto.getNews_id());
		com.setUserId(dto.getUser_id());
		return com;
	}

	public Commentary commentaryRefreshValues(Commentary commentary, CommentaryDTO dto) {
		if(dto.getBody()!=null && !dto.getBody().isEmpty()) {
			commentary.setBody(dto.getBody());
		}
		return commentary;
	}
}
