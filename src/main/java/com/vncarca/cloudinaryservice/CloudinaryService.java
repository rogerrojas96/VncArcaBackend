package com.vncarca.cloudinaryservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
    
    private Cloudinary cloudinary;
    private Map<String, String> valuesmap = new HashMap<>();

    public CloudinaryService(){
        valuesmap.put("cloud_name", "doko2wtms");
        valuesmap.put("api_key", "595988646359762");
        valuesmap.put("api_secret", "VDo-04yLfKjDccg8CGosg1JGitg");
        cloudinary = new Cloudinary(valuesmap);
    }


    public Map<?,?> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<?,?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public Map<?,?> delete(String idImagen) throws IOException {
        Map<?,?> result = cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
