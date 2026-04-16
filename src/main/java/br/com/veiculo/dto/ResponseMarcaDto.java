package br.com.veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMarcaDto {
    private String codigo;
    private String nome;
}
