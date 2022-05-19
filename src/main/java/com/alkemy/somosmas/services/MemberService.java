package com.alkemy.somosmas.services;


import java.util.List;

import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

public interface MemberService {

	public MemberDTO save(MemberDTO memberDTO);

	public void delete(Long id) throws ModelNotFoundException;

	public List<MemberDTO> getAll();

}
