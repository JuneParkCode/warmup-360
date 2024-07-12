package org.kernel360.precourse.chapters.memdb.service;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.memdb.model.UserMemdbEntity;
import org.kernel360.precourse.chapters.memdb.repository.UserMemdbRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMemdbService {
	private final UserMemdbRepository userRepository;

	public Optional<UserMemdbEntity> findById(Long id) {
		return userRepository.findById(id);
	}

	public List<UserMemdbEntity> findAll() {
		return userRepository.findAll();
	}

	public UserMemdbEntity save(UserMemdbEntity userEntity) {
		return userRepository.save(userEntity);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<UserMemdbEntity> filterScore(int score) {
		return userRepository.findAllByScoreIsGreaterThanEqual(score);
	}
}
