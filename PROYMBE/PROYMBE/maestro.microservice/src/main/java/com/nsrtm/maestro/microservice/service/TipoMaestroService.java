package com.nsrtm.maestro.microservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nsrtm.maestro.microservice.domain.TipoMaestro;
import com.nsrtm.maestro.microservice.repository.TipoMaestroRepository;

@Service
public class TipoMaestroService {
	private static final Logger logger = LogManager.getLogger(TipoMaestroService.class);

	@Autowired
	private TipoMaestroRepository tipoMaestroRepository;

	public TipoMaestro Guardar(TipoMaestro e) {
		try{
			return tipoMaestroRepository.save(e);
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	public void Actualizar(TipoMaestro e){
		try {
			TipoMaestro tipoDb = ObtenerPorId(e.tipoMaestroId);
			tipoDb.nombre = e.nombre;
			Guardar(tipoDb);
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	public void Eliminar(Integer id){
		try{
			tipoMaestroRepository.deleteById(id);
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	public TipoMaestro ObtenerPorId(Integer id) {
		logger.info("OBTENER POR ID"+id);
		try {
			return tipoMaestroRepository.findById(id).get();
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	public List<TipoMaestro> Todos(){
		logger.info("TODOS");
		return tipoMaestroRepository.findAll();
	}

	public TipoMaestro TodosTest(Integer id) {
		try {
			TipoMaestro m =tipoMaestroRepository.findById(id).get();
			return m;
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}
}
