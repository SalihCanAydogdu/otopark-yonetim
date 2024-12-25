package com.example.otopark_yonetim.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.services.AracService;
import com.example.otopark_yonetim.services.AracServiceImp;
import com.example.otopark_yonetim_responses.AracResponse;

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

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(yeniArac, response.getBody());

	}

	@Test
	void testGirisYapti_BadRequest() {
		String plaka = " ";
		String aracTuru = "Otomobil";

		ResponseEntity<Object> response = aracController.girisYapti(plaka, aracTuru);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Plaka boş olamaz!", response.getBody());
	}

	@Test
	void testOtoparktakiAraclar() {
		List<AracResponse> aracList = Arrays.asList(
				new AracResponse(1L, "34TEST60", "Otomobil",
						AracServiceImp.formatToTurkish(LocalDateTime.now().minusHours(2))),
				new AracResponse(2L, "60TEST34", "Otomobil",
						AracServiceImp.formatToTurkish(LocalDateTime.now().minusHours(2))));

		when(aracServiceImp.isIcerdeTrue()).thenReturn(aracList);

		List<AracResponse> result = aracController.otoparktakiAraclar();

		// performans amaciyla ilk satiri ekledim
		assertEquals(aracList.size(), result.size());
		assertEquals(aracList, result);
	}

}
