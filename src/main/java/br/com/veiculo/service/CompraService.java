package br.com.veiculo.service;
import lombok.*;
import org.springframework.stereotype.*;
import br.com.veiculo.repository.*;
import br.com.veiculo.dto.ClienteDTO;
import br.com.veiculo.dto.CompraDTO;
import br.com.veiculo.dto.VeiculoDTO;
import br.com.veiculo.dto.CompraRequestDTO;
import br.com.veiculo.entity.*;
import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    public CompraDTO realizarCompra(CompraRequestDTO dto) {
        Cliente c = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Veiculo v = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Compra compra = compraRepository.save(
                Compra.builder()
                        .cliente(c)
                        .veiculo(v)
                        .dataCompra(LocalDate.now())
                        .build()
        );

        return toDTO(compra);
    }

    public List<CompraDTO> listarCompras() {
        return compraRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CompraDTO atualizarCompra(Long id, CompraRequestDTO dto) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        Cliente c = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Veiculo v = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        compra.setCliente(c);
        compra.setVeiculo(v);

        return toDTO(compraRepository.save(compra));
    }

    public CompraDTO atualizarParcial(Long id, CompraRequestDTO dto) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        if (dto.getClienteId() != null) {
            Cliente c = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            compra.setCliente(c);
        }

        if (dto.getVeiculoId() != null) {
            Veiculo v = veiculoRepository.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
            compra.setVeiculo(v);
        }

        return toDTO(compraRepository.save(compra));
    }

    public void deletarCompra(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new RuntimeException("Compra não encontrada");
        }
        compraRepository.deleteById(id);
    }

    private CompraDTO toDTO(Compra compra) {
        return new CompraDTO(
                compra.getId(),
                compra.getDataCompra(),
                new ClienteDTO(
                        compra.getCliente().getId(),
                        compra.getCliente().getNome(),
                        compra.getCliente().getCpf()
                ),
                new VeiculoDTO(
                        compra.getVeiculo().getId(),
                        compra.getVeiculo().getMarca(),
                        compra.getVeiculo().getModelo(),
                        compra.getVeiculo().getValor()
                )
        );
    }


    public CompraDTO buscarPorId(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrado"));

        return toDTO(compra);
    }
}