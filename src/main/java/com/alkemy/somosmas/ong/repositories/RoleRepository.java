package com.alkemy.somosmas.ong.repositories;

import com.alkemy.somosmas.ong.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
