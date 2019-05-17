package com.gjs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gjs.LoginDao.UserDao;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyc  = request.getParameter("verifycode");
		String svc =(String) request.getSession().getAttribute("sessionverify");
		String psw =new UserDao().findUser(username);
		if(!svc.equalsIgnoreCase(verifyc)){
			request.setAttribute("username", username);
			request.setAttribute("msg1", "* 验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(psw ==null){
			request.setAttribute("username", username);
			request.setAttribute("msg2", "* 用户不存在");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(psw!=null&&!psw.equals(password)){
			request.setAttribute("username", username);
			request.setAttribute("msg3", "* 密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(psw.equals(password)){
			request.setAttribute("msg", "用户："+username+"登陆成功，您可以下载和上传");
			request.getRequestDispatcher("/updown.jsp").forward(request, response);
			//response.setHeader("Refresh","1;url=welcome.jsp");
		}
		
	}
}
