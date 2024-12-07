package com.example.otopark_yonetim.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.otopark_yonetim.services.AracService;

@Component
public class AracServiceFactory {

	private final AracService ikiTekerliAracService;
	private final AracService otomobilService;
	private final AracService hafifTicariAracService;
	private final AracService agirTicariAracService;

	@Autowired
	public AracServiceFactory(@Qualifier("ikiTekerliAracService") AracService ikiTekerliAracService,
			@Qualifier("otomobilService") AracService otomobilService,
			@Qualifier("hafifTicariAracService") AracService hafifTicariAracService,
			@Qualifier("agirTicariAracService") AracService agirTicariAracService) {
		this.ikiTekerliAracService = ikiTekerliAracService;
		this.otomobilService = otomobilService;
		this.hafifTicariAracService = hafifTicariAracService;
		this.agirTicariAracService = agirTicariAracService;
	}

	public AracService getServiceName(String serviceName) {
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
