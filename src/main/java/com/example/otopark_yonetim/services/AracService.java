package com.example.otopark_yonetim.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.repositories.AracRepository;
import com.example.otopark_yonetim.state.DiscountState;
import com.example.otopark_yonetim.state.FiveToTwentyNineDiscountState;
import com.example.otopark_yonetim.state.NoDiscountState;
import com.example.otopark_yonetim.state.ThirtyPlusDiscountState;

import jakarta.persistence.EntityNotFoundException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Service
@Transactional
public abstract class AracService {

	protected AracRepository aracRepository;

	public AracService(AracRepository aracRepository) {
		super();
		this.aracRepository = aracRepository;
	}

	public abstract double fiyatHesapla(Long id);

	public Arac aracGirisYapti(String plaka, String aracTuru) {
		Arac arac = Arac.builder().plaka(plaka).aracTuru(aracTuru).girisSaati(LocalDateTime.now()).cikisSaati(null)
				.build();

		return aracRepository.save(arac);
	}

	public Arac aracCikisYapti(Long id) {
		Arac arac = aracRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

		arac.setCikisSaati(LocalDateTime.now());

		Arac savedArac = aracRepository.save(arac);

		return aracRepository.save(savedArac);
	}

	public double hesaplaDakikaFarki(LocalDateTime girisTarihi, LocalDateTime cikisTarihi) {

		// Farkı hesaplama
		Duration fark = Duration.between(girisTarihi, cikisTarihi);

		// Süreyi dakika olarak döndürme
		return fark.toMillis() / 60000.0; // Millisaniyeden dakikaya çevirirken double döndürüyoruz
	}

	public double fiyatFormatter(double fiyat) {
		DecimalFormat df = new DecimalFormat("#,###.##", DecimalFormatSymbols.getInstance(Locale.US));
		return Double.parseDouble(df.format(fiyat));
	}

	public double handleAracDiscount(String plaka) {
		// Plaka numarasına göre aracın kaydedilme sayısını al
		long girisSayisi = aracRepository.countByPlakaIgnoreCase(plaka);

		// Durum sınıfı oluştur
		DiscountState currentState;

		// Durum geçişi kontrolü
		if (girisSayisi < 5) {
			currentState = new NoDiscountState();
		} else if (girisSayisi < 30) {
			currentState = new FiveToTwentyNineDiscountState();
		} else {
			currentState = new ThirtyPlusDiscountState();
		}

		// İndirim oranini ayarla
		double discount = currentState.handleDiscount();

		return discount;
	}

}
