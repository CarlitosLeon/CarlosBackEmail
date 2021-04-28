package com.naib.sinapsist.api.app.models.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

public interface IFirmaEmailDao extends CrudRepository<FirmaEmail,Integer> {
	
	public List<FirmaEmail> findAll();	
	
	public FirmaEmail findById(int idE);
	
	public FirmaEmail save(FirmaEmail firmaEmail);
	
	

}
