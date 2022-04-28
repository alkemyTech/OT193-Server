package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
