package com.alkemy.somosmas.seviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.repositories.RoleRepository;
import com.alkemy.somosmas.services.RoleService;

public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}
}
