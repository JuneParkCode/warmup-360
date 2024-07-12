package org.kernel360.precourse.db.memdb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.kernel360.precourse.db.DataRepository;
import org.kernel360.precourse.entity.Entity;

public abstract class MemoryDbRepository<T extends Entity, ID extends Long> implements DataRepository<T, ID> {
	private final List<T> dataList;
	private long index = 0;

	public MemoryDbRepository() {
		this.dataList = new ArrayList<>();
	}

	public MemoryDbRepository(List<T> data) {
		this.dataList = data;
	}

	private final Comparator<T> sort = Comparator.comparingLong(Entity::getId);

	@Override
	public T save(T data) {
		if (Objects.isNull(data)) {
			throw new RuntimeException("Data is null");
		}

		Optional<T> prevData = dataList.stream()
			.filter(it ->
				it.getId().equals(data.getId())
			)
			.findFirst();
		if (prevData.isPresent()) {
			dataList.remove(prevData.get());
		} else {
			++index;
			data.setId(index);
		}
		dataList.add(data);
		return data;
	}

	@Override
	public Optional<T> findById(ID id) {
		return dataList.stream()
			.filter(it -> it.getId().equals(id))
			.findFirst();
	}

	@Override
	public void deleteById(ID id) {
		dataList.stream()
			.filter(it -> it.getId().equals(id))
			.findFirst()
			.ifPresent(dataList::remove);
	}

	@Override
	public List<T> findAll() {
		return dataList.stream()
			.sorted(sort)
			.toList();
	}
}
