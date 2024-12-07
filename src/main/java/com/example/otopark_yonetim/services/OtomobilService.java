package com.example.otopark_yonetim.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.repositories.AracRepository;

@Service
public class OtomobilService extends AracService {

	public OtomobilService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double fiyatHesapla() {
		return 12.2;
	}

}
