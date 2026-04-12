package br.com.veiculo.service;

import br.com.veiculo.dto.VeiculoDTO;
import br.com.veiculo.dto.VeiculoRequestDTO;
import br.com.veiculo.entity.Veiculo;
import br.com.veiculo.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repo;

    private VeiculoDTO toDTO(Veiculo v) {
        return new VeiculoDTO(
                v.getId(),
                v.getMarca(),
                v.getModelo(),
                v.getValor()
        );
    }

    public VeiculoDTO salvar(VeiculoRequestDTO dto) {
        Veiculo v = Veiculo.builder()
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .valor(dto.getValor())
                .build();

        return toDTO(repo.save(v));
    }

    public List<VeiculoDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public VeiculoDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        v.setMarca(dto.getMarca());
        v.setModelo(dto.getModelo());
        v.setValor(dto.getValor());

        return toDTO(repo.save(v));
    }

    public VeiculoDTO atualizarParcial(Long id, VeiculoRequestDTO dto) {
        Veiculo v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (dto.getMarca() != null) v.setMarca(dto.getMarca());
        if (dto.getModelo() != null) v.setModelo(dto.getModelo());
        if (dto.getValor() != null) v.setValor(dto.getValor());

        return toDTO(repo.save(v));
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        repo.deleteById(id);
    }
}