package org.kernel360.simpleboard.common.crud;

import java.util.List;
import java.util.Optional;

import org.kernel360.simpleboard.common.Api;
import org.springframework.data.domain.Pageable;

public interface CRUDInterface<DTO> {

	DTO create(DTO dto);

	Optional<DTO> read(Long id);

	DTO update(DTO dto);

	void delete(Long id);

	Api<List<DTO>> list(Pageable pageable);
}
