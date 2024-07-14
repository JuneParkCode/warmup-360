package org.kernel360.todoserver.persist;

import java.sql.Date;
import java.util.List;

import org.kernel360.todoserver.constants.TaskStatus;
import org.kernel360.todoserver.persist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	public List<TaskEntity> findAllByDueDate(Date dueDate);

	public List<TaskEntity> findAllByStatus(TaskStatus status);
}
