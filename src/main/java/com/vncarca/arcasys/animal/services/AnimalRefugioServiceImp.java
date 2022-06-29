package com.vncarca.arcasys.animal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vncarca.arcasys.animal.dto.AnimalRefugioRequest;
import com.vncarca.arcasys.animal.dto.AnimalRefugioResponse;
import com.vncarca.arcasys.animal.mapper.AnimalRefugioMapper;
import com.vncarca.arcasys.animal.model.AnimalRefugio;
import com.vncarca.arcasys.animal.repository.AnimalRefugioRepository;
import com.vncarca.cloudinaryservice.CloudinaryService;
import com.vncarca.util.Response;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@Transactional
public class AnimalRefugioServiceImp implements IAnimalRefugioService {

	@Autowired
	private AnimalRefugioRepository animalRepository;

	@Autowired
	private CloudinaryService cloudinaryService;


	@Override
	public List<AnimalRefugioResponse> getAllAnimales(Pageable pageable) {
		Page<AnimalRefugio> animales = animalRepository.findAll(pageable);
		return toListResponse(animales);
	}


	@Override
	public List<AnimalRefugioResponse> getAnimalesNoAdoptados(Pageable pageable) {
		Page<AnimalRefugio> animales = animalRepository.getAnimalesPorestadoAdopcion(false, pageable);
		return toListResponse(animales);
	}


	@Override
	public AnimalRefugio getAnimalEntityPorId(Long idAnimal) {
		return animalRepository.findById(idAnimal).orElse(null);
	}


	@Override
	public Response<AnimalRefugioResponse> guardarAnimal(AnimalRefugioRequest request, MultipartFile multipartFile){
		HttpStatus status = HttpStatus.CREATED;
		AnimalRefugioResponse response = null;
		try{
			AnimalRefugio animal = uploadAndGetAnimal(AnimalRefugioMapper.toAnimal(request), multipartFile);
			if(animal != null)
				response = AnimalRefugioMapper.toResponse(animalRepository.save(animal));
			else
				status = HttpStatus.BAD_REQUEST;
		}catch(IOException e){
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new Response<>(response, status);
	}


	@Override
	public Response<AnimalRefugioResponse> actualizarAnimal(AnimalRefugioRequest request, MultipartFile multipartFile,
			Long idAnimal) {
		HttpStatus status = HttpStatus.OK;
		AnimalRefugioResponse response = null;
		AnimalRefugio animal = animalRepository.findById(idAnimal).orElse(null);
		if(animal != null){
			try{
				String idImagenEliminar = animal.getIdImagenAnimalCld();
				animal = uploadAndGetAnimal(AnimalRefugioMapper.toAnimal(request), multipartFile);
				if(animal != null){
					cloudinaryService.delete(idImagenEliminar);
					animal.setId(idAnimal);
					response = AnimalRefugioMapper.toResponse(animalRepository.save(animal));
				}else
					status = HttpStatus.BAD_REQUEST;
			}catch(IOException e){
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else{
			status = HttpStatus.NOT_FOUND;
		}
		return new Response<>(response, status);
	}


	@Override
	public AnimalRefugioResponse getAnimalPorId(Long idAnimal) {
		AnimalRefugio animal = getAnimalEntityPorId(idAnimal);
		if(animal != null){
			return AnimalRefugioMapper.toResponse(animal);
		}
		return null;
	}


	@Override
	public Response<AnimalRefugioResponse> eliminarAnimal(Long idAnimal) {
		HttpStatus status = HttpStatus.OK;
		AnimalRefugioResponse response = null;
		AnimalRefugio animal = animalRepository.findById(idAnimal).orElse(null);
		if(animal != null){
			try{
				cloudinaryService.delete(animal.getIdImagenAnimalCld());
				response = AnimalRefugioMapper.toResponse(animal);
				response.setUrlImagenAnimal("");
				animalRepository.deleteById(idAnimal);;
			}catch(IOException e){
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else{
			status = HttpStatus.NOT_FOUND;
		}
		return new Response<>(response, status);
	}

	
	@Override
	public AnimalRefugioResponse getinfoDto(AnimalRefugio a) {
		AnimalRefugioResponse adto = new AnimalRefugioResponse();
		adto.setId(a.getId());
		adto.setUrlImagenAnimal(a.getUrlImagenAnimalCld());
		adto.setNombre(a.getNombre());
		return adto;
	}


	/*
	 * MÉTODOS AUXILIARES: --------------------------------------------------------------------
	 */
	private List<AnimalRefugioResponse> toListResponse(Page<AnimalRefugio> animales){
		List<AnimalRefugioResponse> response = new ArrayList<>();
		for (AnimalRefugio animal : animales) {
			response.add(AnimalRefugioMapper.toResponse(animal));
		}
		return response;
	}


	private AnimalRefugio uploadAndGetAnimal(AnimalRefugio animal, MultipartFile multipartFile) throws IOException{
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if(bi != null){
			Map<?,?> result = cloudinaryService.upload(multipartFile);
			animal.setNombreImagenAnimalCld((String)result.get("original_filename"));
			animal.setUrlImagenAnimalCld((String)result.get("url"));
			animal.setIdImagenAnimalCld((String)result.get("public_id"));
			return animal;
		}
		return null;
	}


	/*@Autowired
	private AnimalRefugioRepository animalRepository;

	@Override
	public Page<AnimalRefugio> findAll(Pageable pageable) {
		return animalRepository.findAll(pageable);
	}

	@Override
	public AnimalRefugio save(AnimalRefugio animal) {
		return animalRepository.save(animal);
	}

	@Override
	public AnimalRefugio findById(Long id) {
		return animalRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				"Animal con ID: " + id.toString() + " no existe en el servidor"));
	}

	@Override
	public void delete(Long id) {
		animalRepository.deleteById(id);
	}

	@Override
	public List<AnimalRefugio> findAll() {

		return animalRepository.findAll();
	}
	*/
}
