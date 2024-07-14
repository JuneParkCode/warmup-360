package org.kernel360.todoserver.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.kernel360.todoserver.constants.TaskStatus;
import org.kernel360.todoserver.model.Task;
import org.kernel360.todoserver.persist.TaskRepository;
import org.kernel360.todoserver.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

	public Task create(String title, String description, LocalDate dueDate) {
		TaskEntity entity = TaskEntity.builder()
			.title(title)
			.description(description)
			.dueDate(Date.valueOf(dueDate))
			.status(TaskStatus.TODO)
			.build();

		TaskEntity saved = this.taskRepository.save(entity);
		return this.toObject(saved);
	}

	public List<Task> getAll() {
		return this.taskRepository.findAll().stream()
			.map(this::toObject)
			.toList();
	}

	public List<Task> getByDueDate(String dueDate) {
		return this.taskRepository.findAllByDueDate(Date.valueOf(dueDate)).stream()
			.map(this::toObject)
			.toList();
	}

	public List<Task> getByStatus(TaskStatus status) {
		return this.taskRepository.findAllByStatus(status).stream()
			.map(this::toObject)
			.toList();
	}

	public Task getOne(Long id) {
		var entity = this.getById(id);
		return this.toObject(entity);
	}

	private TaskEntity getById(Long id) {
		return this.taskRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException(String.format("not exists task id [%d]", id)));
	}

	public Task update(Long id, String title, String description, LocalDate dueDate) {
		var exists = this.getById(id);

		exists.setTitle(Strings.isEmpty(title) ? exists.getTitle() : title);
		exists.setDescription(Strings.isEmpty(description) ? exists.getDescription() : description);
		exists.setDueDate(dueDate == null ? exists.getDueDate() : Date.valueOf(dueDate));

		var updated = this.taskRepository.save(exists);
		return this.toObject(updated);
	}

	public Task updateStatus(Long id, TaskStatus status) {
		var exists = this.getById(id);
		exists.setStatus(status);

		var updated = this.taskRepository.save(exists);
		return this.toObject(updated);
	}

	public boolean delete(Long id) {
		try {
			this.taskRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error("delete task error", e);
			return false;
		}
	}

	private Task toObject(TaskEntity e) {
		return Task.builder()
			.id(e.getId())
			.title(e.getTitle())
			.description(e.getDescription())
			.status(e.getStatus())
			.dueDate(e.getDueDate().toString())
			.createdAt(e.getCreatedAt().toLocalDateTime())
			.updatedAt(e.getUpdatedAt().toLocalDateTime())
			.build();
	}

}
