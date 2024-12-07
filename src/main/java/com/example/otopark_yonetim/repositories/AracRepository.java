package com.example.otopark_yonetim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.otopark_yonetim.entities.Arac;

@Repository
public interface AracRepository extends JpaRepository<Arac,Long> {

}
