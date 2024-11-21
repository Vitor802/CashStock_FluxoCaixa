package br.com.CashStock_FluxoCaixa.repository;

import br.com.CashStock_FluxoCaixa.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
