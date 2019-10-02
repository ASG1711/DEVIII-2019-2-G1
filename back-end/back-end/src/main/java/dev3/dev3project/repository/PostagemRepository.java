package dev3.dev3project.repository;

import dev3.dev3project.entity.Postagem;
import dev3.dev3project.entity.Usuario;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostagemRepository extends PagingAndSortingRepository<Postagem, Long> {

    Page<Postagem> findAllByUsuarioOrderByHoraDesc(Usuario usuario, Pageable pgbl);
    
    Page<Postagem> findByUsuarioIdInAndIsPrivadaOrderByHoraDesc(List<Long> ids, boolean isPrivada, Pageable pageable);

}
