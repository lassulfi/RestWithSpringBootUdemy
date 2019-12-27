package br.com.lassulfi.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.lassulfi.config.FileStorageConfig;
import br.com.lassulfi.exceptions.FileStorageException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
				.toAbsolutePath()
				.normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will de saved", e);
		}
	}
	
	public String saveFile(MultipartFile file) {
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(filename.contains("..")) {
				throw new FileStorageException("Filename contains invalid characters sequence" + filename);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(filename);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return filename;
		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + filename + ". Please, try again", e);
		}
	}
}
