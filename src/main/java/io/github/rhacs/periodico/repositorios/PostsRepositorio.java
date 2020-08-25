package io.github.rhacs.periodico.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.periodico.modelos.Post;

@Repository
public interface PostsRepositorio extends JpaRepository<Post, Long> {

}
