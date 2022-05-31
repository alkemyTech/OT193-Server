package com.alkemy.somosmas.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.alkemy.somosmas.dtos.CommentaryBasicDTO;
import com.alkemy.somosmas.dtos.CommentaryDTO;
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
		if(com == null){
			throw new CommentException("Comment does not exist");
		}
		User user = userRepo.getById(com.getUserId());
		if(user==null) {
			throw new InvalidUserException("User does not exist");
		}
		if(hasAccessToCommentary(idCom)) {
			com = mapper.commentaryRefreshValues(com, dto);
			Commentary comSaved = repository.save(com);
			CommentaryDTO result = mapper.commentaryToDTO(comSaved);
			return result;
		}else {
			throw new InvalidUserException("UNAUTHORIZED");
		}
	}

	@Override
	public void delete(Long id) throws InvalidUserException,CommentException{
		if(!repository.existsById(id)) {
			throw new CommentException("Comment does not exist");
		}
		if(hasAccessToCommentary(id)) {
			repository.deleteById(id);
		}else {
			throw new InvalidUserException("UNAUTHORIZED");
		}
	}

	private boolean hasAccessToCommentary(Long id) {
		Commentary com = repository.getById(id);
		User user = userRepo.getById(com.getUserId());
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		boolean hasAccess = false;
		if(auth!=null && auth instanceof org.springframework.security.core.userdetails.User) {
			String username = ((org.springframework.security.core.userdetails.User)auth).getUsername();
			Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			hasAccess = username.equals(user.getEmail());
			if(!hasAccess) {
				for(GrantedAuthority ga : roles) {
					if(ga.getAuthority().equals("admin")) {
						hasAccess = true;
						break;
					}
				}
			}
		}
		return hasAccess;
	}
	@Override
	public List<CommentaryBasicDTO> getByNewsId(Long id) {
		List<Commentary> commentaries = repository.findAllByNewsId(id);
		List<CommentaryBasicDTO> listDTO = mapper.listCommentaryDTO(commentaries);
		return listDTO;
	}

}
