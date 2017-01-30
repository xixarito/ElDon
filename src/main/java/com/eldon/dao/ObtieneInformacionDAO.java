package com.eldon.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.eldon.bean.Categoria;
import com.eldon.bean.Oficio;
import com.eldon.util.Constantes;
import com.eldon.util.Util;

public class ObtieneInformacionDAO extends AccesoJDBCBaseDAO {
	
	Util formatea = new Util();
	
	@SuppressWarnings("unchecked")
	public ArrayList<Categoria> getCategorias(String latitude, String longitud) throws Exception {
		ArrayList<Categoria> resultado = new ArrayList<Categoria>();
		try{
			SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource).withProcedureName(Constantes.SP_CATEGORIA)
					.returningResultSet("lista", new RowMapper<Categoria>() {
						@Override
						public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
							Categoria lista = new Categoria();
							lista.setIdCategoria(rs.getInt("categoria_id"));
							lista.setCategoria(rs.getString("categoria"));
							return lista;
						}
					});
			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("latitud", latitude.trim())
					.addValue("longitud", longitud.trim());
			Map<String, Object> m = infoCategoria.execute(in);
			resultado = (ArrayList<Categoria>) m.get("lista");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Oficio> getOficios(String latitude, String longitud, String categoria) throws Exception {
		ArrayList<Oficio> resultado = new ArrayList<Oficio>();
		try{
			SimpleJdbcCall infoCategoria = new SimpleJdbcCall(dataSource).withProcedureName(Constantes.SP_OFICIOXCAT)
					.returningResultSet("lista", new RowMapper<Oficio>() {
						@Override
						public Oficio mapRow(ResultSet rs, int rowNum) throws SQLException {
							Oficio lista = new Oficio();
							lista.setIdOficio(rs.getInt("oficio_id"));
							lista.setOficio(rs.getString("oficio"));
							return lista;
						}
					});
			SqlParameterSource in = new MapSqlParameterSource()
			.addValue("latitud", latitude.trim())
			.addValue("longitud", longitud.trim())
			.addValue("categoria_id", categoria.trim());
			
			Map<String, Object> m = infoCategoria.execute(in);
			resultado = (ArrayList<Oficio>) m.get("lista");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultado;
	}
	
}
