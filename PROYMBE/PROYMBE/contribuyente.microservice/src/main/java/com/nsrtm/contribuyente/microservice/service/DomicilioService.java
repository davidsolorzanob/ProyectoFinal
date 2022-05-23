package com.nsrtm.contribuyente.microservice.service;

import com.nsrtm.contribuyente.microservice.domain.Domicilio;
import com.nsrtm.contribuyente.microservice.repository.DomicilioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService {
    private static final Logger logger = LogManager.getLogger(DomicilioService.class);

    @Autowired
    private DomicilioRepository domicilioRepository;

    public void Guardar(Domicilio e) {
        try{
            domicilioRepository.save(e);
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
            throw ex;
        }
    }

    public void Actualizar(Domicilio e){
        try {
            Domicilio domicilio = ObtenerPorId(e.contribuyenteDomicilioId);
            domicilio.contribuyenteId = e.contribuyenteId;
            domicilio.departamento = e.departamento;
            domicilio.provincia = e.provincia;
            domicilio.distrito = e.distrito;
            domicilio.tipoDomicilioId = e.tipoDomicilioId;
            domicilio.tipoHabilitacionId = e.tipoHabilitacionId;
            domicilio.nombreHabilitacion = e.nombreHabilitacion;
            domicilio.tipoViaId = e.tipoViaId;
            domicilio.nombreVia = e.nombreVia;
            domicilio.numeroMunicipal = e.numeroMunicipal;
            domicilio.loteUrbano = e.loteUrbano;
            domicilio.numeroAlterno = e.numeroAlterno;
            domicilio.manzanaUrbana = e.manzanaUrbana;
            domicilio.block = e.block;
            domicilio.numeroDpto = e.numeroDpto;
            domicilio.interior = e.interior;
            domicilio.cuadra = e.cuadra;
            domicilio.kilometro = e.kilometro;
            domicilio.referencia = e.referencia;
            domicilio.telefono = e.telefono;
            Guardar(domicilio);
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
            throw ex;
        }
    }

    public void Eliminar(Long id){
        try{
            domicilioRepository.deleteById(id);
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
            throw ex;
        }
    }

    public Domicilio ObtenerPorId(Long id) {
        try {
            return domicilioRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
            throw ex;
        }
    }

    public List<Domicilio> Todos(){
        return domicilioRepository.findAll();
    }
}
