package br.com.veiculo.controller;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

import br.com.veiculo.dto.ClienteDTO;
import br.com.veiculo.dto.ClienteRequestDTO;
import br.com.veiculo.dto.ClientePatchRequestDTO;
import br.com.veiculo.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteDTO salvar(@Valid @RequestBody ClienteRequestDTO dto){
        return service.salvar(dto);
    }

    @Cacheable("clientes")
    @GetMapping
    public Page<ClienteDTO> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @Cacheable(value = "cliente", key = "#id")
    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @CachePut(value = "cliente", key = "#id")
    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}")
    @CachePut(value = "cliente", key = "#id")
    public ClienteDTO atualizarParcial(@PathVariable Long id, @Valid @RequestBody ClientePatchRequestDTO dto) {
        return service.atualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"cliente", "clientes"}, allEntries = true)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/limpar-cache")
    @CacheEvict(value = {"cliente", "clientes"}, allEntries = true)
    public void limparCache() {}
}