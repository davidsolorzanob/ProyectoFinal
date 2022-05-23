package com.nsrtm.maestro.microservice.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Auditoria {

    @Column(name = "usuario_registro")
    public String usuarioRegistro;

    @Column(name = "fecha_registro")
    public Date fechaRegistro;

    @Column(name = "usuario_edicion")
    public String usuarioEdicion;

    @Column(name = "fecha_edicion")
    public Date fechaEdicion;
}
