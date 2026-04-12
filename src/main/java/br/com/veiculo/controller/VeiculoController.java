package br.com.veiculo.controller;

import br.com.veiculo.repository.*;
import br.com.veiculo.entity.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController 
@RequestMapping("/veiculos") 
@RequiredArgsConstructor
public class VeiculoController{ 
    private final VeiculoRepository repo; 

    @PostMapping
    public Veiculo salvar(@RequestBody Veiculo v){
        return repo.save(v);
    } 

    @GetMapping 
    public List<Veiculo> listar(){return repo.findAll();


    }
}