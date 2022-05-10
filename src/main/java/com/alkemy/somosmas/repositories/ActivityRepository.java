package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository <Activity, Long> {
}
