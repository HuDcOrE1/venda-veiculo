package br.com.veiculo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.veiculo.dto.ClienteDTO;
import br.com.veiculo.dto.ClienteRequestDTO;
import br.com.veiculo.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteDTO salvar(@RequestBody ClienteRequestDTO dto){
        return service.salvar(dto);
    }

    @GetMapping
    public List<ClienteDTO> listar(){
        return service.listar();
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    public ClienteDTO atualizarParcial(@PathVariable Long id, @RequestBody ClienteRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}