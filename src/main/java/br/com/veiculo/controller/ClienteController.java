package br.com.veiculo.controller;import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.veiculo.entity.Cliente;
import br.com.veiculo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/clientes") 
@RequiredArgsConstructor
public class ClienteController{ 
    private final ClienteRepository repo; 
    
    @PostMapping 
    public Cliente salvar(@RequestBody Cliente c){
        return repo.save(c);
    } 

    @GetMapping 
    public List<Cliente> listar(){
        return repo.findAll();
    }
}