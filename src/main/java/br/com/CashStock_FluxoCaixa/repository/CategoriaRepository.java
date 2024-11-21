package br.com.CashStock_FluxoCaixa.repository;

import br.com.CashStock_FluxoCaixa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
