package com.alkemy.somosmas.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.mappers.MemberMapper;
import com.alkemy.somosmas.models.Member;
import com.alkemy.somosmas.repositories.MemberRepository;
import com.alkemy.somosmas.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public MemberDTO save(MemberDTO memberDTO) {
		Member memberEntity = this.memberMapper.memberDTO2Entity(memberDTO);
		Member memberEntitySaved = this.memberRepository.save(memberEntity);
		MemberDTO result = this.memberMapper.memberEntity2DTO(memberEntitySaved);
		return result;

	}

	@Override
	public void delete(Long id) {
		if (!this.memberRepository.existsById(id)) {
			//throw  
		}
		this.memberRepository.deleteById(id);
	}

	@Override
	public List<MemberDTO> getAll() {
		List<Member> membersEntity = this.memberRepository.findAll();
		List<MemberDTO> membersDTO = this.memberMapper.membersEntityList2DTOList(membersEntity);
		return membersDTO;
	}

}
