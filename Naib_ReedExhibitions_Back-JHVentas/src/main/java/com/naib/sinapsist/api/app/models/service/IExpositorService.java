package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.Expositor;

public interface IExpositorService {

	public List<Expositor> findAll();

	public Expositor findById(int idExp);

	public Expositor save(Expositor expositor);
	
	public void deleteById(int id);
	
	public String subirImgExpositor(MultipartFile file);
	
	public String delteImgExpositor(String nombreFoto);
	
	public Integer consRfc(String rfc);

}
