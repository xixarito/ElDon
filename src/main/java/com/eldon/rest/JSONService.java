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

import com.eldon.bean.Categoria;
import com.eldon.bean.Oficio;
import com.eldon.dao.ObtieneInformacionDAO;
	
@Path("/service")
public class JSONService {
	private static Logger log = Logger.getLogger(JSONService.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ObtieneInformacionDAO info = (ObtieneInformacionDAO) context.getBean("obtieneInformacionDAO");
	
	@GET
	@Path("/buscar/{latitud}/{longitud}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Categoria> getCategorias(@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud) {
		ArrayList<Categoria> resultado = new ArrayList<Categoria>();

		try {
			resultado = info.getCategorias(latitud, longitud);
		} catch (Exception e) {
			log.error(e);
		}
		
		return resultado;
	}
	
	@GET
	@Path("/buscar/{latitud}/{longitud}/{categoria}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Oficio> getOficios(@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud, @PathParam("categoria") String categoria) {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		try {
			resultado = info.getOficios(latitud, longitud, categoria);
		} catch (Exception e) {
			log.error(e);
		}
		
		return resultado;
	}

}