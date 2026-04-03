
package br.com.senac.pessoa_api.repositorio;

import br.com.senac.pessoa_api.entidades.Animais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimaisRepositorio extends JpaRepository<Animais, Long> {
}
