package org.kernel360.simpleboard.post.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	public Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long boardId, String status);
}
