package com.qushida.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.LoginConfig;

import com.qushida.po.Admin;
import com.qushida.service.AdminService;
import com.qushida.service.MenuService;
import com.qushida.service.impl.AdminServiceImpl;
import com.qushida.service.impl.MenuServiceImpl;
import com.qushida.util.Page;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private AdminService adminService=new AdminServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取前台的请求类型action
		String action = request.getParameter("action");
		switch(action){
		case"login":
			login(request,response);
			break;
		case"logout":
			logout(request,response);
			break;	
		case"updateAdminPwd":
			updateAdminPwd(request,response);
			break;	
		default:
			break;
		}
	}
	public void updateAdminPwd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		int i = adminService.updateAdminPwd(name, pwd);
		if (i>0) {
			//修改成功
			response.getWriter().write("<script>alert('修改成功，请重新登录')</script>");
			logout(request, response);
		}else{
			response.getWriter().write("<script>alert('修改失败');window.location.href='admin/admin_update.jsp'</script>");
		}
		
	}
	//退出
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//移除session域中我们存放的信息
		//获取session
		request.getSession().removeAttribute("admin");   //链式编程
		//跳转到登陆页
		response.getWriter().write("<script>window.parent.location.href='admin/index.jsp'</script>");
	}
	//登录
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//获取页面的参数用户名和密码
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//调用业务
		
		Admin admin = adminService.login(name,pwd);
		//对返回值admin进行判断，如果admin不为null，说明存在这个管理员，那么登陆成功，反之登录失败
		if(admin!=null){
			
			/*登录成功之后显示所有菜单*/
			MenuService menuService = new MenuServiceImpl();
			Page page = new Page();
			page.setCurPage(1);
			page.setPageNumber(3);
			Page pageCurrent = menuService.getMenuByPage(page);
			HttpSession session = request.getSession();
			//将获取到得首页菜单数据放到session域
			session.setAttribute("pageCurrent", pageCurrent);
			//把当前登录的管理员放在session中
			session.setAttribute("admin",admin);
			//重定向到登录后的主页面
			response.sendRedirect(request.getContextPath()+"/admin/main.jsp");
		}else{
			//设置响应的编码方式
			response.setContentType("text/html;charset=utf-8");
			//登录失败
			response.getWriter().write("<script>alert('用户名或密码错误，请重新登录');window.location.href='admin/index.jsp'</script>");
			
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
