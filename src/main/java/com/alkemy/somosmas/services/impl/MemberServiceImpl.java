package com.alkemy.somosmas.services.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alkemy.somosmas.dtos.CategoryDTO;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import com.alkemy.somosmas.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
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
	public void delete(Long id) throws ModelNotFoundException {
		if (!this.memberRepository.existsById(id)) {
			throw new ModelNotFoundException(id,"Member");  
		}
		this.memberRepository.deleteById(id);
	}

	@Override
	public List<MemberDTO> getAll() {
		List<Member> membersEntity = this.memberRepository.findAll();
		List<MemberDTO> membersDTO = this.memberMapper.membersEntityList2DTOList(membersEntity);
		return membersDTO;
	}



	@Override
	public Map<String, Object> getAllMembersByPage(int pageNo ) throws NotAcceptableArgumentException, PageEmptyException {
		if(pageNo<0){
			throw new NotAcceptableArgumentException("The page number must be positive");
		}
		Pageable pageable = PageRequest.of(pageNo,10);

		Page<Member> allMembersPage= memberRepository.findAll(pageable);

		if(allMembersPage.isEmpty()){
			throw new PageEmptyException(pageNo, "members");
		}

		List<Member> membersModel = allMembersPage.getContent();

		List<MemberDTO> membersDtoReturned = membersModel
				.stream()
				.map(i->memberMapper.memberEntity2DTO(i))
				.collect(Collectors.toList());

		Map<String, Object> returnedMap = new HashMap<>();

		returnedMap.put("Members", membersDtoReturned);
		returnedMap.put("currentPage",allMembersPage.getNumber());
		returnedMap.put("totalItems",allMembersPage.getTotalElements());
		returnedMap.put("totalPages",allMembersPage.getTotalPages());

		if (allMembersPage.hasNext()){
			returnedMap.put("nextPage","localhost:8080/members?page="+(pageNo+1));
		}
		if (pageNo!=0){
			returnedMap.put("previousPage","localhost:8080/members?page="+(pageNo-1));
		}

		return returnedMap;
	}

	@Override
	public MemberDTO update(Long id, MemberDTO memberDTO) throws ModelNotFoundException {
		Member memberEntity = this.memberRepository.findById(id).orElse(null);
		if (memberEntity == null) {
			throw new ModelNotFoundException(id,"Member");
		}
		this.memberMapper.memberEntityRefreshValues(memberEntity, memberDTO);
		Member memberEntityModified = this.memberRepository.save(memberEntity);
		MemberDTO result = this.memberMapper.memberEntity2DTO(memberEntityModified);
		
		return result;
	}

}
