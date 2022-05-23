package com.nsrtm.maestro.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsrtm.maestro.microservice.domain.Maestro;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaestroRepository extends JpaRepository<Maestro, Integer>, JpaSpecificationExecutor<Maestro> {

}
