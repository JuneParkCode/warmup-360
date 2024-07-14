package org.kernel360.todoserver.model;

import java.time.LocalDateTime;

import org.kernel360.todoserver.constants.TaskStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Task {

	private Long id;
	private String title;
	private String description;
	private TaskStatus status;
	private String dueDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
