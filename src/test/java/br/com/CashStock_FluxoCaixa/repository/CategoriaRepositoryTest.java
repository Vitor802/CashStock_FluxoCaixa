package br.com.CashStock_FluxoCaixa.repository;

import br.com.CashStock_FluxoCaixa.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Categoria antes de cada teste
        categoria = new Categoria(null, "Eletrônicos");
    }

    @Test
    void deveCriarCategoria() {
        // Act: Salva a categoria no banco de dados
        Categoria savedCategoria = categoriaRepository.save(categoria);

        // Assert: Verifica se a categoria foi salva corretamente e tem um ID gerado
        assertNotNull(savedCategoria.getId(), "O ID da categoria não deve ser nulo após o salvamento");
        assertEquals("Eletrônicos", savedCategoria.getNome(), "O nome da categoria deve ser o mesmo");
    }

    @Test
    void deveEncontrarCategoriaPorId() {
        // Arrange: Salva a categoria no banco de dados
        Categoria savedCategoria = categoriaRepository.save(categoria);

        // Act: Busca a categoria pelo ID
        Categoria foundCategoria = categoriaRepository.findById(savedCategoria.getId()).orElse(null);

        // Assert: Verifica se a categoria foi encontrada
        assertNotNull(foundCategoria, "Categoria deve ser encontrada pelo ID");
        assertEquals(savedCategoria.getId(), foundCategoria.getId(), "O ID deve ser o mesmo");
        assertEquals(savedCategoria.getNome(), foundCategoria.getNome(), "O nome deve ser o mesmo");
    }

    @Test
    void deveAtualizarCategoria() {
        // Arrange: Salva a categoria no banco de dados
        Categoria savedCategoria = categoriaRepository.save(categoria);

        // Atualiza a categoria
        savedCategoria.setNome("Móveis");

        // Act: Atualiza a categoria no banco de dados
        Categoria updatedCategoria = categoriaRepository.save(savedCategoria);

        // Assert: Verifica se a atualização foi feita corretamente
        assertEquals("Móveis", updatedCategoria.getNome(), "O nome da categoria deve ser atualizado");
    }

    @Test
    void deveDeletarCategoria() {
        // Arrange: Salva a categoria no banco de dados
        Categoria savedCategoria = categoriaRepository.save(categoria);

        // Act: Deleta a categoria do banco de dados
        categoriaRepository.deleteById(savedCategoria.getId());

        // Assert: Verifica se a categoria foi removida
        assertFalse(categoriaRepository.findById(savedCategoria.getId()).isPresent(),
                "Categoria não deve ser encontrada após a exclusão");
    }
}