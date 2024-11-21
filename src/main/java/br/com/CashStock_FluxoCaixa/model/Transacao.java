package br.com.CashStock_FluxoCaixa.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Table(name = "transacao")
@Entity(name= "Transacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkIdCategoria;
    private BigDecimal valor;
    private Date data;
    private Tipo tipo;
}
