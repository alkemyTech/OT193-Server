package com.alkemy.somosmas.services;


import java.util.List;

import com.alkemy.somosmas.dtos.MemberDTO;

public interface MemberService {

	public MemberDTO save(MemberDTO memberDTO);

	public void delete(Long id);

	public List<MemberDTO> getAll();

}
