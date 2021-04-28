package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.NotasExpositor;

public interface INotasExpositorService {

	public List<NotasExpositor> getNotasExpositor(int idE);

	public NotasExpositor save(NotasExpositor notas);

}
