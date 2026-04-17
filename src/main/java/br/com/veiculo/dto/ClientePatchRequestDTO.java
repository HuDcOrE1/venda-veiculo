package br.com.veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePatchRequestDTO {
    
    private String nome;

    @CPF(message = "CPF inválido")
    private String cpf;
}
