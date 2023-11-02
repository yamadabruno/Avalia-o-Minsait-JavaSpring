package br.com.brunoyamada.AppMinsaitContato.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;
import br.com.brunoyamada.AppMinsaitContato.service.ContatoService;


@RestController
@RequestMapping("/api/contatos")
public class ContatoResource {
	
	private ContatoService contatoService;

	public ContatoResource(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	@GetMapping
	public ResponseEntity<List<Contato>> getAllContatos() {
		List<Contato> contatos = contatoService.getAll();
		
		if(contatos == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(contatos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> getById(@PathVariable Long id){
		Optional<Contato> contato = contatoService.getById(id);
		
		if(contato == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(contato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato){
		contato.setId(id);
		Contato newContato = contatoService.update(contato);
		
		if(newContato == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(newContato);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Contato> contato = contatoService.getById(id);
		
		if(contato.isEmpty())
			return ResponseEntity.notFound().build();
		
		contatoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}