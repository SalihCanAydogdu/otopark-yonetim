package com.example.otopark_yonetim.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.repositories.AracRepository;

@Service
public class AracServiceImp extends AracService{

	public AracServiceImp(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double fiyatHesapla(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
