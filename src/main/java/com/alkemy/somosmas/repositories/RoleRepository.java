package com.alkemy.somosmas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
