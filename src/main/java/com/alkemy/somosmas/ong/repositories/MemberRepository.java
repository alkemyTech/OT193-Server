package com.alkemy.somosmas.ong.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.ong.models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    
}
