package br.com.brunoyamada.AppMinsaitContato.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;
import br.com.brunoyamada.AppMinsaitContato.repository.ContatoRepository;
import br.com.brunoyamada.AppMinsaitContato.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {
	
	private ContatoRepository contatoRepository;
	
	
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}

	@Override
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Override
	public Optional<Contato> getById(Long id) {
		return contatoRepository.findById(id);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}
	
	public List<Contato> getByPessoa(Long pessoaId) {
		return contatoRepository.findByPessoaId(pessoaId);
	}
	
	@Override
	public Contato update(Contato contato) {
		Optional<Contato> contatoOld = contatoRepository.findById(contato.getId());
		
		if(contatoOld.isPresent()) {
			Contato newContato = contatoOld.get();
			newContato.setContato(contato.getContato());
			newContato.setTipoContato(contato.getTipoContato());
			return contatoRepository.save(newContato);
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		contatoRepository.deleteById(id);
		
	}
	
}