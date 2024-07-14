package org.kernel360.simpleboard.reply.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ReplyDto {
	private Long id;

	@NotNull
	private Long postId;

	@NotBlank
	@Size(min = 1, max = 50)
	private String userName;

	@NotBlank
	@Size(min = 4, max = 4)
	private String password;

	private String status;

	@NotBlank
	private String title;

	@NotBlank
	private String content;

	private LocalDateTime repliedAt;
}
