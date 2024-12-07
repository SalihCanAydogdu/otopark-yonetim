package com.example.otopark_yonetim.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.security.repository.AracRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public abstract class AracService {

	private AracRepository aracRepository;

	public AracService(AracRepository aracRepository) {
		super();
		this.aracRepository = aracRepository;
	}

	public abstract void fiyatHesapla();

	public Arac aracGirisYapti(String plaka) {
		Arac arac = Arac.builder().plaka(plaka).girisSaati(LocalDateTime.now()).cikisSaati(null).build();

		return aracRepository.save(arac);
	}

	public Arac aracCikisYapti(Long id) {

		Arac cikisYapanArac = aracRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

		cikisYapanArac.setCikisSaati(LocalDateTime.now());
		return cikisYapanArac;

	}

}
