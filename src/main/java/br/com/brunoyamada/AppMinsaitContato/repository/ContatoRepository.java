package br.com.brunoyamada.AppMinsaitContato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
