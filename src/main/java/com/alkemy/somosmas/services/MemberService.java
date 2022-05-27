package com.alkemy.somosmas.services;


import java.util.List;
import java.util.Map;


import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;

public interface MemberService {

	public MemberDTO save(MemberDTO memberDTO);

	public void delete(Long id) throws ModelNotFoundException;

	public List<MemberDTO> getAll();

	public MemberDTO update(Long id, MemberDTO memberDTO) throws ModelNotFoundException;
	public Map<String, Object> getAllMembersByPage(int pageNo ) throws NotAcceptableArgumentException, PageEmptyException;

}
