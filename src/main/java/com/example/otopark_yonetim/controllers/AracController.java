package com.example.otopark_yonetim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.services.AracService;

@RestController
@RequestMapping("/api/arac")
public class AracController {

	// Servislerin enjekte edilmesi
	private final AracService ikiTekerliAracService;
	private final AracService otomobilService;
	private final AracService hafifTicariAracService;
	private final AracService agirTicariAracService;

	@Autowired
	public AracController(@Qualifier("ikiTekerliAracService") AracService ikiTekerliAracService,
			@Qualifier("otomobilService") AracService otomobilService,
			@Qualifier("hafifTicariAracService") AracService hafifTicariAracService,
			@Qualifier("agirTicariAracService") AracService agirTicariAracService) {
		this.ikiTekerliAracService = ikiTekerliAracService;
		this.otomobilService = otomobilService;
		this.hafifTicariAracService = hafifTicariAracService;
		this.agirTicariAracService = agirTicariAracService;

	}

	@PostMapping("/girisYapti/{serviceName}")
	public ResponseEntity<Object> girisYapti(@PathVariable String serviceName, @RequestParam String plaka,
			@RequestParam String aracTuru) {
		AracService aracService = getAracService(serviceName);
		if (plaka == null || plaka.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Plaka boş olamaz!");
		}

		// Yeni araç oluştur ve kaydet
		Arac yeniArac = aracService.aracGirisYapti(plaka, aracTuru);
		return ResponseEntity.status(201).body(yeniArac);
	}

	@PostMapping("/cikisYapti/{serviceName}")
	public ResponseEntity<Object> cikisYapti(@PathVariable String serviceName, @RequestParam Long id) {
		AracService aracService = getAracService(serviceName);
		aracService.aracCikisYapti(id);
		return ResponseEntity.status(200).body("Arac cikis yapti");
	}

	@GetMapping("/fiyatHesapla/{serviceName}")
	public double fiyatHesapla(@PathVariable String serviceName, @RequestParam Long id) {
		AracService aracService = getAracService(serviceName);
		return aracService.fiyatFormatter(aracService.fiyatHesapla(id));
	}

	@GetMapping("/indirimHesapla/{serviceName}")
	public double indirimHesapla(@PathVariable String serviceName, @RequestParam Long id, @RequestParam String plaka) {
		AracService aracService = getAracService(serviceName);
		double kullanimTutari = aracService.fiyatHesapla(id);
		return aracService.fiyatFormatter(((100 - aracService.handleAracDiscount(plaka)) / 100) * kullanimTutari);
	}

	/*
	 * @GetMapping("/otoparktakiAraclar") public List<AracResponse>
	 * otoparktakiAaraclar() {
	 * 
	 * }
	 */
	private AracService getAracService(String serviceName) {
		switch (serviceName) {
		case "IkiTekerliArac":
			return ikiTekerliAracService;
		case "Otomobil":
			return otomobilService;
		case "HafifTicariArac":
			return hafifTicariAracService;
		case "AgirTicariArac":
			return agirTicariAracService;
		default:
			throw new IllegalArgumentException("No service found for: " + serviceName);
		}
	}
}
