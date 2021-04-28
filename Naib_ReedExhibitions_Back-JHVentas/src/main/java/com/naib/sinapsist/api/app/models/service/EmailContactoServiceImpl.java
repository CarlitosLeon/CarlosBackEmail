package com.naib.sinapsist.api.app.models.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.naib.sinapsist.api.app.models.dao.IEmailContactoDao;
import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;
import com.naib.sinapsist.api.app.models.entity.EmailContacto;
import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

@Service
public class EmailContactoServiceImpl implements IEmailContactoService {
	@Autowired
	private IEmailContactoDao EmailContactoDa;	

	
	@Override
	@Transactional(readOnly = true)
	public List<EmailContacto> findAll() {		
		return (List<EmailContacto>)EmailContactoDa.findAll();
	}

	@Override
	public List<EmailContacto> getemailContacto(int ideCon) {
		return EmailContactoDa.getemailContacto(ideCon);
	}
	
	@Override
	public List<ArchivoContacto> getFiles(int idEC) {
		return EmailContactoDa.getFiles(idEC);
	}
	
	@Override
	public List<EmailContacto> getfirmaFile(int idE) {
		return EmailContactoDa.getfirmaFile(idE);
	}

	@Override
	public EmailContacto save(EmailContacto emailContacto) {
		return EmailContactoDa.save(emailContacto);
	}

	@Override
	public EmailContacto findById(int idEc) {
		return EmailContactoDa.findById(idEc);
	}



	@Override
	public void deleteById(int idE) {
		EmailContactoDa.deleteById(idE);
		
	}

	



	



}
