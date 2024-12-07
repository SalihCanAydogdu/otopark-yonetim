package com.example.otopark_yonetim.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.repositories.AracRepository;

@Service
public class AgirTicariAracService extends AracService {

	public AgirTicariAracService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fiyatHesapla() {
	}

}
