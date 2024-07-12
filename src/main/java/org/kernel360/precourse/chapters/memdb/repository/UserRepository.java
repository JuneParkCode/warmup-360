package org.kernel360.precourse.chapters.memdb.repository;

import java.util.List;

import org.kernel360.precourse.chapters.memdb.model.UserEntity;
import org.kernel360.precourse.db.memdb.MemoryDbRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepository extends MemoryDbRepository<UserEntity, Long> {
	public UserRepository() {
		super();
	}

	public UserRepository(List<UserEntity> dataList) {
		super(dataList);
	}

	public List<UserEntity> findAllScoreGreaterOrEqualThan(int score) {
		return findAll().stream()
			.filter(it -> it.getScore() >= score)
			.toList();
	}
}
