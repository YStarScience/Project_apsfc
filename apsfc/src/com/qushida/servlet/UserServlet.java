package com.qushida.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.qushida.po.User;
import com.qushida.service.UserService;
import com.qushida.service.impl.UserServiceImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		switch (action) {
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "regist":
			regist(request, response);
			break;
		case "modify":
			modify(request,response);
			break;
		default:
			break;
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name= request.getParameter("name");
		String pwd= request.getParameter("pwd");
		String realname= request.getParameter("realname");
		String age= request.getParameter("age");
		String card= request.getParameter("card");
		String address= request.getParameter("address");
		String phone= request.getParameter("phone");
		String email= request.getParameter("email");
		String code= request.getParameter("code");
		User user = new User(0,name,pwd,realname,null,age,card,address,phone,email,code,null);
		int i = userService.modify(user);
		if (i>0) {
			response.getWriter().write("<script>alert('修改成功，请登录');window.location.href='qiantai/login.jsp'</script>");
		}else{
			//设置响应的编码方式
			response.setContentType("text/html;charset=utf-8");
			//登录失败
			response.getWriter().write("<script>alert('修改失败，请重新注册');window.location.href='qiantai/center.jsp'</script>");
		}
		
	}

	protected void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//String qpwd = request.getParameter("qpwd");
		String realname = request.getParameter("realname");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String card = request.getParameter("card");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		User user = new User(0, name, pwd, realname, sex, age, card, address, phone, email, code, null);
		int i = userService.regist(user);
		if (i>0) {
			response.getWriter().write("<script>alert('注册成功，请登录');window.location.href='qiantai/login.jsp'</script>");
		}else{
			//设置响应的编码方式
			response.setContentType("text/html;charset=utf-8");
			//登录失败
			response.getWriter().write("<script>alert('注册失败，请重新注册');window.location.href='qiantai/reg.jsp'</script>");
		}
	}

	//退出
	 protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//移除session域中我们存放的信息
		//获取session
		request.getSession().removeAttribute("user");   //链式编程
		//跳转到登陆页
		response.getWriter().write("<script>window.parent.location.href='qiantai/login.jsp'</script>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		User user = userService.login(name, pwd);
		
		if (user!=null) {
			request.getSession().setAttribute("user", user);
			out.write("<script>alert('登录成功');window.location.href='qiantai/index.jsp'</script>");
		} else {
			out.write("<script>alert('登录失败');window.location.href='qiantai/login.jsp'</script>");
		}
	}

}
