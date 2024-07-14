package org.kernel360.simpleboard.board.service;

import org.kernel360.simpleboard.board.db.BoardEntity;
import org.kernel360.simpleboard.board.db.BoardRepository;
import org.kernel360.simpleboard.board.model.BoardDto;
import org.kernel360.simpleboard.board.model.BoardRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	private final BoardDtoConverter boardDtoConverter;

	public BoardDto create(BoardRequest boardRequest) {
		BoardEntity boardEntity = BoardEntity.builder()
			.boardName(boardRequest.getBoardName())
			.status("REGISTERED")
			.build();

		boardRepository.save(boardEntity);
		return boardDtoConverter.toDto(boardEntity);
	}

	public BoardDto read(Long id) {
		BoardEntity boardEntity = boardRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Board Not Found"));

		return boardDtoConverter.toDto(boardEntity);
	}

	public BoardDto update(BoardDto boardDto) {
		BoardEntity boardEntity = boardRepository.findById(boardDto.getId())
			.orElseThrow(() -> new RuntimeException("Board Not Found"));

		boardEntity.setBoardName(boardDto.getBoardName());
		boardRepository.save(boardEntity);
		return boardDtoConverter.toDto(boardEntity);
	}

	public void delete(Long id) {
		boardRepository.deleteById(id);
	}
}
