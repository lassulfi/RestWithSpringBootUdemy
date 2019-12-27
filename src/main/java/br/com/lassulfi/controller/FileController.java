package br.com.lassulfi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lassulfi.data.vo.UploadFileResponseVO;
import br.com.lassulfi.service.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/upload")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		String filename = fileStorageService.saveFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/file/v1/download")
				.path(filename)
				.toUriString();
		
		return new UploadFileResponseVO(filename, fileDownloadUri, file.getContentType(), file.getSize());
	}
}
