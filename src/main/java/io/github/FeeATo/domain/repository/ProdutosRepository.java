package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> { //<a classe, tipo_da_chave>
}
