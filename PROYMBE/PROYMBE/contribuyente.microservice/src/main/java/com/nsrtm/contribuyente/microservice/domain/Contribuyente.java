package com.nsrtm.contribuyente.microservice.domain;

import lombok.ToString;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@ToString
@Table (name="contribuyente", schema = "public")
public class Contribuyente extends Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contribuyente_id")
	public Long contribuyenteId;

	@Column(name = "sec_ejec")
	public String secEjec;

	@Column(name = "tipo_contribuyente_id")
	public Integer tipoContribuyenteId;

	@Column(name = "codigo")
	public String codigo;

	private static final long serialVersionUID = 1L;
}
