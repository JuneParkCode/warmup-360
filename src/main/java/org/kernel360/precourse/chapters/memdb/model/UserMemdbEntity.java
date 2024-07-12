package org.kernel360.precourse.chapters.memdb.model;

import org.kernel360.precourse.entity.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserMemdbEntity extends Entity {
	private String name;
	private int score;
}
