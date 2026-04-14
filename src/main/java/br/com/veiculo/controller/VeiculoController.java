package br.com.veiculo.controller;

import br.com.veiculo.dto.VeiculoDTO;
import br.com.veiculo.dto.VeiculoRequestDTO;
import br.com.veiculo.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    @Cacheable("veiculos")
    public Page<VeiculoDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PutMapping("/{id}")
    @CachePut(value = "veiculo", key = "#id")
    public VeiculoDTO atualizar(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    @CachePut(value = "veiculo", key = "#id")
    public VeiculoDTO atualizarParcial(@PathVariable Long id, @RequestBody VeiculoRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "veiculo", key = "#id")
    public VeiculoDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"veiculos", "veiculo"}, allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}