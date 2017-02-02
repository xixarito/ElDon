package com.eldon.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eldon.bean.Oficio;
import com.eldon.bean.Servidor;
import com.eldon.dao.ObtieneInformacionDAO;

@Path("/service")
public class JSONService {
	private static Logger log = Logger.getLogger(JSONService.class);
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	ObtieneInformacionDAO info = (ObtieneInformacionDAO) context
			.getBean("obtieneInformacionDAO");

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String isEnable() {
		String resultado;

		try {
			resultado = "Servicio activo";
		} catch (Exception e) {
			resultado = "Servicio Inactivo";
			log.error(e);
		}

		return resultado;
	}
	
	@GET
	@Path("/oficio/{latitud}/{longitud}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Oficio> getOficios(
			@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud) {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		try {
			resultado = info.getOficios(latitud, longitud);
		} catch (Exception e) {
			log.error(e);
		}

		return resultado;
	}
	
	@GET
	@Path("/buscar/{latitud}/{longitud}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Servidor> getCategorias(
			@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud) {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();

		try {
			resultado = info.getCategorias(latitud, longitud);
		} catch (Exception e) {
			log.error(e);
		}

		return resultado;
	}
}