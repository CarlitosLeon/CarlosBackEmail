package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.Salon;

public interface ISalonService {
	
	public List<Salon> findSalonEvento(int idE);
	
	public Salon findById(int idS);
}
