package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IComponenteDao extends CrudRepository<Componente, Integer> {

	@Query("FROM Stand s WHERE s.componente.salon.detalleEvento.evento.id=?1 AND s.componente.status=1 AND s.estatus_asignacion!='4' AND s.estatus_asignacion!='5'")
	public List<Stand> findStandById(int id);

	@Query("FROM Componente c WHERE c.salon.detalleEvento.evento.id=?1 AND c.status=1 ORDER BY c.id")
	public List<Componente> findComponenteById(int id);

	@Query("FROM CarteraEvento ce WHERE ce.evento.id=?1 AND ce.expositor.estatusCrm>=2 Order By ce.id")
	public List<CarteraEvento> findCarteraEventoById(int id);

	@Transactional
	@Modifying
	@Query("UPDATE CarteraEvento st SET st.reubicacion=?1 where st.id=?2")
	int updateStatusExpositorReubicacion(boolean estatus, int id);

	public Componente findById(int idCom);

}
