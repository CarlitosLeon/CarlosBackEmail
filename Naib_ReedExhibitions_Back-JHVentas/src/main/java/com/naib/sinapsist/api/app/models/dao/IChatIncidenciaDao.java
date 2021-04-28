package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ChatIncidencia;

public interface IChatIncidenciaDao extends CrudRepository<ChatIncidencia, Integer>{
	
	public ChatIncidencia save(ChatIncidencia chat);
	
	@Query("SELECT c FROM ChatIncidencia c join c.incidencia i Where i.ticket=?1 order by c.registro")
	public List<ChatIncidencia> getConversacion(int ticket);

}
