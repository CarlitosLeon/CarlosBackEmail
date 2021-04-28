package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;

public interface ICarteraCrmService {

	public List<CarteraEvento> getExpositoresCartera(int id, int idE);

	public List<CarteraEvento> getAllExpositoresCartera(int idE);

	public CarteraEvento findById(int id);
	
	public List<CarteraEvento> getRelacionVendedor(int idEx);
	
	public void deleteById(int idCa);
	
	public CarteraEvento save(CarteraEvento cartera);

}
