package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

public interface IFirmaEmailService {
	
	public List<FirmaEmail> finAll();
	
	public FirmaEmail save(FirmaEmail firmaEmail);
	
	public String subirArchivo(MultipartFile file);
	
	public FirmaEmail findById(int idE);

}
