package org.kernel360.simpleboard.common.crud;

import java.util.List;
import java.util.Optional;

import org.kernel360.simpleboard.common.Api;
import org.kernel360.simpleboard.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

	@Autowired(required = false)
	private JpaRepository<ENTITY, Long> jpaRepository;

	@Autowired(required = false)
	private Converter<DTO, ENTITY> converter;

	@Override
	public DTO create(DTO dto) {
		ENTITY entity = converter.toEntity(dto);

		jpaRepository.save(entity);
		return converter.toDto(entity);
	}

	@Override
	public Optional<DTO> read(Long id) {
		Optional<ENTITY> entity = jpaRepository.findById(id);

		return entity.map(converter::toDto);
	}

	@Override
	public DTO update(DTO dto) {
		ENTITY entity = converter.toEntity(dto);

		jpaRepository.save(entity);
		return converter.toDto(entity);
	}

	// NOTE: soft delete 가 아님.
	@Override
	public void delete(Long id) {
		jpaRepository.deleteById(id);
	}

	@Override
	public Api<List<DTO>> list(Pageable pageable) {
		Page<ENTITY> page = jpaRepository.findAll(pageable);
		Pagination pagination = Pagination.builder()
			.size(page.getSize())
			.page(page.getNumber())
			.currentElements(page.getNumberOfElements())
			.totalElements(page.getTotalElements())
			.totalPage(page.getTotalPages())
			.build();
		Api<List<DTO>> response = Api.<List<DTO>>builder()
			.data(page.getContent().stream()
				.map(converter::toDto)
				.toList())
			.pagination(pagination)
			.build();
		return response;
	}
}
