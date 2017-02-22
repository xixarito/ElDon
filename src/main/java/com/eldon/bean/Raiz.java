package com.eldon.bean;

import java.util.ArrayList;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Raiz {
	private ArrayList<Servidor> servidor = new ArrayList<Servidor>();
	private ArrayList<Oficio> oficio = new ArrayList<Oficio>();

	public Raiz() {
		this.servidor = null;
		this.oficio = null;
	}

	public ArrayList<Servidor> getServidor() {
		return servidor;
	}

	public void setServidor(ArrayList<Servidor> servidor) {
		this.servidor = servidor;
	}

	public ArrayList<Oficio> getOficio() {
		return oficio;
	}

	public void setOficio(ArrayList<Oficio> oficio) {
		this.oficio = oficio;
	}

}
