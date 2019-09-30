package dev3.repository;

import dev3.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByEmailIgnoreCase(String email);
        Usuario findByNome(String nome);

}
