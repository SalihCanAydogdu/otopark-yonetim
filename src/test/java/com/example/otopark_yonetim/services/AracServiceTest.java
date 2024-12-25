package com.example.otopark_yonetim.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.otopark_yonetim.entities.Arac;
import com.example.otopark_yonetim.repositories.AracRepository;
import com.example.otopark_yonetim_responses.AracResponse;

class AracServiceTest {

	@Mock
	private AracRepository aracRepository;

	@InjectMocks
	private AracService aracService = new AracService(aracRepository) {
		@Override
		public double fiyatHesapla(Long id) {
			// Test için dummy bir hesaplama
			return 100.0;
		}
	};

	private Arac testArac;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		testArac = Arac.builder().id(1L).plaka("34TEST123").aracTuru("Otomobil")
				.girisSaati(LocalDateTime.now().minusHours(2)).cikisSaati(null).icerde(true).build();
	}

	@Test
	void testAracGirisYapti() {
		when(aracRepository.save(any(Arac.class))).thenReturn(testArac);

		Arac savedArac = aracService.aracGirisYapti("34TEST123", "Otomobil");

		assertNotNull(savedArac);
		assertEquals("34TEST123", savedArac.getPlaka());
		assertEquals("Otomobil", savedArac.getAracTuru());
		assertTrue(savedArac.isIcerde());
		verify(aracRepository, times(1)).save(any(Arac.class));
	}

	@Test
	void testAracCikisSaatiSet() {
		when(aracRepository.findById(1L)).thenReturn(Optional.of(testArac));
		when(aracRepository.save(any(Arac.class))).thenReturn(testArac);

		Arac updatedArac = aracService.aracCikisSaatiSet(1L);

		verify(aracRepository, times(1)).findById(1L);
		verify(aracRepository, times(1)).save(any(Arac.class));
		assertNotNull(updatedArac.getCikisSaati());

	}

	@Test
	void testHesaplaDakikaFarki() {
		LocalDateTime giris = LocalDateTime.now().minusHours(2);
		LocalDateTime cikis = LocalDateTime.now();

		double dakikaFarki = aracService.hesaplaDakikaFarki(giris, cikis);

		assertEquals(120.0, dakikaFarki, 0.1);
	}

	@Test
	void testFiyatFormatter() {
		double fiyat = 1234.56;

		String formattedFiyat = aracService.fiyatFormatter(fiyat);

		assertEquals("1,234.56", formattedFiyat);
	}

	@Test
	void testHandleAracDiscount() {
		when(aracRepository.countByPlakaIgnoreCase("34TEST123")).thenReturn(10L);

		double discount = aracService.handleAracDiscount("34TEST123");

		assertEquals(5, discount);
		verify(aracRepository, times(1)).countByPlakaIgnoreCase("34TEST123");
	}

	@Test
	void testIsIcerdeTrue() {
		when(aracRepository.findByIcerdeIsTrue()).thenReturn(Arrays.asList(testArac));

		List<AracResponse> aracResponses = aracService.isIcerdeTrue();

		assertNotNull(aracResponses);
		assertEquals(1, aracResponses.size());
		assertEquals("34TEST123", aracResponses.get(0).getPlaka());
		verify(aracRepository, times(1)).findByIcerdeIsTrue();
	}

	@Test
	void testAracCikisYapti() {
		when(aracRepository.findById(1L)).thenReturn(Optional.of(testArac));
		when(aracRepository.save(any(Arac.class))).thenReturn(testArac);

		Arac updatedArac = aracService.aracCikisYapti(1L);

		assertFalse(updatedArac.isIcerde());
		verify(aracRepository, times(1)).findById(1L);
		verify(aracRepository, times(1)).save(any(Arac.class));
	}
}
