package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ContactoExpositor;

public interface IContactoExpositorDao extends CrudRepository<ContactoExpositor, Integer> {

	@Query("SELECT ce FROM ContactoExpositor ce WHERE ce.expositor.id=?1 ORDER BY ce.id")
	public List<ContactoExpositor> getContactoExpositor(int idEx);

	public void deleteById(int idC);
	
	public ContactoExpositor findById(int idC);
	
	public ContactoExpositor save(ContactoExpositor contacto);
	
	public List<ContactoExpositor> findAll();


}