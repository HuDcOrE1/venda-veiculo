package br.com.veiculo.repository;

import br.com.veiculo.entity.*;
import org.springframework.data.jpa.repository.*;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdNot(String cpf, Long id);
}