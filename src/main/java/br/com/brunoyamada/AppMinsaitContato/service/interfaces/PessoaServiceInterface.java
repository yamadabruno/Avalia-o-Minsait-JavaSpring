package br.com.brunoyamada.AppMinsaitContato.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.brunoyamada.AppMinsaitContato.model.Pessoa;

public interface PessoaServiceInterface {

	Pessoa save(Pessoa contato);
	Optional<Pessoa> getById(Long id);
	List<Pessoa> getAll();
	Pessoa update(Pessoa contato);
	void delete(Long id);
	
}
