package br.com.CashStock_FluxoCaixa.repository;

import br.com.CashStock_FluxoCaixa.model.SaldoInicial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SaldoInicialRepositoryTest {

    @Autowired
    private SaldoInicialRepository saldoInicialRepository;

    private SaldoInicial saldoInicial;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto SaldoInicial antes de cada teste
        saldoInicial = new SaldoInicial(null, new BigDecimal("1000.00"), new Date());
    }

    @Test
    void deveCriarSaldoInicial() {
        // Act: Salva o saldoInicial no banco de dados
        SaldoInicial savedSaldoInicial = saldoInicialRepository.save(saldoInicial);

        // Assert: Verifica se o saldoInicial foi salvo corretamente e tem um ID gerado
        assertNotNull(savedSaldoInicial.getId(), "O ID do saldoInicial não deve ser nulo após o salvamento");
        assertEquals(new BigDecimal("1000.00"), savedSaldoInicial.getSaldoAtual(), "O saldo atual deve ser o mesmo");
    }

    @Test
    void deveEncontrarSaldoInicialPorId() {
        // Arrange: Salva o saldoInicial no banco de dados
        SaldoInicial savedSaldoInicial = saldoInicialRepository.save(saldoInicial);

        // Act: Busca o saldoInicial pelo ID
        SaldoInicial foundSaldoInicial = saldoInicialRepository.findById(savedSaldoInicial.getId()).orElse(null);

        // Assert: Verifica se o saldoInicial foi encontrado
        assertNotNull(foundSaldoInicial, "SaldoInicial deve ser encontrado pelo ID");
        assertEquals(savedSaldoInicial.getId(), foundSaldoInicial.getId(), "O ID deve ser o mesmo");
        assertEquals(savedSaldoInicial.getSaldoAtual(), foundSaldoInicial.getSaldoAtual(), "O saldo deve ser o mesmo");
    }

    @Test
    void deveAtualizarSaldoInicial() {
        // Arrange: Salva o saldoInicial no banco de dados
        SaldoInicial savedSaldoInicial = saldoInicialRepository.save(saldoInicial);

        // Atualiza o saldoInicial
        savedSaldoInicial.setSaldoAtual(new BigDecimal("1500.00"));

        // Act: Atualiza o saldoInicial no banco de dados
        SaldoInicial updatedSaldoInicial = saldoInicialRepository.save(savedSaldoInicial);

        // Assert: Verifica se a atualização foi feita corretamente
        assertEquals(new BigDecimal("1500.00"), updatedSaldoInicial.getSaldoAtual(), "O saldo deve ser atualizado");
    }

    @Test
    void deveDeletarSaldoInicial() {
        // Arrange: Salva o saldoInicial no banco de dados
        SaldoInicial savedSaldoInicial = saldoInicialRepository.save(saldoInicial);

        // Act: Deleta o saldoInicial do banco de dados
        saldoInicialRepository.deleteById(savedSaldoInicial.getId());

        // Assert: Verifica se o saldoInicial foi removido
        assertFalse(saldoInicialRepository.findById(savedSaldoInicial.getId()).isPresent(),
                "SaldoInicial não deve ser encontrado após a exclusão");
    }
}