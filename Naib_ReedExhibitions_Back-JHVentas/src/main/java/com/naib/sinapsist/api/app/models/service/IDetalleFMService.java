package com.naib.sinapsist.api.app.models.service;

import java.util.Date;
import java.util.List;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;

public interface IDetalleFMService {

	public List<DetalleFM> getFMEvento(int idE);
}
