package com.alkemy.somosmas.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.repositories.RoleRepository;
import com.alkemy.somosmas.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Optional<Role> getById(Long id) {
		return roleRepository.findById(id);
	}

}
