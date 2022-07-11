/**
* Created by Roy Morocho.
* User: steve
* Date: 25 jun 2022
* Time: 13:44:58
*/
package com.vncarca.arcasys.globalService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GlobalService<DTO, ENTITY> {
	ENTITY convertToEntity(DTO dto);
	 DTO convertToDto(ENTITY entity);
}
