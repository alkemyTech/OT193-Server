package com.alkemy.somosmas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(String name);
}
