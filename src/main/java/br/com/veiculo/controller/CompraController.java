package br.com.veiculo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.veiculo.dto.CompraDTO;
import br.com.veiculo.service.CompraService;
import br.com.veiculo.dto.CompraRequestDTO;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService service;

    @PostMapping
    public CompraDTO comprar(@RequestBody CompraRequestDTO dto) {
        return service.realizarCompra(dto);
    }

    @GetMapping
    public List<CompraDTO> listarCompras() {
        return service.listarCompras();
    }

    @PutMapping("/{id}")
    public CompraDTO atualizar(
            @PathVariable Long id,
            @RequestBody CompraRequestDTO dto) {
        return service.atualizarCompra(id, dto);
    }

    @PatchMapping("/{id}")
    public CompraDTO atualizarParcial(
            @PathVariable Long id,
            @RequestBody CompraRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @GetMapping("/{id}")
    public CompraDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletarCompra(id);
    }
}