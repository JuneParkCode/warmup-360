package org.kernel360.todoserver.persist;

import org.kernel360.todoserver.persist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
