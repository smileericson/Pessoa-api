package br.com.senac.pessoa_api.controller;

import br.com.senac.pessoa_api.dtos.PessoaRequestDTO;
import br.com.senac.pessoa_api.entidades.Pessoas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin
public class PessoasController {

    private PessoasRepositorio pessoasRepositorio;

    public PessoasController(PessoasRepositorio pessoasRepositorio) {
        this.pessoasRepositorio = pessoasRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar(){
        List<Pessoas> pessoasList = pessoasRepositorio.findAll();

        if (pessoasList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pessoasList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoas> criar (@RequestBody PessoaRequestDTO pessoa){

        Pessoas pessoaPersist = new Pessoas();
        pessoaPersist.setNome(pessoa.getNome());
        pessoaPersist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = pessoasRepositorio.save(pessoaPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoas> atualizar (@RequestBody PessoaRequestDTO pessoa, @PathVariable long id){

        if (pessoasRepositorio.existsById(id)){

            Pessoas pessoaPersist = new Pessoas();
            pessoaPersist.setNome(pessoa.getNome());
            pessoaPersist.setSobrenome(pessoa.getSobrenome());
            pessoaPersist.setId(id);

            Pessoas retorno = pessoasRepositorio.save(pessoaPersist);

            return ResponseEntity.ok(retorno);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id){

        if (pessoasRepositorio.existsById(id)){
            pessoasRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    private class PessoasRepositorio {
        public List<Pessoas> findAll() {
            return List.of();
        }

        public boolean existsById(long id) {
            return false;
        }

        public void deleteById(long id) {
        }

        public Pessoas save(Pessoas pessoaPersist) {
            return pessoaPersist;
        }
    }
}