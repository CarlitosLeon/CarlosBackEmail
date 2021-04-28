package com.naib.sinapsist.api.app.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.findByEmail(authentication.getName());
		
		Map<String, Object> data = new HashMap<>();
		data.put("id", usuario.getId());
		data.put("name", usuario.getNombre());
		data.put("surname", usuario.getaPaterno());
		data.put("tel", usuario.getTelefono());
		
	
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(data);
				
		return accessToken;
	}

}
