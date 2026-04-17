package br.com.veiculo.service;

import br.com.veiculo.client.FipeApiClient;
import br.com.veiculo.dto.*;
import br.com.veiculo.entity.Veiculo;
import br.com.veiculo.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static br.com.veiculo.util.VeiculoUtils.converterValor;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repo;
    private final FipeApiClient fipeApiClient;

    private VeiculoDTO toDTO(Veiculo v) {
        return new VeiculoDTO(
                v.getId(),
                v.getMarca(),
                v.getModelo(),
                v.getValor(),
                v.getAnoModelo(),
                v.getCodigoFipe()
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

    public Page<VeiculoDTO> listar(Pageable pageable) {
        return repo.findAll(pageable)
                .map(this::toDTO);
    }

    public VeiculoDTO buscarPorId(Long id) {
        Veiculo veiculo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));

        return toDTO(veiculo);
    }

    public VeiculoDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        v.setMarca(dto.getMarca());
        v.setModelo(dto.getModelo());
        v.setValor(dto.getValor());
        v.setAnoModelo(dto.getAnoModelo());
        v.setCodigoFipe(dto.getCodigoFipe());

        return toDTO(repo.save(v));
    }

    public VeiculoDTO atualizarParcial(Long id, VeiculoRequestDTO dto) {
        Veiculo v = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (dto.getMarca() != null) v.setMarca(dto.getMarca());
        if (dto.getModelo() != null) v.setModelo(dto.getModelo());
        if (dto.getValor() != null) v.setValor(dto.getValor());
        if (dto.getAnoModelo() != 0) v.setAnoModelo(dto.getAnoModelo());
        if (dto.getCodigoFipe() != null) v.setCodigoFipe(dto.getCodigoFipe());

        return toDTO(repo.save(v));
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        repo.deleteById(id);
    }

    public void importarDadosFipeApi() {
        List<Veiculo> veiculos = new ArrayList<>();
        List<ResponseMarcaDto> marcas = fipeApiClient.getMarcas();

        marcas.stream().limit(5).forEach(marca -> {
            String codigoMarca = marca.getCodigo();
            ResponseModelosWrapper response = fipeApiClient.getModelos(codigoMarca);

            response.getModelos().stream().limit(5).forEach(modelo -> {
                String codigoModelo = modelo.getCodigo();
                List<ResponseAnoDto> anos = fipeApiClient.getAnos(codigoMarca, codigoModelo);

                anos.stream().limit(5).forEach(ano -> {
                    String codigoAno = ano.getCodigo();

                    try {
                        ResponseVeiculoApiDto veiculoApiDto = fipeApiClient.getVeiculo(codigoMarca, codigoModelo, codigoAno);

                        Veiculo veiculo = Veiculo.builder()
                                .marca(veiculoApiDto.getMarca())
                                .modelo(veiculoApiDto.getModelo())
                                .anoModelo(veiculoApiDto.getAnoModelo())
                                .valor(converterValor(veiculoApiDto.getValor()))
                                .codigoFipe(veiculoApiDto.getCodigoFipe())
                                .build();

                        veiculos.add(veiculo);
                    } catch (Exception e) {
                        //implementar exceção
                    }
                });
            });
        });

        repo.saveAll(veiculos);
    }
}