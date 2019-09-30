package dev3.repository;

import dev3.entity.Postagem;
import dev3.entity.Usuario;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author victor.scherer
 */
public interface PostagemRepository extends PagingAndSortingRepository<Postagem, Long> {

    Page<Postagem> findAllByUsuarioOrderByHoraDesc(Usuario usuario, Pageable pgbl);
    
    Page<Postagem> findByUsuarioIdInAndIsPrivadaOrderByHoraDesc(List<Long> ids, boolean isPrivada, Pageable pageable);

}
