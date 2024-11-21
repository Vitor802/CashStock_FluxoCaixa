package br.com.CashStock_FluxoCaixa.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categoria")
@Entity(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
