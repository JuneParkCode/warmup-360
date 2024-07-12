package org.kernel360.precourse.chapters.memdb.db.memdb;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kernel360.precourse.chapters.memdb.db.entity.Entity;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/**
 * TODO: 해당 클래스에 대해서는 어떻게 테스트해야하는가?
 * - Memory DB 의 경우 List 와 같은 Collection 을 통해서 데이터를 저장하고 관리한다.
 * - 결국에 이 상태가 변경된다. 이를 검증하기 위해서는 해당 데이터가 저장되었는지 확인하는 것이 중요하다.
 * - 하지만 private 하므로 테스트에서는 일반적인 방법으로 접근하기는 어렵다.
 * - 이를 의존성으로 두고, 외부로 Data 를 가지도록 하는 것이 옳은가?
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class MemoryDbRepositoryTest {
	private MemoryDbRepository<Entity, Long> memoryDbRepository;
	private List<Entity> dataList;

	@BeforeEach
	public void setUp() {
		this.dataList = new ArrayList<>();
		this.memoryDbRepository = new MemoryDbRepository<>(dataList) {
		};
	}

	@Nested
	@DisplayName("save()")
	public class Save {
		@Test
		@DisplayName("dataList 가 null 일 경우 RuntimeException 이 발생한다.")
		public void saveEntity_null() {
			// given
			final MemoryDbRepository<Entity, Long> noDataRepository = new MemoryDbRepository<Entity, Long>(null) {
			};

			Entity entity = new Entity() {
			};
			// when
			// then
			assertThrows(RuntimeException.class, () -> noDataRepository.save(entity));
		}

		@Test
		@DisplayName("Entity 를 저장하면 해당 Entity 를 반환한다.")
		public void saveEntity() {
			// given
			Entity entity = new Entity() {
			};
			// when
			final var result = memoryDbRepository.save(entity);

			// then
			assertEquals(entity, result);
		}

		@Test
		@DisplayName("Entity 를 저장하면 dataList 에 저장된 Entity 가 존재한다.")
		public void saveEntity_checkDataList() {
			// given
			Entity entity = new Entity() {
			};
			// when
			memoryDbRepository.save(entity);

			// then
			assertTrue(dataList.contains(entity));
		}

		@Test
		@DisplayName("Entity 를 저장하면 index 가 증가한다.")
		public void saveEntity_checkIndex() {
			// given
			Entity entity = new Entity() {
			};
			Entity entity2 = new Entity() {
			};
			// when
			memoryDbRepository.save(entity);
			memoryDbRepository.save(entity2);

			// then
			assertEquals(2, entity2.getId());
		}

		@Test
		@DisplayName("존재하는 ID의 Entity를 저장하면 Entity의 index가 변경되지 않는다.")
		public void saveEntity_update_checkIndex() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);

			Entity updateEntity = new Entity() {
			};
			updateEntity.setId(1L);
			// when
			memoryDbRepository.save(updateEntity);

			// then
			assertEquals(1, updateEntity.getId());
		}
	}

	@Nested
	@DisplayName("deleteById()")
	public class DeleteById {
		@Test
		@DisplayName("Entity 를 삭제하면 dataList 에서 삭제된다.")
		public void deleteEntity() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);
			// when
			memoryDbRepository.deleteById(1L);

			// then
			assertFalse(dataList.contains(entity));
		}

		@Test
		@DisplayName("존재하지 않는 Entity 를 삭제하면 dataList 에서 삭제되지 않는다.")
		public void deleteEntity_not_exist() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);
			// when
			memoryDbRepository.deleteById(2L);

			// then
			assertTrue(dataList.contains(entity));
		}
	}

	@Nested
	@DisplayName("findById()")
	public class FindById {
		@Test
		@DisplayName("Entity 를 찾으면 Optional<Entity> 를 반환한다.")
		public void findById() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);
			// when
			final var result = memoryDbRepository.findById(1L);

			//
			assertTrue(result.isPresent());
		}

		@Test
		@DisplayName("존재하지 않는 Entity 를 찾으면 Optional.empty() 를 반환한다.")
		public void findById_not_exist() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);
			// when
			final var result = memoryDbRepository.findById(2L);

			//
			assertFalse(result.isPresent());
		}
	}

	@Nested
	@DisplayName("findAll()")
	public class FindAll {
		@Test
		@DisplayName("Entity 가 존재하면 List<Entity> 를 반환한다.")
		public void findAll() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(1L);
			dataList.add(entity);
			// when
			final var result = memoryDbRepository.findAll();

			//
			assertEquals(1, result.size());
		}

		@Test
		@DisplayName("Entity 가 여러개 존재하면 ID 가 정렬된 List<Entity> 를 반환한다.")
		public void findAll_multiple() {
			// given
			Entity entity = new Entity() {
			};
			entity.setId(2L);
			dataList.add(entity);

			Entity entity2 = new Entity() {
			};
			entity2.setId(1L);
			dataList.add(entity2);
			// when
			final var result = memoryDbRepository.findAll();

			//
			assertEquals(2, result.size());
			assertEquals(1L, result.get(0).getId());
			assertEquals(2L, result.get(1).getId());
		}

		@Test
		@DisplayName("Entity 가 존재하지 않으면 빈 List 를 반환한다.")
		public void findAll_empty() {
			// given
			// when
			final var result = memoryDbRepository.findAll();

			//
			assertEquals(0, result.size());
		}
	}
}
