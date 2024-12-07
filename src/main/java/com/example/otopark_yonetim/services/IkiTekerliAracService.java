package com.example.otopark_yonetim.services;

import org.springframework.stereotype.Service;

import com.example.otopark_yonetim.security.repository.AracRepository;



@Service
public class IkiTekerliAracService extends AracService{



	public IkiTekerliAracService(AracRepository aracRepository) {
		super(aracRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fiyatHesapla() {
		// TODO Auto-generated method stub

	}



}
