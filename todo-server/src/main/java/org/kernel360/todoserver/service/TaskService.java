package org.kernel360.todoserver.service;

import java.sql.Date;
import java.time.LocalDate;

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
