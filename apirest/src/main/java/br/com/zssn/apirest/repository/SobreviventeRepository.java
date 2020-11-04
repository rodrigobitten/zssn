package br.com.zssn.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zssn.apirest.models.Sobrevivente;

public interface SobreviventeRepository extends JpaRepository<Sobrevivente, Long> {
	Sobrevivente findById(long id);
}
