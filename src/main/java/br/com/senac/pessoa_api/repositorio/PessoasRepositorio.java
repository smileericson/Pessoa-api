package br.com.senac.pessoa_api.repositorio;

import br.com.senac.pessoa_api.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepositorio extends JpaRepository<Pessoas, Long> {

}