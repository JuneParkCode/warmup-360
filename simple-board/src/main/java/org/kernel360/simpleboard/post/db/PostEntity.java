package org.kernel360.simpleboard.post.db;

import java.time.LocalDateTime;
import java.util.List;

import org.kernel360.simpleboard.board.db.BoardEntity;
import org.kernel360.simpleboard.reply.db.ReplyEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity(name = "post")
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnore
	@ToString.Exclude
	@JoinColumn(name = "board_id")
	private BoardEntity board;

	private String userName;

	private String password;

	private String email;

	private String status;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime postedAt;

	@OneToMany(
		mappedBy = "post"
	)
	@ToString.Exclude
	@Builder.Default
	private List<ReplyEntity> replies = List.of();
}
