package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;

public interface IDetalleUsuarioService {

	public Integer findIdDetailUser(int id);
	
	public DetalleUsuario findById(int idDU);
}