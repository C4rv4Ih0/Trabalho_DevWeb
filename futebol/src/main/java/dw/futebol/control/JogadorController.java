package dw.futebol.control;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.futebol.model.Jogador;
import dw.futebol.repository.JogadorRepository;

@RestController
public class JogadorController {

    @Autowired
    JogadorRepository rep;

    @GetMapping("/jogador")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam (required = false) String nome){
        try {
            List<Jogador> jogadores = new ArrayList<>();

            if(nome == null){
                rep.findAll().forEach(jogadores::add);
            }else{
                rep.findByNomeContainingIgnoreCase(nome).forEach(jogadores::add);
            }

            if(jogadores.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(jogadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jogador/{id}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable ("id") long id){
        Optional<Jogador> jogador = rep.findById(id);

        try {
            if(jogador.isPresent()) {
                return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/jogador")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jgdr){
        try {
            Jogador jogador = rep.save(new Jogador(jgdr.getNome(), jgdr.getEmail(), jgdr.getDatanasc()));
            return new ResponseEntity<>(jogador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/jogador/{id}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable ("id") long id, @RequestBody Jogador jgdr){
        Optional<Jogador> data = rep.findById(id);

        try{
            if(data.isPresent()) {
                Jogador jogador = data.get();
                jogador.setNome(jgdr.getNome());
                jogador.setEmail(jgdr.getEmail());
                jogador.setDatanasc(jgdr.getDatanasc());

                return new ResponseEntity<>(rep.save(jogador), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/jogador/{id}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable ("id") long id){
        Optional<Jogador> jogador;
        
        try {
            jogador = rep.findById(id);

            if(jogador.isPresent()) {
                rep.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/jogador")
    public ResponseEntity<HttpStatus> deleteAllJogadores(){
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}