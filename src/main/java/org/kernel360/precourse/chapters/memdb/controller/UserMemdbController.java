package org.kernel360.precourse.chapters.memdb.controller;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.memdb.model.UserMemdbEntity;
import org.kernel360.precourse.chapters.memdb.service.UserMemdbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/memdb/users")
@RequiredArgsConstructor
public class UserMemdbController {
	private final UserMemdbService userService;

	@PutMapping("")
	public ResponseEntity<UserMemdbEntity> saveUser(
		@RequestBody UserMemdbEntity requestUserMemdbEntity) {
		return ResponseEntity.ok(userService.save(requestUserMemdbEntity));
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteUser(
		@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserMemdbEntity> getUser(@PathVariable Long id) {
		Optional<UserMemdbEntity> userEntity = userService.findById(id);

		return userEntity.map(entity -> ResponseEntity.ok()
			.body(entity)).orElseGet(() -> ResponseEntity.notFound()
			.build());
	}

	@GetMapping("")
	public ResponseEntity<List<UserMemdbEntity>> getUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/score")
	public ResponseEntity<List<UserMemdbEntity>> getUserByScore(
		@RequestParam(name = "score") int score) {
		List<UserMemdbEntity> filteredScore = userService.filterScore(score);

		return ResponseEntity.ok().body(filteredScore);
	}
}
