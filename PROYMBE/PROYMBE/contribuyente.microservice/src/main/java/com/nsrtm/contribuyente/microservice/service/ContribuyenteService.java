package com.nsrtm.contribuyente.microservice.service;

import java.util.List;
import java.util.Locale;

import com.nsrtm.contribuyente.microservice.config.CacheConfig;
import com.nsrtm.contribuyente.microservice.domain.Domicilio;
import com.nsrtm.contribuyente.microservice.util.ResponseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.nsrtm.contribuyente.microservice.domain.Contribuyente;
import com.nsrtm.contribuyente.microservice.repository.ContribuyenteRepository;

@Service
public class ContribuyenteService {
	private static final Logger logger = LogManager.getLogger(ContribuyenteService.class);

	@Autowired
	private ContribuyenteRepository contribuyenteRepository;

	@Autowired
	public ContribuyenteService(ContribuyenteRepository contribuyenteRepository) {
		this.contribuyenteRepository = contribuyenteRepository;
	}


	@CachePut(cacheNames = CacheConfig.CONTRIBUYENTE_CACHE, key = "#e", unless = "#result == null")
	public Contribuyente Guardar(Contribuyente e) {
		Contribuyente res = contribuyenteRepository.save(e);
		return res;
	}

	@CachePut(cacheNames = CacheConfig.CONTRIBUYENTE_CACHE, key = "#e.contribuyenteId",unless = "#result == null")
	public Contribuyente Actualizar(Contribuyente e){
			Contribuyente contribuyente = ObtenerPorId(e.contribuyenteId);
			contribuyente.codigo = e.codigo;
			contribuyente.contribuyenteId = e.contribuyenteId;
			contribuyente.secEjec = e.secEjec;
			contribuyente.tipoDocumento = e.tipoDocumento;
			contribuyente.tipoContribuyenteId = e.tipoContribuyenteId;
			contribuyente.numeroDocumento = e.numeroDocumento;
			contribuyente.apellidoPaterno = e.apellidoPaterno;
			contribuyente.apellidoMaterno = e.apellidoMaterno;
			contribuyente.nombres = e.nombres;
			contribuyente.razonSocial = e.razonSocial;
			contribuyente.celular1 = e.celular1;
			contribuyente.celular2 = e.celular2;
			contribuyente.correoElectronico1 = e.correoElectronico1;
			contribuyente.correoElectronico2 = e.correoElectronico2;

			Contribuyente res = this.contribuyenteRepository.save(contribuyente);
			return res;
	}

	@CacheEvict(value="contribuyente-cache",allEntries=true)//Clear all caches after method invocation
	public void Eliminar(Long id){
		logger.info("ELiminar id "+id);
		try{
			this.contribuyenteRepository.deleteById(id);
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	@Cacheable(cacheNames = CacheConfig.CONTRIBUYENTE_CACHE, unless = "#result == null")
	public Contribuyente ObtenerPorId(Long id) {
		try {
			return contribuyenteRepository.findById(id).get();
		}
		catch (Exception ex){
			logger.info(ex.getMessage());
			throw ex;
		}
	}

	@Cacheable(cacheNames = CacheConfig.CONTRIBUYENTE_CACHE, unless = "#result == null")
	public List<Contribuyente> Todos(){
		return contribuyenteRepository.findAll();
	}

	private static Specification<Contribuyente> getByApellidoPaterno(String apellidoPaterno) {
		if(apellidoPaterno == null) return null;
		if(apellidoPaterno.isEmpty() || apellidoPaterno.isBlank()) return null;
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("apellidoPaterno")), "%" + apellidoPaterno.toUpperCase(Locale.ROOT) + "%");
	}

	private static Specification<Contribuyente> getByApellidoMaterno(String apellidoMaterno) {
		if(apellidoMaterno == null) return null;
		if(apellidoMaterno.isEmpty() || apellidoMaterno.isBlank()) return null;
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("apellidoMaterno")), "%" + apellidoMaterno.toUpperCase(Locale.ROOT) + "%");
	}

	private static Specification<Contribuyente> getByNombres(String nombres) {
		if(nombres == null) return null;
		if(nombres.isEmpty() || nombres.isBlank()) return null;
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nombres")),"%" + nombres.toUpperCase(Locale.ROOT) + "%");
	}

	private static Specification<Contribuyente> getByCodigo(String codigo) {
		if(codigo == null) return null;
		if(codigo.isEmpty() || codigo.isBlank()) return null;
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("codigo"), codigo);
	}

	@Cacheable(cacheNames = CacheConfig.CONTRIBUYENTE_CACHE, unless = "#result == null")
	public List<Contribuyente> ListarPorFiltros(Contribuyente e){
		Specification<Contribuyente> spec = null;
		Specification<Contribuyente> specTemp = getByApellidoPaterno(e.apellidoPaterno);
		specTemp = specTemp==null ? getByApellidoMaterno(e.apellidoMaterno):specTemp.and(getByApellidoMaterno(e.apellidoMaterno));
		specTemp = specTemp==null ? getByNombres(e.nombres):specTemp.and(getByNombres(e.nombres));
		specTemp = specTemp==null ? getByCodigo(e.codigo):specTemp.and(getByCodigo(e.codigo));
		spec = Specification.where(specTemp);
		if(spec == null)
			return contribuyenteRepository.findAll();
		return contribuyenteRepository.findAll(spec);
	}
}
