package br.com.veiculo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Compra{ 
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Long id; 
    
    @ManyToOne 
    private Cliente cliente; 

    @ManyToOne 
    private Veiculo veiculo; 
    private LocalDate dataCompra;}