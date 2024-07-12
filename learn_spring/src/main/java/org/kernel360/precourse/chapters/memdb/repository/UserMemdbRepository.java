package org.kernel360.precourse.chapters.memdb.repository;

import java.util.List;

import org.kernel360.precourse.chapters.memdb.db.memdb.MemoryDbRepository;
import org.kernel360.precourse.chapters.memdb.model.UserMemdbEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMemdbRepository extends MemoryDbRepository<UserMemdbEntity, Long> {
	public UserMemdbRepository() {
		super();
	}

	public UserMemdbRepository(List<UserMemdbEntity> dataList) {
		super(dataList);
	}

	public List<UserMemdbEntity> findAllByScoreIsGreaterThanEqual(int score) {
		return findAll().stream()
			.filter(it -> it.getScore() >= score)
			.toList();
	}
}
