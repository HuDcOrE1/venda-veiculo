package br.com.veiculo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Veiculo{ 
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id; 
    private String marca; 
    private String modelo; 
    private Double valor;
}