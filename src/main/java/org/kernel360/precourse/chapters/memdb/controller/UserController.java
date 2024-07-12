package org.kernel360.precourse.chapters.memdb.controller;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.memdb.model.UserEntity;
import org.kernel360.precourse.chapters.memdb.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PutMapping("")
	public ResponseEntity<UserEntity> saveUser(
		@RequestBody UserEntity requestUserEntity) {
		return ResponseEntity.ok(userService.save(requestUserEntity));
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteUser(
		@PathVariable("id") Long id) {
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
		Optional<UserEntity> userEntity = userService.findById(id);

		return userEntity.map(entity -> ResponseEntity.ok()
			.body(entity)).orElseGet(() -> ResponseEntity.notFound()
			.build());
	}

	@GetMapping("")
	public ResponseEntity<List<UserEntity>> getUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/score")
	public ResponseEntity<List<UserEntity>> getUserByScore(
		@RequestParam(name = "score") int score) {
		List<UserEntity> filteredScore = userService.filterScore(score);

		return ResponseEntity.ok().body(filteredScore);
	}
}
