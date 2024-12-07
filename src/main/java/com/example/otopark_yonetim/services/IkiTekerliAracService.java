package com.example.otopark_yonetim.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.repositories.AracRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IkiTekerliAracService extends AracService {

	public IkiTekerliAracService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double fiyatHesapla(Long id) {

		Arac arac = aracRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		LocalDateTime aracinGirisVakti = arac.getGirisSaati();
		LocalDateTime aracinCikisSaati = arac.getCikisSaati();
		return 12 + (hesaplaDakikaFarki(aracinGirisVakti, aracinCikisSaati) * 1.10);

	}

}
