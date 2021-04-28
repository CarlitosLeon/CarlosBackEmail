package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Incidencia;

public interface IIncidenciaDao extends CrudRepository<Incidencia, Integer> {

	@Query("SELECT i FROM Incidencia i join i.detalleReferencia ir join ir.stand s join s.componente co join co.salon sa join sa.detalleEvento de join de.evento e WHERE e.id=?1 AND i.tipoIncidencia='1' ORDER BY i.ticket")
	public List<Incidencia> incidenciasReferenciadas(int id);

	@Query("SELECT i FROM Incidencia i WHERE i.idDetalleUsuario.id=?1 AND i.tipoIncidencia=?2 ORDER BY i.ticket")
	public List<Incidencia> incidenciaFME(int idDU, String tipoIncidencia);

	public Incidencia findById(int idI);

	public Incidencia save(Incidencia incidencia);

	@Query("SELECT i FROM Incidencia i join i.detalleGeneral dg join dg.salon s join s.detalleEvento de join de.evento e Where e.id=?1 AND i.tipoIncidencia='2' ORDER BY i.ticket")
	public List<Incidencia> incidenciaGeneral(int id);

	@Query("SELECT i FROM Incidencia i join i.detalleGeneral dg join dg.salon s WHERE s.id=?1 AND i.tipoIncidencia='2' ORDER BY i.ticket")
	public List<Incidencia> incidenciaGeneralSalon(int idS);

	@Query("SELECT COUNT(i) FROM Incidencia i WHERE i.idDetalleUsuario.id=?1")
	public Integer countTotalIncidencias(int idDu);

	@Query("SELECT i FROM Incidencia i WHERE i.idDetalleUsuario.id=?1")
	public List<Incidencia> todasIncidencias(int idDu);
	
	
}
