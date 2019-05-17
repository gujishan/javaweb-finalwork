package com.gjs.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Downloadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Downloadservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String filename=request.getParameter("filename");
		System.out.println(filename);
		//String folder = "/res";
//		response.addHeader("Content-type", "appllication-stream");
		response.setContentType(getServletContext().getMimeType(filename));
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		String fullFileName=getServletContext().getRealPath("/download/"+filename);
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		//byte[] buffer = new byte[1024];
		int b;
		while((b=in.read())!=-1) {
			out.write(b);
		}
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
