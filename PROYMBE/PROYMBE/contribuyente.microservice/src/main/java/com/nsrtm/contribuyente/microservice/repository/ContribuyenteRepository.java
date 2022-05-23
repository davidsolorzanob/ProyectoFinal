package com.nsrtm.contribuyente.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nsrtm.contribuyente.microservice.domain.Contribuyente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ContribuyenteRepository extends JpaRepository<Contribuyente, Long>, JpaSpecificationExecutor {

}
