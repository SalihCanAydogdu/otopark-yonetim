package com.example.otopark_yonetim.security.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.security.repository.AracRepository;

@Service
public class OtomobilService extends AracService {


	public OtomobilService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fiyatHesapla() {

	}

}
