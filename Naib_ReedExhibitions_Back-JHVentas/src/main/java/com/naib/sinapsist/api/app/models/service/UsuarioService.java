package com.naib.sinapsist.api.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IUsuarioDao;
import com.naib.sinapsist.api.app.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario dataUser = usuarioDao.findByEmail(email);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(dataUser.getRol().getNombre()));

		return new User(dataUser.getEmail(), dataUser.getPassword(), dataUser.getStatus(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findUserEvent(String rol, int idE) {
		return usuarioDao.findUserEvent(rol, idE);
	}

	@Override
	public List<Usuario> findUserVendedor(int idEv) {
		return usuarioDao.findUserVendedor(idEv);
	}

}
