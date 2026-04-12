package br.com.veiculo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
}