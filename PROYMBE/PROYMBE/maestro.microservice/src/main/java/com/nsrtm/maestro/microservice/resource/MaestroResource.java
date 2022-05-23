package com.nsrtm.maestro.microservice.resource;

import java.util.LinkedList;
import java.util.List;

import com.nsrtm.maestro.microservice.domain.TipoMaestro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.nsrtm.maestro.microservice.domain.Maestro;
import com.nsrtm.maestro.microservice.service.MaestroService;

@RestController
@RequestMapping("/api/maestro")
public class MaestroResource {
	@Autowired
	private MaestroService maestroService;

	@PostMapping("crear")
	@ResponseStatus(HttpStatus.OK)
	public Maestro Crear(@RequestBody Maestro e) {
		return maestroService.Guardar(e);
	}

	@PostMapping("editar")
	@ResponseStatus(HttpStatus.OK)
	public Maestro Editar(@RequestBody Maestro e) {
		return maestroService.Actualizar(e);
	}

	@PostMapping("eliminar")
	@ResponseStatus(HttpStatus.OK)
	public void Eliminar(Integer id) {
		maestroService.Eliminar(id);
	}

	@GetMapping("obtener")
	@ResponseStatus(HttpStatus.OK)
	public Maestro ObtenerPorId(Integer id) {
		return maestroService.ObtenerPorId(id);
	}

	@PostMapping("filtrar")
	@ResponseStatus(HttpStatus.OK)
	public List<Maestro> Filtrar(@RequestBody Maestro e) {
		return maestroService.ObtenerPorFiltro(e);
	}

	@GetMapping("todos")
	@ResponseStatus(HttpStatus.OK)
	public List<Maestro> Todos() {
		return maestroService.Todos();
	}
}
