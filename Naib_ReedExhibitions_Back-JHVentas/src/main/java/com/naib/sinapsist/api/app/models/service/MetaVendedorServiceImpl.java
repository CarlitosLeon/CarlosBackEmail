package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IMetaVendedorDao;
import com.naib.sinapsist.api.app.models.entity.MetaVendedor;

@Service
public class MetaVendedorServiceImpl implements IMetaVendedorService{
	
	@Autowired
	private IMetaVendedorDao metaVendedorDAO;

	@Override
	@Transactional
	public List<MetaVendedor> getEventoVendedor(int idU) {
		return metaVendedorDAO.getEventoVendedor(idU);
	}

}
