package org.kernel360.simpleboard.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.kernel360.simpleboard.board.db.BoardEntity;
import org.kernel360.simpleboard.board.db.BoardRepository;
import org.kernel360.simpleboard.common.Api;
import org.kernel360.simpleboard.common.Pagination;
import org.kernel360.simpleboard.post.db.PostEntity;
import org.kernel360.simpleboard.post.db.PostRepository;
import org.kernel360.simpleboard.post.model.PostRequest;
import org.kernel360.simpleboard.post.model.PostViewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final PostConverter postConverter;

	public PostEntity create(
		PostRequest postRequest
	) {
		Optional<BoardEntity> boardEntity = boardRepository.findById(postRequest.getBoardId());
		PostEntity entity = postConverter.toEntity(postRequest);
		entity.setBoard(boardEntity.orElse(null));
		entity.setPostedAt(LocalDateTime.now());
		return postRepository.save(entity);
	}

	/**
	 * 1. 게시글이 있는가?
	 * 2. 비밀번호가 맞는가?
	 */
	public PostEntity view(PostViewRequest postViewRequest) {

		return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
			.map(it -> {
				if (!it.getPassword().equals(postViewRequest.getPassword())) {
					String format = "패스워드가 맞지 않습니다 %s vs %s";
					throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
				}
				return it;
			}).orElseThrow(
				() -> new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId())
			);
	}

	public Api<List<PostEntity>> all(Pageable pageable) {
		Page<PostEntity> list = postRepository.findAll(pageable);

		Pagination pagination = Pagination.builder()
			.page(list.getNumber())
			.size(list.getSize())
			.currentElements(list.getNumberOfElements())
			.totalElements(list.getTotalElements())
			.totalPage(list.getTotalPages())
			.build();

		Api<List<PostEntity>> response = Api.<List<PostEntity>>builder()
			.data(list.toList())
			.pagination(pagination)
			.build();
		return response;
	}

	public void delete(PostViewRequest postViewRequest) {
		postRepository.findById(postViewRequest.getPostId())
			.map(it -> {
				if (!it.getPassword().equals(postViewRequest.getPassword())) {
					var format = "패스워드가 맞지 않습니다 %s vs %s";
					throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
				}
				it.setStatus("UNREGISTERED"); // soft delete
				postRepository.save(it);
				return it;
			}).orElseThrow(
				() -> new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId())
			);
	}
}
