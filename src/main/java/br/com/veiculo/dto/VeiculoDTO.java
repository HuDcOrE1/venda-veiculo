package br.com.veiculo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private Double valor;
}