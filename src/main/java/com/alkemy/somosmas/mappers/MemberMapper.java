package com.alkemy.somosmas.mappers;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<MemberDTO> membersEntityList2DTOList(List<Member> membersEntity) {
		List<MemberDTO> membersDTO = membersEntity.stream()
										.map(i->this.memberEntity2DTO(i))
										.collect(Collectors.toList());
		return membersDTO;
	}

	public void memberEntityRefreshValues(Member memberEntity, MemberDTO memberDTO) {
		if (memberDTO.getName() != null && !memberDTO.getName().isBlank()) {
			memberEntity.setName(memberDTO.getName());
		}
		if (memberDTO.getFacebookUrl() != null && !memberDTO.getFacebookUrl().isBlank()) {
			memberEntity.setFacebookUrl(memberDTO.getFacebookUrl());
		}
		if (memberDTO.getInstagramUrl() != null && !memberDTO.getInstagramUrl().isBlank()) {
			memberEntity.setInstagramUrl(memberDTO.getInstagramUrl());
		}
		if (memberDTO.getLinkedinUrl() != null && !memberDTO.getLinkedinUrl().isBlank()) {
			memberEntity.setLinkedinUrl(memberDTO.getLinkedinUrl());
		}
		if (memberDTO.getImage() != null && !memberDTO.getImage().isBlank()) {
			memberEntity.setImage(memberDTO.getImage());
		}
		if (memberDTO.getDescription() != null && !memberDTO.getDescription().isBlank()) {
			memberEntity.setDescription(memberDTO.getDescription());
		}
		
	}
}
