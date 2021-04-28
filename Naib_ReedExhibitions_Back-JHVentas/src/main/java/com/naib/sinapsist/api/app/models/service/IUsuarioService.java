package com.naib.sinapsist.api.app.models.service;


import java.util.List;

import com.naib.sinapsist.api.app.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByEmail(String email);
	
	public Usuario findById(Integer id);
	
	public List<Usuario> findUserEvent(String rol,int idE);
	
	public List<Usuario> findUserVendedor(int idEv);
}
