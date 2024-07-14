package org.kernel360.simpleboard.board.db;

import java.util.List;

import org.hibernate.annotations.SQLOrder;
import org.hibernate.annotations.SQLRestriction;
import org.kernel360.simpleboard.post.db.PostEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "board")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String boardName;

	private String status;

	@OneToMany(
		mappedBy = "board"
	)
	@Builder.Default // Builder 패턴에서 해당 필드를 기본값으로 설정
	@ToString.Exclude
	@SQLOrder("id DESC") // 연결된 post 엔티티를 id 역순으로 정렬
	@SQLRestriction("status = 'REGISTERED'") // @Where 의 경우 Deprecated.
	private List<PostEntity> postList = List.of();
}
