package org.kernel360.precourse.chapters.jpa.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.jpa.db.UserJpaEntity;
import org.kernel360.precourse.chapters.jpa.db.UserJpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserJpaService {
	private final UserJpaRepository userJpaRepository;

	public Optional<UserJpaEntity> findById(Long id) {
		return userJpaRepository.findById(id);
	}

	public List<UserJpaEntity> findAll() {
		return userJpaRepository.findAll();
	}

	public UserJpaEntity save(UserJpaEntity userEntity) {
		return userJpaRepository.save(userEntity);
	}

	public void deleteById(Long id) {
		userJpaRepository.deleteById(id);
	}

	public List<UserJpaEntity> filterScore(int score) {
		return userJpaRepository.findAllByScoreIsGreaterThanEqual(score);
	}
}
