package com.nsrtm.maestro.microservice.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nsrtm.maestro.microservice.domain.TipoMaestro;

import java.util.List;

public interface TipoMaestroRepository  extends JpaRepository<TipoMaestro, Integer> {
}
