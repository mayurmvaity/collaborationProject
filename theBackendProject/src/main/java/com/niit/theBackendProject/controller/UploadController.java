package com.niit.theBackendProject.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.model.Response;
import com.niit.theBackendProject.dao.UserDAO;

@RestController
@RequestMapping("/upload")
@PropertySource("classpath:config.properties")
public class UploadController {

	@Autowired 
	private UserDAO userDAO;
	
	/* new things added */
	@Autowired
	ServletContext req;
	
	// this is an absolute path since we are using two different servers based on project requirement 
	// we are able to upload the image at the desired location important thing to note is we are 
	// able to sent the multipart file from the front end that is written purely in angular js
	// the image could have been stored in the back end server but then we need to have many
	// request
	
	@Value("${imageBasePath}")
	private String imageBasePath;
	
	/*
	 * Using post mapping for uploading the file 
	 * 
	 * */
	
	@PostMapping("/profile-picture")
	public ResponseEntity<Response> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
		
		String message = null;

		// We would be using the USER_PROFILE as a prefix so that we can use other prefix 
		// for other kind of upload such as event which may have id auto-generated
		String fileName = "USER_PROFILE_" + id + ".png";			
		
		/** Newly added things **/
		String p1 = req.getRealPath("/");
		String p2 = req.getContextPath();
		
		String npath = "";
		
		/** Newly added things **/
		try {
			byte[] bytes = file.getBytes();
			npath=p1+"\\assets\\images\\"+fileName;
			
			// writing file to new path
			BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(npath)));  
			stream.write(bytes);  
			stream.close();
			
			
		}
		catch(Exception e) {
			System.out.println("Picture error = "+ e);
		}
		
		if(uploadFile(imageBasePath, fileName, file)){

			
			// String abpath = "D:\\\\workspace\\\\.metadata\\\\.plugins\\\\org.eclipse.wst.server.core\\\\tmp0\\\\wtpwebapps\\\\theBackendProject\\\\assets\\\\images\\\\";
			/*String dbfileName = abpath+fileName;
			System.out.println("dbfilename = "+ dbfileName);*/
			
			// update the picture id in the database table by using userDAO
			userDAO.updateUserProfile(fileName, id);
			 /** changes made here **/
			//userDAO.updateUserProfile(dbfileName, id);
			
			//in the response the filename of the new image will be send			
			return new ResponseEntity<Response>(new Response(1,fileName),HttpStatus.OK);			
		}
		else {
			message = "Failed to update the profile picture!";
			return new ResponseEntity<Response>(new Response(0,message),HttpStatus.NOT_FOUND);
		}		
		
	}

	/**
	 * 
	 * uploadFile method has three parameters
	 * directory - where to upload
	 * fileName - that will be used for naming the uploaded file
	 * file - the file to upload
	 *  
	 * */

	private boolean uploadFile(String directory, String fileName,  MultipartFile file) {		
		
		// Create the directory if does not exists
		if(!new File(directory).exists()) {
			new File(directory).mkdirs();
		}		
		
		try {			
			// transfer the file
			file.transferTo(new File(directory + fileName));
			// file uploaded successfully 
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
				
		return false;				
	}
	
	//To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }   
		
}