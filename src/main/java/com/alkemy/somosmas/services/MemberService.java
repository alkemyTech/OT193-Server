package com.alkemy.somosmas.services;


import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

public interface MemberService {

	public MemberDTO save(MemberDTO memberDTO);

	public void delete(Long id) throws ModelNotFoundException;

}
