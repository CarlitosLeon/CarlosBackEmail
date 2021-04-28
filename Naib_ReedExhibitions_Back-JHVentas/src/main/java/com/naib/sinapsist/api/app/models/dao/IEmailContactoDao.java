package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;
import com.naib.sinapsist.api.app.models.entity.EmailContacto;
import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

public interface IEmailContactoDao extends CrudRepository<EmailContacto,Integer>{
	@Query("Select ec FROM EmailContacto ec WHERE ec.contacto.id=?1 ORDER BY ec.id")
	public List<EmailContacto>getemailContacto(int ideCon);
	
	@Query("Select a FROM ArchivoContacto a WHERE a.email.id=?1 ORDER BY a.id")
	public List<ArchivoContacto> getFiles(int idEC);
	
	@Query("Select f FROM EmailContacto f WHERE f.firma.id=?1 ORDER BY f.id")
	public List<EmailContacto> getfirmaFile(int idE);
	
	@SuppressWarnings("unchecked")
	public EmailContacto save(EmailContacto emailContacto);
	
	public EmailContacto findById(int idEc);
	
	public void deleteById(int idE);
}
