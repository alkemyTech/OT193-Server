package com.alkemy.somosmas.mappers;

import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.models.Member;

@Component		
public class MemberMapper {

	public Member memberDTO2Entity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setName(memberDTO.getName());
		member.setImage(memberDTO.getImage());
		member.setDescription(memberDTO.getDescription());
		member.setFacebookUrl(memberDTO.getFacebookUrl());
		member.setInstagramUrl(memberDTO.getInstagramUrl());
		member.setLinkedinUrl(memberDTO.getLinkedinUrl());
		return member;
	}
	
	public MemberDTO memberEntity2DTO(Member memberEntity) {
		MemberDTO dto = new MemberDTO();
		dto.setId(memberEntity.getId());
		dto.setName(memberEntity.getName());
		dto.setImage(memberEntity.getImage());
		dto.setDescription(memberEntity.getDescription());
		dto.setFacebookUrl(memberEntity.getFacebookUrl());
		dto.setInstagramUrl(memberEntity.getInstagramUrl());
		dto.setLinkedinUrl(memberEntity.getLinkedinUrl());
		return dto;

	}

}
