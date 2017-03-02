package com.eldon.rest;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eldon.bean.Oficio;
import com.eldon.bean.Raiz;
import com.eldon.bean.Servidor;
import com.eldon.dao.ObtieneInformacionDAO;
import com.eldon.util.Util;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.MultiPart;

@Path("/service")
public class JSONService {
	private static Logger log = Logger.getLogger(JSONService.class);
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	ObtieneInformacionDAO info = (ObtieneInformacionDAO) context
			.getBean("obtieneInformacionDAO");

	Raiz respuesta = new Raiz();

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
	@Path("/oficio/{latitud}/{longitud}/{pagina}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Raiz getOficios(@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud,
			@PathParam("pagina") String pagina) {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		try {
			resultado = info.getOficios(latitud, longitud, pagina);
		} catch (Exception e) {
			log.error("getOficios: " + e);
		}

		respuesta.setOficio(resultado);

		return respuesta;
	}

	@GET
	@Path("/oficio/{latitud}/{longitud}/{clave}/{pagina}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Raiz getOficios(@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud,
			@PathParam("clave") String clave, @PathParam("pagina") String pagina) {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		try {
			resultado = info.getOficios(latitud, longitud, clave, pagina);
		} catch (Exception e) {
			log.error(e);
		}

		respuesta.setOficio(resultado);

		return respuesta;
	}

	@GET
	@Path("/servidorOficio/{latitud}/{longitud}/{oficio}/{pagina}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Raiz getServidorOficio(@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud,
			@PathParam("oficio") String oficio,
			@PathParam("pagina") String pagina) {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();

		try {
			resultado = info.getServidorOficio(latitud, longitud, oficio,
					pagina);
		} catch (Exception e) {
			log.error(e);
		}

		respuesta.setServidor(resultado);

		return respuesta;
	}

	@GET
	@Path("/actividadServidor/{servidor}/{latitud}/{longitud}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Raiz getActividadServidor(@PathParam("servidor") String servidor,
			@PathParam("latitud") String latitud,
			@PathParam("longitud") String longitud) {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();

		try {
			resultado = info.getActividadServidor(servidor, latitud, longitud);
		} catch (Exception e) {
			log.error(e);
		}

		respuesta.setServidor(resultado);

		return respuesta;
	}

	@POST
	@Path("/altaServidor")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response altaServidor(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("idServidor") String idServidor,
			@FormDataParam("idOficio") String idOficio,
			@FormDataParam("nombre") String nombre,
			@FormDataParam("fecNacimiento") String fecNacimiento,
			@FormDataParam("telefono") String telefono,
			@FormDataParam("latitud") String latitud,
			@FormDataParam("longitud") String longitud,
			@FormDataParam("actividad") String actividad,
			@FormDataParam("experiencia") String experiencia,
			@FormDataParam("datoAdicional") String datoAdicional) {

		String resultado = "Error";
		//Image imagen = null;

		System.out.println(nombre + " " + fecNacimiento + " " + telefono + " "
				+ latitud + " " + longitud + " " + actividad + " "
				+ experiencia + " " + datoAdicional);

		try {
			//imagen = ImageIO.read(fileInputStream);
			resultado = info.altaServidor(idServidor, idOficio, nombre,
					fecNacimiento, telefono, latitud, longitud, actividad,
					experiencia, datoAdicional, fileInputStream);
		} catch (Exception e) {
			log.error(e);
		}

		//JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(imagen))); Mostrar imagen

		return Response.status(200).entity(resultado).build();

	}
}