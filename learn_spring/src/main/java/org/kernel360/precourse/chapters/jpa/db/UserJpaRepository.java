package org.kernel360.precourse.chapters.jpa.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
	public List<UserJpaEntity> findAllByScoreIsGreaterThanEqual(int score);
}
