package org.kernel360.todoserver.web;

import java.util.List;
import java.util.Optional;

import org.kernel360.todoserver.constants.TaskStatus;
import org.kernel360.todoserver.model.Task;
import org.kernel360.todoserver.service.TaskService;
import org.kernel360.todoserver.web.vo.ResultResponse;
import org.kernel360.todoserver.web.vo.TaskRequest;
import org.kernel360.todoserver.web.vo.TaskStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/**
	 * 특정 ID 에 해당하는 할 일을 수정
	 *
	 *  @param id 할 일 ID
	 * @param req 수정할 할 일 내용
	 * @return 수정된 할 일
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequest req) {
		Task task = this.taskService.update(id, req.getTitle(), req.getDescription(), req.getDueDate());
		return ResponseEntity.ok(task);
	}

	/**
	 * 특정 ID 에 해당하는 할 일의 상태를 수정
	 *
	 * @param id 할 일 ID
	 * @param req 수정할 상태
	 * @return 수정된 할 일
	 */
	@PatchMapping("/{id}/status")
	public ResponseEntity<Task> updateTaskStatus(
		@PathVariable Long id,
		@RequestBody TaskStatusRequest req) {
		Task task = this.taskService.updateStatus(id, req.getStatus());
		return ResponseEntity.ok(task);
	}

	/**
	 * 특정 ID 에 해당하는 할 일을 삭제
	 *
	 * @param id 할 일 ID
	 * @return 삭제 결과를 담은 응답 객체
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResultResponse> deleteTask(@PathVariable Long id) {
		return ResponseEntity.ok(new ResultResponse(this.taskService.delete(id)));
	}

	/**
	 * 할 일의 상태 목록을 반환
	 * @return 상태 목록
	 */
	@GetMapping("/status")
	public ResponseEntity<TaskStatus[]> getStatus() {
		return ResponseEntity.ok(TaskStatus.values());
	}
}
