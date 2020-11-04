package br.com.zssn.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zssn.apirest.models.ItensComerciais;

public interface ItensComerciaisRepository extends JpaRepository<ItensComerciais, Long> {
	ItensComerciais findById(long id);
}