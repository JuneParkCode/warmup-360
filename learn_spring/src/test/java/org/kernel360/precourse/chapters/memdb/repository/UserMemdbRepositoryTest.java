package org.kernel360.precourse.chapters.memdb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.precourse.chapters.memdb.model.UserMemdbEntity;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT)
class UserMemdbRepositoryTest {
	private UserMemdbRepository userRepository;

	@BeforeEach
	public void setUp() {
		userRepository = new UserMemdbRepository();
	}

	@Nested
	@DisplayName("findAllScoreGreaterOrEqualThan()")
	public class FindAllScoreGreaterOrEqualThan {
		@DisplayName("점수가 80점 이상인 사용자 목록을 조회한다.")
		@Test
		public void findAllScoreGreaterOrEqualThan() {
			// given
			for (int i = 0; i < 10; i++) {
				userRepository.save(UserMemdbEntity.builder().name("user" + i).score(i * 10).build());
			}
			// when
			final List<UserMemdbEntity> users = userRepository.findAllByScoreIsGreaterThanEqual(80);
			// then
			for (UserMemdbEntity user : users) {
				assertTrue(user.getScore() >= 80);
			}
		}
	}
}
