package com.example.otopark_yonetim.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.otopark_yonetim.models.ERole;
import com.example.otopark_yonetim.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
	  Optional<Role> findByName(ERole name);
}
