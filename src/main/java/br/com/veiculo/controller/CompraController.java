package br.com.veiculo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.veiculo.dto.CompraDTO;
import br.com.veiculo.entity.Compra;
import br.com.veiculo.service.CompraService;
import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/compras") 
@RequiredArgsConstructor
public class CompraController{ 

    private final CompraService s; 

    @PostMapping 
    public Compra comprar(
        @RequestParam Long clienteId,
        @RequestParam Long veiculoId){
            return s.realizarCompra(clienteId,veiculoId);
    }

    
    @GetMapping
    public List<CompraDTO> listarCompras() {
        return s.listarCompras();
    }

}