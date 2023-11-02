package br.com.brunoyamada.AppMinsaitContato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	@Query(value = "SELECT * FROM contatos WHERE pessoa_id = ?", nativeQuery = true)
	List<Contato> findByPessoaId(Long pessoaId);

}
