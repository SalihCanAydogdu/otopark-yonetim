package com.example.otopark_yonetim_responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AracResponse {

	long id;
	String aracTuru;
	String plaka;
	String girisSaati;

}
