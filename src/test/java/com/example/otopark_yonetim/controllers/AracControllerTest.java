package com.example.otopark_yonetim.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.services.AracService;
import com.example.otopark_yonetim.services.AracServiceImp;

public class AracControllerTest {

	@Mock
	private AracService ikiTekerliAracService;

	@Mock
	private AracService otomobilService;

	@Mock
	private AracService agirTicariAracService;

	@Mock
	private AracServiceImp aracServiceImp;

	@InjectMocks
	private AracController aracController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGirisYapti_Success() {
		String plaka = "34ABC123";
		String aracTuru = "Otomobil";

		Arac yeniArac = new Arac();
		yeniArac.setPlaka(plaka);
		yeniArac.setAracTuru(aracTuru);

		when(aracServiceImp.aracGirisYapti(plaka, aracTuru)).thenReturn(yeniArac);

		ResponseEntity<Object> response = aracController.girisYapti(plaka, aracTuru);

		assertEquals(201, response.getStatusCode());
		assertEquals(yeniArac, response.getBody());

	}
	
	
	

}
