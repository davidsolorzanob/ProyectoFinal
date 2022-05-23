package com.nsrtm.maestro.microservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="tipo_maestro", schema = "public")
public class TipoMaestro implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipomaestro_id")
	public Integer tipoMaestroId;
	
	@Column(name = "nombre")
	public String nombre;
		
	public TipoMaestro() {
	}
	
	public TipoMaestro(Integer id, String name) {
		this.tipoMaestroId = id;
		this.nombre=name;
	}

	private static final long serialVersionUID = 1L;
}
