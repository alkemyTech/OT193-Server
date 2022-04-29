package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization , Long> {
}
