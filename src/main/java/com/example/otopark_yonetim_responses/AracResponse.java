package com.example.otopark_yonetim_responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AracResponse {

	long id;
	String plaka;
	String aracTuru;
	LocalDateTime girisSaati;
	
	
}
