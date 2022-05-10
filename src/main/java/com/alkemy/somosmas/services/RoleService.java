package com.alkemy.somosmas.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alkemy.somosmas.models.Role;

@Service
public interface RoleService {
	public Optional<Role> findById(Long id);
}
