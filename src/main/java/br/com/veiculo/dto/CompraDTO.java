package br.com.veiculo.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

    private Long id;
    private LocalDate dataCompra;
    private ClienteDTO cliente;
    private VeiculoDTO veiculo;
}