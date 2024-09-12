package com.microservice.products.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import jakarta.annotation.PostConstruct;

@Service
public class FileSystemStorageService implements StorageService {

	
	
	private String mediaLocation="mediafiles";
	
	private Path path;
	
	@Override
	@PostConstruct
	public void init() throws IOException {
		// TODO Auto-generated method stub
		path = 	Paths.get(mediaLocation);
		Files.createDirectories(path);
	}

	@Override
	public String store(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
		
		if(file.isEmpty()) {
			throw new RuntimeException("Archivo vacio!");
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String filename = file.getOriginalFilename();
		filename = filename.replace(" ", "_");
		filename = timestamp.getTime()+filename;
		Path destino = path.resolve(Paths.get(filename)).normalize().toAbsolutePath();
		
		try(InputStream inputstream =file.getInputStream()) {
			Files.copy(inputstream, destino, StandardCopyOption.REPLACE_EXISTING);
			
		}
		return filename;
		}catch(IOException ex) {
			throw new RuntimeException("failed: "+ex);
		}
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		try {
		Path file = path.resolve(filename);
		Resource resource = new UrlResource(file.toUri());
			
		if(resource.exists() || resource.isReadable()) {
			return resource;
		}else {
			throw new RuntimeException("no se puede leer el archivo"+filename);
		}
		}catch(MalformedURLException e) {
			throw new RuntimeException("no se puede leer el archivo"+filename);
		}
	}
	
}
