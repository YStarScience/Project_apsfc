package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import com.qushida.po.Type;
import com.qushida.service.TypeService;
import com.qushida.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private TypeService typeService = new TypeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//获取具体动作
		String action = request.getParameter("action");
		switch (action) {
		case "getAllType":
			getAllType(request, response);
			break;
		case "addType":
			addType(request, response);
			break;
		case "selectByID":
			selectByID(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "deleteType":
			deleteType(request, response);
			break;	
		case "forMenuAdd":
			forMenuAdd(request, response);
			break;
		default:
			break;
		}
	}
	
	//查询所有类别供添加菜单使用
	public void forMenuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用方法获取所有类别
		List<Type> typelist = typeService.getAllType();
		request.setAttribute("typelist", typelist);
		//转发到菜单添加页面
		request.getRequestDispatcher("admin/menus_add.jsp").forward(request, response);
		
		
	}

	//删除类别
	public void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int id1 = Integer.parseInt(id);
		int i = typeService.deleteType(id1);
		if (i>0) {
			//删除成功
			String  deletemsg = "<script>alert('删除成功')</script>";
			request.setAttribute("deletemsg", deletemsg);
			getAllType(request, response);
		} else {
			//失败
			String  deletemsg = "<script>alert('删除失败')</script>";
			request.setAttribute("deletemsg", deletemsg);
			getAllType(request, response);
		}
		
	}
	//更新操作
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int id1 = Integer.parseInt(id);
		String name = request.getParameter("name");
		//把获取的数据放到对象中
		Type type = new Type(id1,name);
		//调用service层方法
		int i = typeService.update(type);
		//对 i 进行判断
		if (i>0) {
			//更新成功
			String  updatemsg = "<script>alert('修改成功')</script>";
			request.setAttribute("updatemsg", updatemsg);
			getAllType(request, response);
		}else{
			//更新失败
			String  updatemsg = "<script>alert('修改失败')</script>";
			request.setAttribute("updatemsg", updatemsg);
			getAllType(request, response);
		}
	}
	//根据ID查询
	public void selectByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取待修改类别的id
		String id = request.getParameter("id");
		//把String类型的id转成int
		int id1 = Integer.parseInt(id);
		//调用service层的查询
		Type type = typeService.selectByID(id1);
		//把type放到request域中
		request.setAttribute("type", type);
		//转发页面请求到类别更新页面
		request.getRequestDispatcher("admin/type_update.jsp").forward(request, response);
		
		
	}
	public void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面的类别名称
		String name = request.getParameter("name");
		int i = typeService.addType(name);
		if(i>0){
			//添加成功
			String  addmsg = "<script>alert('添加成功')</script>";
			request.setAttribute("addmsg", addmsg);
			getAllType(request, response);
		}else{
			//失败
			response.getWriter().write("<script>alert('添加失败，请重新添加');window.location.href='admin/type_add.jsp'</script>");
			
		}
		
	}
	//获取所有的类别
	public void getAllType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//调用业务方法
		
		List<Type> list =typeService.getAllType();
		//把查询到的类别列表放到request域中
		request.setAttribute("typelist", list);
		//转发到用来显示类别的页面type.jsp（只发送一次请求）
		//获得调度器并执行转发
		request.getRequestDispatcher("admin/type.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
