package com.hks.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*10,	// 10MB
				maxFileSize = 1024*1024*50,			// 50MB
				maxRequestSize = 1024*1024*100)		// 100MB
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Directory where uploaded files will be saved,
	 * it's relative to the application directory.
	 */
	private static final String UPLOAD_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// gets absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save the uploaded files
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		
		// creates the save directory if it doesn't exist
		File fileSaveDir = new File(uploadFilePath);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory: " + fileSaveDir.getAbsolutePath());
		
		String fileName = null;
		// Get all the parts from request and write it to the file on the server
		for(Part part : request.getParts()) {
			fileName = getFileName(part);
			System.out.println("before part.write()"+fileName + " uploadFilePath:"+uploadFilePath);
			part.write(uploadFilePath + File.separator + fileName);
		}
		
		request.setAttribute("message", fileName + " File uploaded successfully!");
		getServletContext().getRequestDispatcher("/response.jsp").forward(request, response);
	}
	
	/*
	 * Utility method to get file name from HTTP header content-disposition 
	 */
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("contect-disposition header="+contentDisp);
		String[] tokens = contentDisp.split(";");
		System.out.println(Arrays.toString(tokens));
		for(String token : tokens) {
			System.out.println(token);
			if(token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length()-1);
			}
		}
		return "";
	}

}
