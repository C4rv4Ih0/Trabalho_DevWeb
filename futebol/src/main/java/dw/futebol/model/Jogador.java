package dw.futebol.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cod_jogador;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable=false, length = 50)
    private String email;

  
    @Column(nullable=false)
    private LocalDate datanasc;

    @OneToMany(mappedBy = "jogador")
    @JsonIgnoreProperties("jogador")
    private List<Pagamento> pagamentos;

    public Jogador(String nome, String email, LocalDate datanasc) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
    }

    public Jogador() {
    }

    public long getCod_jogador() {
        return cod_jogador;
    }

    public void setCod_jogador(long cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(LocalDate datanasc) {
        this.datanasc = datanasc;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

}