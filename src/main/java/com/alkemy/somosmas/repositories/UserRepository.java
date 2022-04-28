package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
