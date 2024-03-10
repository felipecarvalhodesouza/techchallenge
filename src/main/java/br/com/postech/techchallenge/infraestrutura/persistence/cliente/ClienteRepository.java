package br.com.postech.techchallenge.infraestrutura.persistence.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

	ClienteEntity getByCpf(Long cpf);
}
