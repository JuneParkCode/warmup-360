package org.kernel360.simpleboard.board.controller;

import org.kernel360.simpleboard.board.model.BoardDto;
import org.kernel360.simpleboard.board.model.BoardRequest;
import org.kernel360.simpleboard.board.service.BoardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
	private final BoardService boardService;

	@PostMapping("")
	public BoardDto create(
		@Valid
		@RequestBody
		BoardRequest boardRequest) {
		return boardService.create(boardRequest);
	}

	@GetMapping("/id/{id}")
	public BoardDto read(@PathVariable Long id) {
		return boardService.read(id);
	}

	@PutMapping("")
	public BoardDto update(
		@Valid
		@RequestBody
		BoardDto boardDto) {
		return boardService.update(boardDto);
	}

	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}
}
