package br.com.veiculo.controller;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public CompraDTO comprar(@Valid @RequestBody CompraRequestDTO dto) {
        return service.realizarCompra(dto);
    }

    @GetMapping
    @Cacheable("compras")
    public Page<CompraDTO> listarCompras(Pageable pageable) {
        return service.listar(pageable);
    }

    @CachePut(value = "compra", key = "#id")
    @PutMapping("/{id}")
    public CompraDTO atualizar(
            @PathVariable Long id,
            @RequestBody CompraRequestDTO dto) {
        return service.atualizarCompra(id, dto);
    }

    @CachePut(value = "compra", key = "#id")
    @PatchMapping("/{id}")
    public CompraDTO atualizarParcial(
            @PathVariable Long id,
            @RequestBody CompraRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "compra", key = "#id")
    public CompraDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"compra", "compras"}, allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletarCompra(id);
    }

    @GetMapping("/limpar-cache")
    @CacheEvict(value = {"compra", "compras"}, allEntries = true)
    public void limparCache() {}
}