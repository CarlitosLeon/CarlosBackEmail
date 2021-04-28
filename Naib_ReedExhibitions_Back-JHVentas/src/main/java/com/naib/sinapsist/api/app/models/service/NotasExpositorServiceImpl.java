package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.INotasExpositorDao;
import com.naib.sinapsist.api.app.models.entity.NotasExpositor;

@Service
public class NotasExpositorServiceImpl implements INotasExpositorService {

	@Autowired
	private INotasExpositorDao notasExpositorDao;

	@Override
	@Transactional(readOnly = true)
	public List<NotasExpositor> getNotasExpositor(int idE) {
		return notasExpositorDao.getNotasExpositor(idE);
	}

	@Override
	@Transactional
	public NotasExpositor save(NotasExpositor notas) {
		return notasExpositorDao.save(notas);
	}

}