package com.eldon.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	public ArrayList<Servidor> getCategorias(String latitude, String longitud) throws Exception {
		ArrayList<Servidor> resultado = new ArrayList<Servidor>();
		try{
			SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource).withProcedureName(Constantes.SP_CATEGORIA)
					.returningResultSet("lista", new RowMapper<Servidor>() {
						@Override
						public Servidor mapRow(ResultSet rs, int rowNum) throws SQLException {
							Servidor lista = new Servidor();
							lista.setIdServidor(rs.getInt(1));
							lista.setNombre(rs.getString(2));
							lista.setActividad(rs.getString(3));
							lista.setDistancia(rs.getFloat(4));
							return lista;
						}
					});
			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("p_latitud", latitude.trim())
					.addValue("p_longitud", longitud.trim());
			Map<String, Object> m = infoCategoria.execute(in);
			resultado = (ArrayList<Servidor>) m.get("lista");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Oficio> getOficios(String latitude, String longitud, int pagina) throws Exception {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();
		try{
			SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource).withProcedureName(Constantes.SP_OFICIOS)
					.returningResultSet("lista", new RowMapper<Oficio>() {
						@Override
						public Oficio mapRow(ResultSet rs, int rowNum) throws SQLException {
							Oficio lista = new Oficio();
							lista.setIdOficio(rs.getInt(1));
							lista.setOficio(rs.getString(2));
							return lista;
						}
					});
			SqlParameterSource in = new MapSqlParameterSource()
			.addValue("p_latitud", latitude.trim())
			.addValue("p_longitud", longitud.trim())
			.addValue("p_pagina", pagina);
			
			Map<String, Object> m = infoCategoria.execute(in);
			resultado = (ArrayList<Oficio>) m.get("lista");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultado;
	}
	
}
