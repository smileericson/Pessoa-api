package br.com.senac.pessoa_api.controller;

import br.com.senac.pessoa_api.dtos.AnimalRequestDTO;
import br.com.senac.pessoa_api.entidades.Animais;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animais")
@CrossOrigin

public class AnimaisController {

    private AnimaisRepositorio animaisRepositorio;

    public AnimaisController(AnimaisRepositorio animaisRepositorio) {
        this.animaisRepositorio = animaisRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Animais>> listar(){
        List<Animais> animaisList = animaisRepositorio.findAll();

        if (animaisList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(animaisList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Animais> criar (@RequestBody AnimalRequestDTO animais){

        Animais animaisPersist = new Animais();
        animaisPersist.setNome(animais.getNome());
        animaisPersist.setRaca(animais.getRaca());
        animaisPersist.setPeso(animais.getPeso());

        Animais retorno = animaisRepositorio.save(animaisPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar (@RequestBody AnimalRequestDTO animais, @PathVariable long id){

        if (animaisRepositorio.existsById(id)){

            Animais animaisPersist = new Animais();
            animaisPersist.setNome(animais.getNome());
            animaisPersist.setRaca(animais.getRaca());
            animaisPersist.setPeso(animais.getPeso());
            animaisPersist.setId(id);

            Animais retorno = animaisRepositorio.save(animaisPersist);

            return ResponseEntity.ok(retorno);

        }

        return ResponseEntity.badRequest().body(null);

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id){

        if (animaisRepositorio.existsById(id)){

            animaisRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().body(null);
    }

    private class AnimaisRepositorio {
        public List<Animais> findAll() {
            return List.of();
        }

        public Animais save(Animais animaisPersist) {
            return animaisPersist;
        }

        public boolean existsById(long id) {
            return false;
        }

        public void deleteById(long id) {
        }
    }
}