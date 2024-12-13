package com.example.otopark_yonetim.controllers;

import java.util.List;

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
import com.example.otopark_yonetim.services.AracServiceImp;
import com.example.otopark_yonetim_responses.AracResponse;

@RestController
@RequestMapping("/api/arac")
public class AracController {

	// Servislerin enjekte edilmesi
	private final AracService ikiTekerliAracService;
	private final AracService otomobilService;
	private final AracService hafifTicariAracService;
	private final AracService agirTicariAracService;
	private final AracServiceImp aracServiceImp;

	@Autowired
	public AracController(@Qualifier("ikiTekerliAracService") AracService ikiTekerliAracService,
			@Qualifier("otomobilService") AracService otomobilService,
			@Qualifier("hafifTicariAracService") AracService hafifTicariAracService,
			@Qualifier("agirTicariAracService") AracService agirTicariAracService, AracServiceImp aracServiceImp) {
		this.ikiTekerliAracService = ikiTekerliAracService;
		this.otomobilService = otomobilService;
		this.hafifTicariAracService = hafifTicariAracService;
		this.agirTicariAracService = agirTicariAracService;
		this.aracServiceImp = aracServiceImp;

	}

	@PostMapping("/girisYapti")
	public ResponseEntity<Object> girisYapti(@RequestParam String plaka, @RequestParam String aracTuru) {
		if (plaka == null || plaka.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Plaka boş olamaz!");
		}

		// Yeni araç oluştur ve kaydet
		Arac yeniArac = aracServiceImp.aracGirisYapti(plaka, aracTuru);
		return ResponseEntity.status(201).body(yeniArac);
	}

	@PostMapping("/cikisYapti")
	public ResponseEntity<Object> cikisYapti(@RequestParam Long id) {

		aracServiceImp.aracCikisYapti(id);
		return ResponseEntity.status(200).body("Arac cikis yapti");
	}

	@GetMapping("/fiyatHesapla/{serviceName}")
	public String fiyatHesapla(@PathVariable String serviceName, @RequestParam Long id) {
		AracService aracService = getAracService(serviceName);
		return aracService.fiyatFormatter(aracService.fiyatHesapla(id));
	}

	// indirimli fiyati gosteren controller
	@GetMapping("/indirimHesapla/{serviceName}")
	public String indirimHesapla(@PathVariable String serviceName, @RequestParam Long id, @RequestParam String plaka) {
		AracService aracService = getAracService(serviceName);
		double kullanimTutari = aracService.fiyatHesapla(id);
		return aracService.fiyatFormatter(((100 - aracService.handleAracDiscount(plaka)) / 100) * kullanimTutari);
	}

	@GetMapping("/otoparktakiAraclar")
	public List<AracResponse> otoparktakiAraclar() {
		return aracServiceImp.isCikisSaatiNull();
	}

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
