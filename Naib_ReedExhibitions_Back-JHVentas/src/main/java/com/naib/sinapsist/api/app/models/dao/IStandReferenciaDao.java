/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.RenderDro;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IStandReferenciaDao extends CrudRepository<Stand, Integer> {

	public Stand findById(int id);

	@Query("FROM RenderDro dro WHERE dro.idStandReferencia=?1")
	public List<RenderDro> findDroById(int id);

	@SuppressWarnings("unchecked")
	public Stand save(Stand refe);

	@Transactional
	@Modifying
	@Query("UPDATE Stand st SET st.status='1' WHERE st.id=?1")
	int updateStatusStandEtiquetado(int id);

	@Transactional
	@Modifying
	@Query("UPDATE Stand st SET st.status='2' where st.id=?1")
	int updateStatusStandDetalle(int id);

	@Query("FROM Stand st WHERE st.componente.salon.detalleEvento.evento.id=?1 AND st.componente.status=1 ORDER BY st.componente.numeroStand")
	public List<Stand> findAllById(int id);

	//// Método para obtener el evento del usuario
	@Query("FROM DetalleUsuario dus WHERE dus.status=1 AND dus.usuario.id=?1")
	public DetalleUsuario findDetalleUsuarioById(int id);

	/// Método para obtner a los floor manager del evento
	@Query("FROM DetalleFM dfm WHERE dfm.zona.evento.id=?1 AND dfm.detalleUsuario.usuario.rol.id=1 ORDER BY dfm.zona.id")
	public List<DetalleFM> findFMZonasEventoById(int id);

	public void deleteById(int idS);

	@Query("SELECT count(s) FROM Stand s WHERE s.estatus_asignacion=?1 AND s.componente.salon.detalleEvento.evento.id=?2")
	public int countStatusCrm(String status, int idE);
	
	@Query("SELECT count(s) FROM Stand s WHERE s.expositor.id=?1")
	public int consultaSiTieneStands(int idEx);
}
