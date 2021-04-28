package com.naib.sinapsist.api.app.auth;

import java.util.Arrays;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/detalleExpositor/showEvidenciaArmado/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/api/detalleExpositor/showEvidenciaServicio/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/chat/getMessage/img/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/ventasProspectos/getImgContacto/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/ventasProspectos/getImgExpositor/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/ventasProspectos/getImgFirma/**","/images/**").permitAll()
                        .antMatchers(HttpMethod.PUT,"/api/detalleExpositor/evidencias/porcentajeArmado/{porcentajeArmado}").hasAnyRole("FM","ADMINFM")
                        .antMatchers(HttpMethod.PUT,"/api/detalleExpositor/statusServicio").hasAnyRole("FM","ADMINFM")
                        .antMatchers(HttpMethod.PUT,"/api/detalleExpositor/incidenciasStand/{id}").hasAnyRole("FM","ADMINFM")
                        .antMatchers(HttpMethod.PUT,"/api/detalleExpositor/incidenciasStand/status").hasAnyRole("FM","ADMINFM")
                        .antMatchers(HttpMethod.GET,"/api/detalleExpositor/{id}").hasAnyRole("FM","ADMINFM")
                        .antMatchers(HttpMethod.POST,"/api/listaExpositores/getListaExpositores").permitAll()
                        .antMatchers(HttpMethod.PUT,"/api/listaExpositores/updateStatusStand").permitAll()
                        .antMatchers(HttpMethod.POST,"/public/**").permitAll()
                        .anyRequest().authenticated().and().cors()
				.configurationSource(corsConfigurationSource());
	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200","*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
