package org.kernel360.simpleboard.common.crud;

public interface Converter<DTO, ENTITY> {
	public ENTITY toEntity(DTO dto);

	public DTO toDto(ENTITY entity);
}
