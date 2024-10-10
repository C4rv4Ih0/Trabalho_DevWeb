package dw.futebol.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.futebol.model.Jogador;
import dw.futebol.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    List<Pagamento> findByJogador(Optional<Jogador> jogador);
}
