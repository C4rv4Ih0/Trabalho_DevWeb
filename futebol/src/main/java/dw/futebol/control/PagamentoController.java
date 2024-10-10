package dw.futebol.control;

import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RestController;

import dw.futebol.model.Jogador;
import dw.futebol.model.Pagamento;
import dw.futebol.repository.JogadorRepository;
import dw.futebol.repository.PagamentoRepository;

@RestController
public class PagamentoController {
    @Autowired
    PagamentoRepository rep;

    @Autowired
    JogadorRepository repJogador;

    @GetMapping("/pagamento")
    public ResponseEntity<List <Pagamento> > getAllPagamentos(){
        try {
            List<Pagamento> pagamentos = rep.findAll();

            if(pagamentos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagamento/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable ("id") long id){
        Optional<Pagamento> data = rep.findById(id);

        try {
            if(data.isPresent()){
                return new ResponseEntity<>(data.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagamento/jogador/{id}")
    public ResponseEntity<List <Pagamento> > getPagamentoByJogador(@PathVariable ("id") long id){
        Optional<Jogador> jogador;

        try {
            if(id != 0){
                jogador = repJogador.findById(id);
                if(jogador.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                List<Pagamento> pagamentos = rep.findByJogador(jogador);

                if(pagamentos.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(pagamentos, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pagamento")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pgto){
        try{
            Optional<Jogador> jogador;

            if(Objects.isNull(pgto.getJogador().getCod_jogador())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                jogador = repJogador.findById(pgto.getJogador().getCod_jogador());
            }

            if(jogador.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Pagamento pagamento = rep.save(new Pagamento(pgto.getAno(), pgto.getMes(), pgto.getValor(), jogador.get()));
            return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pagamento/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") long id, @RequestBody Pagamento pgto){
        Optional<Pagamento> data = rep.findById(id);

        try {
            if(data.isPresent()){
                Pagamento pagamento = data.get();

                if(pgto.getAno() != 0){
                    pagamento.setAno(pgto.getAno());
                }
                
                if(pgto.getMes() != 0){
                    pagamento.setMes(pgto.getMes());
                }

                if(pgto.getValor() != 0){
                    pagamento.setValor(pgto.getValor());
                }

                if(pgto.getJogador() != null){
                    Optional<Jogador> jogador;
                    jogador = repJogador.findById(pgto.getJogador().getCod_jogador());

                    if(jogador.isPresent()){
                        System.out.println(jogador);
                        pagamento.setJogador(jogador.get());
                    }else{
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }

                return new ResponseEntity<>(rep.save(pagamento), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pagamento/{id}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("id") long id){
        Optional<Pagamento> pagamento = rep.findById(id);
        
        try {
            if(pagamento.isPresent()) {
                rep.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/pagamento")
    public ResponseEntity<HttpStatus> deleteAllPagamentos(){
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}