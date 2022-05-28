package com.alkemy.somosmas.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.CommentaryBasicDTO;
import com.alkemy.somosmas.dtos.CommentaryDTO;
import com.alkemy.somosmas.dtos.UserDTOId;
import com.alkemy.somosmas.exceptions.CommentException;
import com.alkemy.somosmas.exceptions.InvalidUserException;
import com.alkemy.somosmas.mappers.CommentaryMapper;
import com.alkemy.somosmas.models.Commentary;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.CommentaryRepository;
import com.alkemy.somosmas.repositories.UserRepository;
import com.alkemy.somosmas.services.CommentaryService;

@Service
public class CommentaryServiceImpl implements CommentaryService{

	@Autowired
	CommentaryRepository repository;
	@Autowired
	CommentaryMapper mapper;
	@Autowired
	UserRepository userRepo;

	@Override
	public List<CommentaryBasicDTO> findAllOrderByCreateDate() {
		List <CommentaryBasicDTO> listDTO = mapper.listCommentaryDTO(repository.findAllByOrderByCreateDateAsc());
		return listDTO;
	}

	@Override
	public CommentaryDTO save(CommentaryDTO dto) {
		Commentary newCom = mapper.dtoToModel(dto);
		Commentary newComSaved = repository.save(newCom);
		CommentaryDTO result = mapper.commentaryToDTO(newComSaved);
		return result;
	}

	@Override
	public CommentaryDTO update(Long idCom,CommentaryDTO dto) throws InvalidUserException, CommentException {
		Commentary com = repository.findById(idCom).orElse(null);
//		User user = userRepo.findById(idUser.getId()).orElse(null);
//		if(com == null){
//			throw new CommentException("Comment does not exist");
//		}
//		if(user==null) {
//			throw new InvalidUserException("User does not exist");
//		}
//		if(!user.getRole().getName().equals("ADMIN") || user.getIdUser()!=dto.getUserId()) {
//			throw new InvalidUserException("UNAUTHORIZED");
//		}
		com = mapper.commentaryRefreshValues(com, dto);
		Commentary comSaved = repository.save(com);
		CommentaryDTO result = mapper.commentaryToDTO(comSaved);
		return result;
	}

	@Override
	public void delete(Long id) throws InvalidUserException,CommentException{
		if(!repository.existsById(id)) {
			throw new CommentException("Comment does not exist");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth==null) {
			throw new InvalidUserException("UNAUTHORIZED");
		}
		repository.deleteById(id);
	}

	@Override
	public List<CommentaryBasicDTO> getByNewsId(Long id) {
		List<Commentary> commentaries = repository.findAllByNewsId(id);
		List<CommentaryBasicDTO> listDTO = mapper.listCommentaryDTO(commentaries);
		return listDTO;
	}

}
