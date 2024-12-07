package com.example.otopark_yonetim.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.security.repository.AracRepository;

@Service
public class HafifTicariAracService extends AracService {

	public HafifTicariAracService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fiyatHesapla() {
	}

}
