package dev3.dev3project.repository;

import dev3.dev3project.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByEmailIgnoreCase(String email);
        Usuario findByNome(String nome);

}
