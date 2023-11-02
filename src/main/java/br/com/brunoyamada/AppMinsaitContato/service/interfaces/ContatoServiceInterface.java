package br.com.brunoyamada.AppMinsaitContato.service.interfaces;

import java.util.List;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;

public interface ContatoServiceInterface {
	
	Contato save(Contato contato);
	Contato getById(Long id);
	List<Contato> getAll();
	Contato update(Contato contato);
	void delete(Long id);

}
