package com.nsrtm.maestro.microservice.service;

import java.util.List;


import com.nsrtm.maestro.microservice.config.CacheConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nsrtm.maestro.microservice.domain.Maestro;
import com.nsrtm.maestro.microservice.repository.MaestroRepository;

@Service
public class MaestroService {

	private static final Logger logger = LogManager.getLogger(MaestroService.class);

	@Autowired
	private MaestroRepository maestroRepository;

	@CacheEvict(cacheNames = CacheConfig.MAESTRO_CACHE, allEntries = true)
	public Maestro Guardar(Maestro e) {
		return this.maestroRepository.save(e);
	}

 	@CacheEvict(cacheNames = CacheConfig.MAESTRO_CACHE, allEntries = true)
	public Maestro Actualizar(Maestro e){
		Maestro maestroUpdate = ObtenerPorId(e.maestroId);
		maestroUpdate.nombre = e.nombre;
		Maestro maestro = maestroRepository.save(maestroUpdate);
		return maestro.maestroId.longValue()!=0 ? this.maestroRepository.findById(e.maestroId).orElse(null):null;
	}

	@Caching(evict = { @CacheEvict(cacheNames = CacheConfig.MAESTRO_CACHE, key = "#id"),
			@CacheEvict(cacheNames = CacheConfig.MAESTRO_CACHE, allEntries = true) })
	public void Eliminar(Integer id){
		try {
			logger.info("ID "+id);
			maestroRepository.deleteById(id);
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	@Cacheable(cacheNames = CacheConfig.MAESTRO_CACHE, key = "#id", unless = "#result == null")
	public Maestro ObtenerPorId (Integer id) {
		logger.info("OBTENER POR ID"+id);
		try{
			return this.maestroRepository.findById(id).get();
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

 	@Cacheable(cacheNames = CacheConfig.MAESTRO_CACHE)
	public List<Maestro> ObtenerPorFiltro (Maestro e) {
		logger.info("OBTENER POR FILTRO");
		try{
			Maestro m = Maestro.builder().tipoMaestroId(e.tipoMaestroId).build();
			return maestroRepository.findAll(Example.of(m));
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

 	@Cacheable(cacheNames = CacheConfig.MAESTRO_CACHE)
	public List<Maestro> Todos(){
		logger.info("TODOS");
		return maestroRepository.findAll();
	}
}
