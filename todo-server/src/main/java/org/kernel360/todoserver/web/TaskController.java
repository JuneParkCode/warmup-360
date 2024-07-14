package org.kernel360.todoserver.web;

import org.kernel360.todoserver.model.Task;
import org.kernel360.todoserver.service.TaskService;
import org.kernel360.todoserver.web.vo.TaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
	private final TaskService taskService;

	/**
	 * 새로운 할 일을 추가합니다.
	 * @param req 추가하고자 하는 할 일
	 * @return 추가된 할 일
	 */
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody TaskRequest req) {
		Task task = this.taskService.create(req.getTitle(), req.getDescription(), req.getDueDate());

		return ResponseEntity.ok(task);
	}
}
