package org.kernel360.todoserver.persist.entity;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.kernel360.todoserver.constants.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "TASK")
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private TaskStatus status;

	private Date dueDate;

	@CreationTimestamp
	@Column(insertable = false, updatable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(insertable = false, updatable = false)
	private Timestamp updatedAt;
}
