package com.alkemy.somosmas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.CommentaryBasicDTO;
import com.alkemy.somosmas.dtos.CommentaryDTO;
import com.alkemy.somosmas.dtos.UserDTOId;
import com.alkemy.somosmas.exceptions.CommentException;
import com.alkemy.somosmas.exceptions.InvalidUserException;

@Service
public interface CommentaryService {
	public List<CommentaryBasicDTO> findAllOrderByCreateDate();
	public CommentaryDTO save(CommentaryDTO dto);
	public CommentaryDTO update(Long idCom,CommentaryDTO dto)throws InvalidUserException,CommentException;
	public void delete(Long id)throws InvalidUserException,CommentException;
	public List<CommentaryBasicDTO> getByNewsId(Long id);
}
