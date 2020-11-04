package br.com.zssn.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zssn.apirest.models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}