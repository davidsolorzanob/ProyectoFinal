package com.nsrtm.contribuyente.microservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nsrtm.contribuyente.microservice.domain.Contribuyente;
import com.nsrtm.contribuyente.microservice.service.ContribuyenteService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@EnableWebMvc
@RestController
@RequestMapping("/api/contribuyente")
public class ContribuyenteResource {

	@Autowired
	private KafkaTemplate<Integer, Contribuyente> kafkaTemplate;

	@Autowired
	private ContribuyenteService contribuyenteService;

	@Autowired
	public ContribuyenteResource(ContribuyenteService contribuyenteService) {
		this.contribuyenteService = contribuyenteService;
	}

	@PostMapping("crear")
	@ResponseStatus(HttpStatus.OK)
	public Contribuyente Crear(@Valid @RequestBody Contribuyente contribuyente, BindingResult result) {
		Contribuyente contribuyenteNew = contribuyenteService.Guardar(contribuyente);
		kafkaTemplate.send("nsrtm-topic", contribuyenteNew);
		return contribuyenteNew;
	}


	@PutMapping("editar")
	@ResponseStatus(HttpStatus.OK)
	public Contribuyente Editar(@RequestBody Contribuyente contribuyente) {
		Contribuyente res = contribuyenteService.Actualizar(contribuyente);
		return res;
	}

	@DeleteMapping("eliminar")
	@ResponseStatus(HttpStatus.OK)
	public void Eliminar(Long id) {
		contribuyenteService.Eliminar(id);
	}

	@GetMapping("obtener")
	@ResponseStatus(HttpStatus.OK)
	public Contribuyente ObtenerPorId(Long id) {
		return contribuyenteService.ObtenerPorId(id);
	}

	@GetMapping("todos")
	@ResponseStatus(HttpStatus.OK)
	public List<Contribuyente> Todos() {
		return contribuyenteService.Todos();
	}

	@PostMapping("filtrar")
	@ResponseStatus(HttpStatus.OK)
	public List<Contribuyente> Filtrar(@RequestBody Contribuyente contribuyente) {
		return contribuyenteService.ListarPorFiltros(contribuyente);
	}
}
