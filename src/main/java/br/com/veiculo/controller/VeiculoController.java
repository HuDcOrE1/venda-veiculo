package br.com.veiculo.controller;

import br.com.veiculo.dto.VeiculoDTO;
import br.com.veiculo.dto.VeiculoRequestDTO;
import br.com.veiculo.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public VeiculoDTO salvar(@RequestBody VeiculoRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<VeiculoDTO> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public VeiculoDTO atualizar(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    public VeiculoDTO atualizarParcial(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @GetMapping("/{id}")
    public VeiculoDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}