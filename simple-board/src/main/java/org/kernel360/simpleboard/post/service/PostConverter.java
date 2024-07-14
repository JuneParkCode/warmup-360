package org.kernel360.simpleboard.post.service;

import java.util.Optional;

import org.kernel360.simpleboard.board.db.BoardEntity;
import org.kernel360.simpleboard.board.db.BoardRepository;
import org.kernel360.simpleboard.common.crud.Converter;
import org.kernel360.simpleboard.post.db.PostEntity;
import org.kernel360.simpleboard.post.model.PostDto;
import org.kernel360.simpleboard.post.model.PostRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostConverter implements Converter<PostDto, PostEntity> {
	private final BoardRepository boardRepository;

	@Override
	public PostEntity toEntity(PostDto postDto) {
		final Optional<BoardEntity> boardEntity = boardRepository.findById(postDto.getBoardId());

		return PostEntity.builder()
			.id(postDto.getId())
			.userName(postDto.getUserName())
			.status(postDto.getStatus())
			.email(postDto.getEmail())
			.password(postDto.getPassword())
			.title(postDto.getTitle())
			.content(postDto.getContent())
			.postedAt(postDto.getPostedAt())
			.board(boardEntity.orElse(null))
			.build()
			;
	}

	public PostEntity toEntity(PostRequest postRequest) {
		final Optional<BoardEntity> boardEntity = boardRepository.findById(postRequest.getBoardId());

		return PostEntity.builder()
			.id(null)
			.userName(postRequest.getUserName())
			.status("REGISTERED")
			.email(postRequest.getEmail())
			.password(postRequest.getPassword())
			.title(postRequest.getTitle())
			.content(postRequest.getContent())
			.postedAt(null)
			.board(boardEntity.orElse(null))
			.build()
			;
	}

	@Override
	public PostDto toDto(PostEntity postEntity) {
		return PostDto.builder()
			.id(postEntity.getId())
			.userName(postEntity.getUserName())
			.status(postEntity.getStatus())
			.email(postEntity.getEmail())
			.password(postEntity.getPassword())
			.title(postEntity.getTitle())
			.content(postEntity.getContent())
			.postedAt(postEntity.getPostedAt())
			.boardId(postEntity.getBoard().getId())
			.build()
			;
	}
}
