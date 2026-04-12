package br.com.veiculo.service;

import br.com.veiculo.dto.ClienteDTO;
import br.com.veiculo.dto.ClienteRequestDTO;
import br.com.veiculo.entity.Cliente;
import br.com.veiculo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repo;

    private ClienteDTO toDTO(Cliente c) {
        return new ClienteDTO(c.getId(), c.getNome(), c.getCpf());
    }

    public ClienteDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .build();

        return toDTO(repo.save(cliente));
    }

    public List<ClienteDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());

        return toDTO(repo.save(cliente));
    }

    public ClienteDTO atualizarParcial(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (dto.getNome() != null) cliente.setNome(dto.getNome());
        if (dto.getCpf() != null) cliente.setCpf(dto.getCpf());

        return toDTO(repo.save(cliente));
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        repo.deleteById(id);
    }
}