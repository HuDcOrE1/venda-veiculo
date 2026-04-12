package br.com.veiculo.service;
import lombok.*;
import org.springframework.stereotype.*;
import br.com.veiculo.repository.*;
import br.com.veiculo.dto.ClienteDTO;
import br.com.veiculo.dto.CompraDTO;
import br.com.veiculo.dto.VeiculoDTO;
import br.com.veiculo.entity.*;
import java.time.*;
import java.util.List;

@Service 
@RequiredArgsConstructor
public class CompraService{ 
    private final CompraRepository cr; 
    private final ClienteRepository clr; 
    private final VeiculoRepository vr;

    public Compra realizarCompra(Long cId,Long vId){ 
        Cliente c=clr.findById(cId).orElseThrow(); 
        Veiculo v=vr.findById(vId).orElseThrow(); 
        return cr.save(Compra.builder().cliente(c).veiculo(v).dataCompra(LocalDate.now()).build()); 
    }

    
    public List<CompraDTO> listarCompras() {
        return cr.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
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

}