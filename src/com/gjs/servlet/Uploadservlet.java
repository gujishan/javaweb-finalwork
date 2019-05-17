package com.gjs.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gjs.UploadListener.UploadListener;
import com.gjs.UploadStatus.UploadStatus;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Uploadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploadservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 禁止浏览器缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);

		UploadStatus status = (UploadStatus) request.getSession(true).getAttribute("uploadStatus"); // 从Session读取数据

		if (status == null) {

			response.getWriter().println("没有上传的信息");
			return;
		}

		// 各种上传信息
		long startTime = status.getStartTime();
		long currentTime = System.currentTimeMillis();
		long time = (currentTime - startTime) / 1000 + 1;

		double velocity = ((double) status.getBytesRead()) / (double) time;
		double totalTime = status.getContentLength() / velocity;
		double timeLeft = totalTime - time;
		int percent = (int) (100 * (double) status.getBytesRead() / (double) status.getContentLength());
		double length = ((double) status.getBytesRead()) / 1024 / 1024;
		double totalLength = ((double) status.getContentLength()) / 1024 / 1024;

		String value = percent + "||" + length + "||" + totalLength + "||" + velocity + "||" + time + "||" + totalTime
				+ "||" + timeLeft + "||" + status.getItems();

		response.getWriter().println(value); // 输出给浏览器进度条
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UploadStatus status = new UploadStatus();
		UploadListener listener = new UploadListener(status);

		request.getSession(true).setAttribute("uploadStatus", status); // 把状态放到Session里

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory()); // 解析
		upload.setProgressListener(listener);

		try {
			List itemList = upload.parseRequest(request); // 提交的所有参数

			for (Iterator it = itemList.iterator(); it.hasNext();) {

				FileItem item = (FileItem) it.next();

				if (item.isFormField()) { // 如果是表单数据

					System.out.println("FormField:" + item.getFieldName() + "=" + item.getString());
				} else { // 否则是文件

					System.out.println("File:" + item.getName());

					String fileName = item.getName().replace("/", "\\");
					fileName = fileName.substring(fileName.lastIndexOf("\\"));

					File saved = new File("D:\\upandload", fileName);

					saved.getParentFile().mkdirs(); // 保证路径存在

					InputStream ins = item.getInputStream();
					OutputStream ous = new FileOutputStream(saved);

					// 缓存写入
					byte[] tmp = new byte[999999999];
					int len = -1;
					while ((len = ins.read(tmp)) != -1) {
						ous.write(tmp, 0, len);

					}

					ous.close();
					ins.close();
					response.getWriter().println("已保存文件:" + saved);

				}

			}

		} catch (Exception e) {

			response.getWriter().println("上传发生错误: " + e.getMessage());
		}

	}

}
