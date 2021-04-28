package com.naib.sinapsist.api.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByEmail(String email);
	
	public Optional<Usuario> findById(Integer id);
	
	@Query("SELECT u FROM Usuario u join u.detalleUsuario du join du.evento e WHERE u.rol.nombre=?1 AND du.status=1 AND e.id=?2 ORDER BY u.id")
	public List<Usuario> findUserEvent(String rol,int idE);
	
	@Query("SELECT u FROM Usuario u join u.detalleUsuario du WHERE u.rol.nombre = 'ROLE_VENTAS' and du.evento.id=?1")
	public List<Usuario> findUserVendedor(int idEv);
}
