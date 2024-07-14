package org.kernel360.simpleboard.reply.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.kernel360.simpleboard.common.crud.Converter;
import org.kernel360.simpleboard.post.db.PostEntity;
import org.kernel360.simpleboard.post.db.PostRepository;
import org.kernel360.simpleboard.reply.db.ReplyEntity;
import org.kernel360.simpleboard.reply.model.ReplyDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDto, ReplyEntity> {
	private final PostRepository postRepository;

	@Override
	public ReplyEntity toEntity(ReplyDto replyDto) {
		Optional<PostEntity> postEntity = postRepository.findById(replyDto.getPostId());

		return ReplyEntity.builder()
			.id(replyDto.getId())
			.post(postEntity.orElse(null))
			.title(replyDto.getTitle())
			.content(replyDto.getContent())
			.userName(replyDto.getUserName())
			.password(replyDto.getPassword())
			.status(replyDto.getStatus() != null ? replyDto.getStatus() : "REGISTERED")
			.repliedAt(replyDto.getRepliedAt() != null ? replyDto.getRepliedAt() : LocalDateTime.now())
			.build();
	}

	@Override
	public ReplyDto toDto(ReplyEntity replyEntity) {
		return ReplyDto.builder()
			.id(replyEntity.getId())
			.postId(replyEntity.getPost().getId())
			.title(replyEntity.getTitle())
			.content(replyEntity.getContent())
			.userName(replyEntity.getUserName())
			.password(replyEntity.getPassword())
			.status(replyEntity.getStatus())
			.repliedAt(replyEntity.getRepliedAt())
			.build();
	}
}
