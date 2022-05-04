package com.alkemy.somosmas.ong.repositories;

import com.alkemy.somosmas.ong.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
