package org.kernel360.simpleboard.board.model;

import java.util.List;

import org.kernel360.simpleboard.post.model.PostDto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDto {
	private Long id;

	private String boardName;

	private String status;

	@Builder.Default
	private List<PostDto> postList = List.of();
}
