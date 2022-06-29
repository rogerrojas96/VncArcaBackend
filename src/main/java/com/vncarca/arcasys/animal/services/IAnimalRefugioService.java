package com.vncarca.arcasys.animal.services;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.vncarca.arcasys.animal.dto.AnimalRefugioRequest;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import com.vncarca.util.Response;

public interface IAnimalRefugioService {
	List<AnimalRefugioResponse> getAllAnimales(Pageable pageable);
	List<AnimalRefugioResponse> getAnimalesNoAdoptados(Pageable pageable);
	AnimalRefugio getAnimalEntityPorId(Long idAnimal);
	Response<AnimalRefugioResponse> guardarAnimal(AnimalRefugioRequest animal, MultipartFile multipartFile);
	Response<AnimalRefugioResponse>  actualizarAnimal(AnimalRefugioRequest animal, MultipartFile multipartFile, Long idAnimal);
	AnimalRefugioResponse getAnimalPorId(Long idAnimal);
	Response<AnimalRefugioResponse> eliminarAnimal(Long idAnimal);
	AnimalRefugioResponse getinfoDto(AnimalRefugio a);
}
