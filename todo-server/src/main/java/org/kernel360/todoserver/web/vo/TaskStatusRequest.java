package org.kernel360.todoserver.web.vo;

import org.kernel360.todoserver.constants.TaskStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TaskStatusRequest {
	private TaskStatus status;
}
