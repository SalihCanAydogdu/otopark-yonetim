package com.example.otopark_yonetim.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.repositories.AracRepository;

import jakarta.persistence.EntityNotFoundException;

class OtomobilServiceTest {

	@InjectMocks
	private OtomobilService otomobilService;

	@Mock
	private AracRepository aracRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFiyatHesapla_ValidArac() {
		Long aracId = 1L;
		LocalDateTime girisSaati = LocalDateTime.of(2024, 12, 22, 10, 0);
		LocalDateTime cikisSaati = LocalDateTime.of(2024, 12, 22, 12, 0);

		Arac arac = new Arac();
		arac.setId(aracId);
		arac.setGirisSaati(girisSaati);
		arac.setCikisSaati(cikisSaati);

		when(aracRepository.findById(aracId)).thenReturn(Optional.of(arac));

		double fiyat = otomobilService.fiyatHesapla(aracId);

		assertEquals(170.0, fiyat, 0.01); // 20 + (120 dk * 1.25 TL)

	}

	// cagrilan ID ile otomobilin db'de bulunmadigi durum
	@Test
	void testFiyatHesapla_AracNotFound() {

		Long invalidAracId = 99L;
		when(aracRepository.findById(invalidAracId)).thenReturn(Optional.empty());

		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			otomobilService.fiyatHesapla(invalidAracId);
		});

	}

	@Test
	void testFiyatHesapla_NoCikiSaati() {
		Long aracId = 2L;
		LocalDateTime girisSaati = LocalDateTime.of(2024, 12, 12, 12, 12);

		Arac arac = new Arac();
		arac.setId(aracId);
		arac.setGirisSaati(girisSaati);
		arac.setCikisSaati(null); // cikis saatinin eksik oldugu durum

		when(aracRepository.findById(aracId)).thenReturn(Optional.of(arac));

		Exception exception = assertThrows(NullPointerException.class, () -> {
			otomobilService.fiyatHesapla(aracId);
		});

		assertNotNull(exception);
	}

}
