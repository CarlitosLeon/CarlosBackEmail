package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.NotasExpositor;

public interface INotasExpositorDao extends CrudRepository<NotasExpositor, Integer> {

	@Query("SELECT ne FROM NotasExpositor ne WHERE ne.expositor.id=?1")
	public List<NotasExpositor> getNotasExpositor(int idE);

	@SuppressWarnings("unchecked")
	public NotasExpositor save(NotasExpositor notas);
}