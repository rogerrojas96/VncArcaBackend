/**
* Created by Roy Morocho.
* User: steve
* Date: 25 jun 2022
* Time: 13:44:58
*/
package com.vncarca.arcasys.globalService;

public interface GlobalService<DTO, ENTITY> {

	DTO convertToDto(ENTITY entity);

	ENTITY convertToEntity(DTO dto);
}
