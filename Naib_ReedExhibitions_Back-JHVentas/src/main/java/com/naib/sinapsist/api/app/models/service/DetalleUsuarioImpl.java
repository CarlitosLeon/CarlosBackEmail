package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IDetalleUsuarioDao;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleUsuarioImpl implements IDetalleUsuarioService{

    @Autowired
    private IDetalleUsuarioDao detalleUsuarioDao;

    @Override
    @Transactional(readOnly = true)
    public Integer findIdDetailUser(int id) {
        return detalleUsuarioDao.findIdDetailUser(id);
    }
    
	@Override
	public DetalleUsuario findById(int idDU) {
		return detalleUsuarioDao.findById(idDU);
	}
    
}