package br.com.brunoyamada.AppMinsaitContato.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunoyamada.AppMinsaitContato.model.Contato;
import br.com.brunoyamada.AppMinsaitContato.model.Pessoa;
import br.com.brunoyamada.AppMinsaitContato.record.PessoaRecord;
import br.com.brunoyamada.AppMinsaitContato.service.ContatoService;
import br.com.brunoyamada.AppMinsaitContato.service.PessoaService;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {
	
	private PessoaService pessoaService;
	
	@Autowired
	private ContatoService contatoService;

	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaService.getAll();
		
		if(pessoas == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		} else if(pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/maladireta/{id}")
	public ResponseEntity <PessoaRecord> getMalaDiretaById(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		
		Pessoa pessoaObj = pessoa.get();
		PessoaRecord dto = new PessoaRecord(pessoaObj.getId(), pessoaObj.getNome(), pessoaObj.infMalaDireta());
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}/contatos")
	public ResponseEntity <List<Contato>> getContatos(@PathVariable Long id){
		List<Contato> listContato = contatoService.getByPessoa(id);
		
		if(listContato.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(listContato);
	}

	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa newPessoa = pessoaService.save(pessoa);
		
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(newPessoa);
	}
	
	@PostMapping("/{id}/contatos")
	public ResponseEntity<Contato> adicionaContato(@PathVariable Long id, @RequestBody Contato contato) {
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa == null)
			return ResponseEntity.notFound().build();
		
		contato.setPessoaId(pessoa.get());
		Contato newContato = contatoService.save(contato);
		return ResponseEntity.ok(newContato);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa){
		pessoa.setId(id);
		Pessoa newPessoa = pessoaService.update(pessoa);
		
		if(newPessoa == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(newPessoa);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		
		if(pessoa.isEmpty())
			return ResponseEntity.notFound().build();
		
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}