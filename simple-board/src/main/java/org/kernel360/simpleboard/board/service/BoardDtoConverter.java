package org.kernel360.simpleboard.board.service;

import java.util.List;

import org.kernel360.simpleboard.board.db.BoardEntity;
import org.kernel360.simpleboard.board.model.BoardDto;
import org.kernel360.simpleboard.common.crud.Converter;
import org.kernel360.simpleboard.post.model.PostDto;
import org.kernel360.simpleboard.post.service.PostConverter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardDtoConverter implements Converter<BoardDto, BoardEntity> {
	private final PostConverter postConverter;

	@Override
	public BoardEntity toEntity(BoardDto boardDto) {
		return BoardEntity.builder()
			.boardName(boardDto.getBoardName())
			.id(boardDto.getId())
			.status(boardDto.getStatus() != null ? boardDto.getStatus() : "REGISTERED")
			.build();
	}

	@Override
	public BoardDto toDto(BoardEntity boardEntity) {
		List<PostDto> postList = boardEntity.getPostList()
			.stream()
			.map(postConverter::toDto)
			.toList();
		return BoardDto.builder()
			.boardName(boardEntity.getBoardName())
			.id(boardEntity.getId())
			.status(boardEntity.getStatus())
			.postList(postList)
			.build();
	}
}
