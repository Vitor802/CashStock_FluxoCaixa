package br.com.CashStock_FluxoCaixa.repository;

import br.com.CashStock_FluxoCaixa.model.Tipo;
import br.com.CashStock_FluxoCaixa.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TransacaoRepositoryTest {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private Transacao transacao;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Transacao antes de cada teste
        transacao = new Transacao(null, 1, new BigDecimal("100.00"), new Date(), Tipo.RECEITA);
    }

    @Test
    void deveCriarTransacao() {
        // Act: Salva a transacao no banco de dados
        Transacao savedTransacao = transacaoRepository.save(transacao);

        // Assert: Verifica se a transacao foi salva corretamente e tem um ID gerado
        assertNotNull(savedTransacao.getId(), "O ID da transação não deve ser nulo após o salvamento");
        assertEquals(new BigDecimal("100.00"), savedTransacao.getValor(), "O valor da transação deve ser o mesmo");
        assertEquals(Tipo.RECEITA, savedTransacao.getTipo(), "O tipo da transação deve ser o mesmo");
    }

    @Test
    void deveEncontrarTransacaoPorId() {
        // Arrange: Salva a transacao no banco de dados
        Transacao savedTransacao = transacaoRepository.save(transacao);

        // Act: Busca a transacao pelo ID
        Transacao foundTransacao = transacaoRepository.findById(savedTransacao.getId()).orElse(null);

        // Assert: Verifica se a transacao foi encontrada
        assertNotNull(foundTransacao, "Transacao deve ser encontrada pelo ID");
        assertEquals(savedTransacao.getId(), foundTransacao.getId(), "O ID deve ser o mesmo");
        assertEquals(savedTransacao.getValor(), foundTransacao.getValor(), "O valor deve ser o mesmo");
        assertEquals(savedTransacao.getTipo(), foundTransacao.getTipo(), "O tipo deve ser o mesmo");
    }

    @Test
    void deveAtualizarTransacao() {
        // Arrange: Salva a transacao no banco de dados
        Transacao savedTransacao = transacaoRepository.save(transacao);

        // Atualiza a transacao
        savedTransacao.setValor(new BigDecimal("200.00"));
        savedTransacao.setTipo(Tipo.DESPESA);

        // Act: Atualiza a transacao no banco de dados
        Transacao updatedTransacao = transacaoRepository.save(savedTransacao);

        // Assert: Verifica se a atualização foi feita corretamente
        assertEquals(new BigDecimal("200.00"), updatedTransacao.getValor(), "O valor da transação deve ser atualizado");
        assertEquals(Tipo.DESPESA, updatedTransacao.getTipo(), "O tipo da transação deve ser atualizado");
    }

    @Test
    void deveDeletarTransacao() {
        // Arrange: Salva a transacao no banco de dados
        Transacao savedTransacao = transacaoRepository.save(transacao);

        // Act: Deleta a transacao do banco de dados
        transacaoRepository.deleteById(savedTransacao.getId());

        // Assert: Verifica se a transacao foi removida
        assertFalse(transacaoRepository.findById(savedTransacao.getId()).isPresent(),
                "Transacao não deve ser encontrada após a exclusão");
    }
}