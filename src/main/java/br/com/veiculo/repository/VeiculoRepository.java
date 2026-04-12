package br.com.veiculo.repository;
import br.com.veiculo.entity.*;
import org.springframework.data.jpa.repository.*;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long>{}