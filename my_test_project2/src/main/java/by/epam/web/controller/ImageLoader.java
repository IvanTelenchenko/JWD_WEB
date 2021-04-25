package by.epam.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import by.epam.web.service.ServiceCar;
import by.epam.web.service.ServiceProvider;
import by.epam.web.validator.Validator;

/**
 * Servlet implementation class ImageLoader
 * This class works with image (upload to directory)
 */
@WebServlet( urlPatterns = "/image/*" )
@MultipartConfig
public class ImageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ImageLoader.class);
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String IMAGE_DIRECTORY = "image.directory";
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String ATTRIBUTE_PHOTO = "photo";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageLoader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getPathInfo().substring(1);
		String directory = getServletContext().getInitParameter(IMAGE_DIRECTORY);
		Path path = Paths.get(directory, filename);
		response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
	    response.setHeader(CONTENT_LENGTH, String.valueOf(Files.size(path)));
	    Files.copy(path, response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String directory = getServletContext().getInitParameter(IMAGE_DIRECTORY);
		String uniqueNamePhoto;
		
//		System.out.println("part size > " + request.getPart("photo").getSize());
		
		Validator validator = new Validator();
		
		if(request.getPart(ATTRIBUTE_PHOTO).getSize() <= 0 ) {
//			System.out.println("null!!!");
			uniqueNamePhoto = null;
		}else if(!validator.valiadateImage(request.getPart(ATTRIBUTE_PHOTO))){
			Part photo = request.getPart(ATTRIBUTE_PHOTO);
			String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
			uniqueNamePhoto = fileName;
		}else {
			Part photo = request.getPart(ATTRIBUTE_PHOTO);
			uniqueNamePhoto = getUniqueName(photo);
			photo.write(directory + uniqueNamePhoto);
		}
		
		request.setAttribute("photoName", uniqueNamePhoto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Controller");
		dispatcher.forward(request, response);
		
	}
	
	
	/**
	 * The file image gets unique name
	 * @param photo {@link Part} file
	 * @return returns {@link String} with a unique file name
	 * */
	
	private static String getUniqueName(Part photo) {
		
		String uniqueNamePhoto;
		
		UUID uuid = UUID.randomUUID();
		String uniq = uuid.toString();
		
		String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
		
//		System.out.println(fileName);
		
		String arr[] = fileName.split("\\.");
		String extension = arr[1];
		
		uniqueNamePhoto = uniq+"."+extension;
		
		return uniqueNamePhoto;
	}
}