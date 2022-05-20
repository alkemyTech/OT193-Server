package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    boolean existsByEmail(String email);
}
