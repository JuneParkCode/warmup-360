package org.kernel360.precourse.chapters.memdb.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.memdb.model.UserEntity;
import org.kernel360.precourse.chapters.memdb.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public Optional<UserEntity> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<UserEntity> filterScore(int score) {
		return userRepository.findAllScoreGreaterOrEqualThan(score);
	}
}
