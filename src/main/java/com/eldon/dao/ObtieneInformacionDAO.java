package com.eldon.dao;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.eldon.bean.Oficio;
import com.eldon.bean.Servidor;
import com.eldon.util.Constantes;
import com.eldon.util.Util;

public class ObtieneInformacionDAO extends AccesoJDBCBaseDAO {

	Util formatea = new Util();

	@SuppressWarnings("unchecked")
	public ArrayList<Oficio> getOficios(String latitude, String longitud,
			String pagina) throws Exception {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource)
				.withProcedureName(Constantes.SP_CONSULTA).returningResultSet(
						"lista", new RowMapper<Oficio>() {
							@Override
							public Oficio mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Oficio lista = new Oficio();
								lista.setIdOficio(rs.getInt(1));
								lista.setOficio(rs.getString(2));
								return lista;
							}
						});
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("p_latitud", latitude.trim())
				.addValue("p_longitud", longitud.trim())
				.addValue("p_pagina", pagina.trim());

		Map<String, Object> m = infoCategoria.execute(in);
		resultado = (ArrayList<Oficio>) m.get("lista");

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Oficio> getOficios(String latitude, String longitud,
			String clave, String pagina) throws Exception {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();

		SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource)
				.withProcedureName(Constantes.SP_BUSQUEDA).returningResultSet(
						"lista", new RowMapper<Oficio>() {
							@Override
							public Oficio mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Oficio lista = new Oficio();
								lista.setIdOficio(rs.getInt(1));
								lista.setOficio(rs.getString(2));
								return lista;
							}
						});
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("p_latitud", latitude.trim())
				.addValue("p_longitud", longitud.trim())
				.addValue("p_clave", clave.trim())
				.addValue("p_pagina", pagina.trim());

		Map<String, Object> m = infoCategoria.execute(in);
		resultado = (ArrayList<Oficio>) m.get("lista");

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Servidor> getServidorOficio(String latitud,
			String longitud, String oficio, String pagina) throws Exception {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();

		SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource)
				.withProcedureName(Constantes.SP_SERVIDOR).returningResultSet(
						"lista", new RowMapper<Servidor>() {
							@Override
							public Servidor mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Servidor lista = new Servidor();
								lista.setIdServidor(rs.getInt(1));
								// lista.setFoto((Blob) rs.getBlob(2));
								lista.setValoracion(rs.getInt(3));
								lista.setNombre(rs.getString(4));
								lista.setEdad(rs.getString(5));
								lista.setTelefono(rs.getString(6));
								lista.setActividad(rs.getString(7));
								lista.setDistancia(rs.getString(8));
								return lista;
							}
						});
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("p_latitud", latitud.trim())
				.addValue("p_longitud", longitud.trim())
				.addValue("p_id_oficio", oficio.trim())
				.addValue("p_pagina", pagina.trim());

		Map<String, Object> m = infoCategoria.execute(in);
		resultado = (ArrayList<Servidor>) m.get("lista");

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Servidor> getActividadServidor(String servidor,
			String latitud, String longitud) throws Exception {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();

		SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource)
				.withProcedureName(Constantes.SP_ACIVIDAD).returningResultSet(
						"lista", new RowMapper<Servidor>() {
							@Override
							public Servidor mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Servidor lista = new Servidor();
								// lista.setFoto((Blob) rs.getBlob(1));
								lista.setNombre(rs.getString(2));
								lista.setTelefono(rs.getString(3));
								lista.setEdad(rs.getString(4));
								lista.setDistancia(rs.getString(5));
								lista.setValoracion(rs.getInt(6));
								lista.setActividad(rs.getString(7));
								lista.setExperiencia(rs.getString(8));
								lista.setAdicional(rs.getString(9));
								return lista;
							}
						});
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("p_id_servidor", servidor.trim())
				.addValue("p_latitud", latitud.trim())
				.addValue("p_longitud", longitud.trim());

		Map<String, Object> m = infoCategoria.execute(in);
		resultado = (ArrayList<Servidor>) m.get("lista");

		return resultado;
	}

	public String altaServidor(String idServidor, String idOficio,
			String nombre, String fecNacimiento, String telefono,
			String latitud, String longitud, String actividad,
			String experiencia, String datoAdicional, InputStream imagen)
			throws Exception {
		String resultado;

		SimpleJdbcCall altaServidor = new SimpleJdbcCall(dataSource)
				.withProcedureName(Constantes.SP_ALTA).returningResultSet(
						"respuesta", new RowMapper<String>() {
							@Override
							public String mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								System.out.println(rs.getString(0));
								return rs.getString(1);
							}
						});
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("p_id_servidor", idServidor.trim())
				.addValue("p_id_oficio", idOficio.trim())
				.addValue("p_latitud", latitud.trim())
				.addValue("p_longitud", longitud.trim())
				.addValue("p_foto", formatea.convierteImagen(imagen),
						Types.BLOB).addValue("p_nombre", nombre.trim())
				.addValue("p_telefono", telefono.trim())
				.addValue("p_fec_nacimiento", fecNacimiento.trim())
				.addValue("p_actividad", actividad.trim())
				.addValue("p_experiencia", latitud.trim())
				.addValue("p_latitud", experiencia.trim())
				.addValue("p_adicional", datoAdicional.trim());

		Map<String, Object> m = altaServidor.execute(in);
		resultado = (String) m.get("respuesta");

		return resultado;
	}

}
