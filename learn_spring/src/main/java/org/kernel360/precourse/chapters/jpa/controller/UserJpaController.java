package org.kernel360.precourse.chapters.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.kernel360.precourse.chapters.jpa.db.UserJpaEntity;
import org.kernel360.precourse.chapters.jpa.service.UserJpaService;
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
@RequestMapping("/jpa/users")
@RequiredArgsConstructor
public class UserJpaController {
	private final UserJpaService userJpaService;

	@PutMapping("")
	public ResponseEntity<UserJpaEntity> saveUser(
		@RequestBody UserJpaEntity requestUserEntity) {
		return ResponseEntity.ok(userJpaService.save(requestUserEntity));
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> deleteUser(
		@PathVariable("id") Long id) {
		userJpaService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserJpaEntity> getUser(@PathVariable Long id) {
		Optional<UserJpaEntity> userEntity = userJpaService.findById(id);

		return userEntity.map(entity -> ResponseEntity.ok()
			.body(entity)).orElseGet(() -> ResponseEntity.notFound()
			.build());
	}

	@GetMapping("")
	public ResponseEntity<List<UserJpaEntity>> getUsers() {
		return ResponseEntity.ok(userJpaService.findAll());
	}

	@GetMapping("/score")
	public ResponseEntity<List<UserJpaEntity>> getUserByScore(
		@RequestParam(name = "score") int score) {
		List<UserJpaEntity> filteredScore = userJpaService.filterScore(score);

		return ResponseEntity.ok().body(filteredScore);
	}
}
