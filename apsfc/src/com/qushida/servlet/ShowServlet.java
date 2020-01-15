package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushida.po.Menu;
import com.qushida.po.Type;
import com.qushida.service.MenuService;
import com.qushida.service.TypeService;
import com.qushida.service.impl.MenuServiceImpl;
import com.qushida.service.impl.TypeServiceImpl;
import com.qushida.util.Page;
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenuService menuService=new MenuServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html;charset=utf-8");
		
		
		
		int id1 = 0;
		String id = request.getParameter("id");
		id1 = Integer.parseInt(id);
		Menu menu = menuService.selectById(id1);
		TypeService typeService = new TypeServiceImpl();
		Type type = typeService.selectByID(Integer.parseInt(menu.getTypeid()));
		//把type放到request域
		request.setAttribute("menu", menu);
		request.setAttribute("type", type);
		//转发到更新页面(第一定位页面，第二forward前进)
		request.getRequestDispatcher("qiantai/show.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
