package br.com.veiculo.client;

import br.com.veiculo.dto.ResponseAnoDto;
import br.com.veiculo.dto.ResponseMarcaDto;
import br.com.veiculo.dto.ResponseModelosWrapper;
import br.com.veiculo.dto.ResponseVeiculoApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "fipe-api", url = "https://parallelum.com.br/fipe/api/v1/carros")
public interface FipeApiClient {
    @GetMapping("/marcas")
    List<ResponseMarcaDto> getMarcas();

    @GetMapping("/marcas/{codigoMarca}/modelos")
    ResponseModelosWrapper getModelos(@PathVariable("codigoMarca") String codigoMarca);

    @GetMapping("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
    List<ResponseAnoDto> getAnos(@PathVariable("codigoMarca") String codigoMarca, @PathVariable("codigoModelo") String codigoModelo);

    @GetMapping("/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{codigoAno}")
    ResponseVeiculoApiDto getVeiculo(@PathVariable("codigoMarca") String codigoMarca, @PathVariable("codigoModelo") String codigoModelo, @PathVariable("codigoAno") String codigoAno);
}
