package org.kernel360.todoserver.web;

import java.util.List;
import java.util.Optional;

import org.kernel360.todoserver.constants.TaskStatus;
import org.kernel360.todoserver.model.Task;
import org.kernel360.todoserver.service.TaskService;
import org.kernel360.todoserver.web.vo.TaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * 특정 마감일에 해당하는 할 일 목록을 반환
	 *
	 * @param dueDate 마감일
	 * @return 마감일에 해당하는 할 일 목록
	 */
	@GetMapping
	public ResponseEntity<List<Task>> getTasks(Optional<String> dueDate) {
		List<Task> result;
		if (dueDate.isPresent()) {
			result = this.taskService.getByDueDate(dueDate.get());
		} else {
			result = this.taskService.getAll();
		}
		return ResponseEntity.ok(result);
	}

	/**
	 * 특정 ID 에 해당하는 할 일을 반환
	 *
	 * @param id 할 일 ID
	 * @return ID 에 해당하는 할 일
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Task> fetchOneTask(@PathVariable Long id) {
		return ResponseEntity.ok(this.taskService.getOne(id));
	}

	/**
	 * 특정 상태에 해당하는 할 일 목록을 반환
	 *
	 * @param status 상태
	 * @return 상태에 해당하는 할 일 목록
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
		return ResponseEntity.ok(this.taskService.getByStatus(status));
	}
}
