package br.com.infnet.pbtp1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "gato")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Character genero;
    private String descricao;
    private boolean disponivelAdocao = true;
}
