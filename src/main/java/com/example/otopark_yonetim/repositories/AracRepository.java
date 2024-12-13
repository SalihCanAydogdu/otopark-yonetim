package com.example.otopark_yonetim.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.otopark_yonetim.entities.Arac;

@Repository
public interface AracRepository extends JpaRepository<Arac, Long> {
	long countByPlakaIgnoreCase(String plaka);

	List<Arac> findByIcerdeIsTrue();
	// List<Arac> findByCikisSaatiIsNull();
}
