package br.com.postech.techchallenge.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postech.techchallenge.domain.model.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Long>{

	Cliente getByCpf(Long cpf);

}
