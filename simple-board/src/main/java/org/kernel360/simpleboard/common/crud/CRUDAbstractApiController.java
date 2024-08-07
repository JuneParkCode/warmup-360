package org.kernel360.simpleboard.common.crud;

import java.util.List;
import java.util.Optional;

import org.kernel360.simpleboard.common.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CRUDAbstractApiController<DTO, ENTITY> implements CRUDInterface<DTO> {
	@Autowired(required = false)
	private CRUDAbstractService<DTO, ENTITY> crudAbstractService;

	@PostMapping("")
	@Override
	public DTO create(
		@Valid
		@RequestBody
		DTO dto) {
		return crudAbstractService.create(dto);
	}

	@GetMapping("/{id}")
	@Override
	public Optional<DTO> read(@PathVariable Long id) {
		return crudAbstractService.read(id);
	}

	@PutMapping("")
	@Override
	public DTO update(
		@Valid
		@RequestBody
		DTO dto) {
		return crudAbstractService.update(dto);
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable Long id) {
		crudAbstractService.delete(id);
	}

	@GetMapping("")
	@Override
	public Api<List<DTO>> list(
		@PageableDefault
		Pageable pageable) {
		return crudAbstractService.list(pageable);
	}
}
