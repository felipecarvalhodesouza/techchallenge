package br.com.postech.techchallenge.infraestrutura.persistence.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

	ClienteEntity getByCpf(Long cpf);

	Optional<ClienteEntity> findByEmail(String email);
}
