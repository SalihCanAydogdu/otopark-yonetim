package com.example.otopark_yonetim.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.repositories.AracRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public abstract class AracService {

	protected AracRepository aracRepository;

	public AracService(AracRepository aracRepository) {
		super();
		this.aracRepository = aracRepository;
	}

	public abstract double fiyatHesapla();

	public Arac aracGirisYapti(String plaka) {
		Arac arac = Arac.builder().plaka(plaka).girisSaati(LocalDateTime.now()).cikisSaati(null).build();

		return aracRepository.save(arac);
	}

	public Arac aracCikisYapti(Long id) {
		Arac arac = aracRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

		arac.setCikisSaati(LocalDateTime.now());

		Arac savedArac = aracRepository.save(arac);

		return aracRepository.save(savedArac);
	}

}
