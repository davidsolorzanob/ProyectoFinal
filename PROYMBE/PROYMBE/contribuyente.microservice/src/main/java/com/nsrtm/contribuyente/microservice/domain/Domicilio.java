package com.nsrtm.contribuyente.microservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="contribuyente_domicilio", schema = "public")
public class Domicilio extends Auditoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contribuyente__domicilio_id")
    public Long contribuyenteDomicilioId;

    @Column(name = "contribuyente_id")
    public Long contribuyenteId;

    @Column(name = "departamento")
    public String departamento;

    @Column(name = "provincia")
    public String provincia;

    @Column(name = "distrito")
    public String distrito;

    @Column(name = "tipo_domicilio_id")
    public int tipoDomicilioId;

    @Column(name = "tipo_habilitacion_id")
    public int tipoHabilitacionId;

    @Column(name = "nombre_habilitacion")
    public String nombreHabilitacion;

    @Column(name = "tipo_via_id")
    public int tipoViaId;

    @Column(name = "nombre_via")
    public String nombreVia;

    @Column(name = "numero_municipal")
    public String numeroMunicipal;

    @Column(name = "lote_urbano")
    public String loteUrbano;

    @Column(name = "numero_alterno")
    public String numeroAlterno;

    @Column(name = "manzana_urbana")
    public String manzanaUrbana;

    @Column(name = "block")
    public String block;

    @Column(name = "numero_dpto")
    public String numeroDpto;

    @Column(name = "interior")
    public String interior;

    @Column(name = "cuadra")
    public String cuadra;

    @Column(name = "kilometro")
    public String kilometro;

    @Column(name = "referencia")
    public String referencia;

    @Column(name = "telefono")
    public String telefono;

    private static final long serialVersionUID = 1L;
}
