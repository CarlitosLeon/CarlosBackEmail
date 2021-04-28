package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;

public interface IArchivoContactoDao extends CrudRepository<ArchivoContacto, Integer> {
	@Query("Select ac FROM ArchivoContacto ac WHERE ac.email.id=?1 ORDER BY ac.id")
	public List<ArchivoContacto>getarchivoContacto(int idaC);
	
	public ArchivoContacto save(ArchivoContacto archivoContacto);
}
