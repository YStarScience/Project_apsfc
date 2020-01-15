package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.qushida.po.Menu;
import com.qushida.po.Type;
import com.qushida.service.MenuService;
import com.qushida.service.TypeService;
import com.qushida.service.impl.MenuServiceImpl;
import com.qushida.service.impl.TypeServiceImpl;
import com.qushida.util.Page;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	//创建service层对象
	private	MenuService menuService = new MenuServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		switch (action) {
		case "getMenuByPage":
			getMenuByPage(request, response);
			break;
		case "addMenu":
			addMenu(request, response);
			break;
		case "deleteMenu":
			deleteMenu(request, response);
			break;
		case "selectById":
			selectById(request, response);
			break;
		case "updateMenu":
			updateMenu(request, response);
			break;	
		default:
			break;
		}
	}
	//修改菜单
	public void updateMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int id1 = Integer.parseInt(id);
		String name = request.getParameter("name");
		String typeid = request.getParameter("typeid");
		String burden = request.getParameter("burden");
		String brief = request.getParameter("brief");
		String price = request.getParameter("price");
		String price1 = request.getParameter("price1");
		//把获取的数据放到对象中
		Menu menu = new Menu(id1, name, typeid, burden, brief, price, null, price1, null, null);
		System.out.println();
		//调用service层方法
		int i = menuService.updateMenu(menu);
		//对 i 进行判断
		if (i>0) {
			//更新成功
			String  updatemsg = "<script>alert('修改成功');</script>";
			request.setAttribute("updatemsg", updatemsg);
			getMenuByPage(request, response);
		}else{
			//更新失败
			String  updatemsg = "<script>alert('修改失败');</script>";
			request.setAttribute("updatemsg", updatemsg);
			getMenuByPage(request, response);
		}
		
	}
	public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取待修改菜单的id和其他相关信息
		String id = request.getParameter("id");
		//把String类型的id转成int
		int id1 = Integer.parseInt(id);
		//调用service层的查询
		Menu menu = menuService.selectById(id1);
		//调用方法获取所有类别
		TypeService typeService = new TypeServiceImpl();		
		List<Type> typelist = typeService.getAllType();
		//转发菜单和类型列表到修改页面
		//把数据放到request域中
		request.setAttribute("typelist", typelist);
		request.setAttribute("menu", menu);
		//转发页面请求到类别更新页面
		request.getRequestDispatcher("admin/menus_update.jsp").forward(request, response);		
	}
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int id1 = Integer.parseInt(id);
		int i = menuService.deleteMenu(id1);
		if (i>0) {
			//删除成功
			String  deletemsg = "<script>alert('删除成功');</script>";
			request.setAttribute("deletemsg", deletemsg);
			getMenuByPage(request, response);
		} else {
			//失败
			String  deletemsg = "<script>alert('删除失败');</script>";
			request.setAttribute("deletemsg", deletemsg);
			getMenuByPage(request, response);
		}
		
	}
	//添加菜单
	public void addMenu(HttpServletRequest request, HttpServletResponse response) {
		//借助SmartUpload完成图片是的上传
		//1.创建SmartUpload对象
		SmartUpload smartUpload = new SmartUpload();
		//2.调用初始化方法
		try {
			smartUpload.initialize(getServletConfig(), request, response);
			//3.smartUpload上传准备
			smartUpload.upload();
			//4.获取上传的文件并获取文件名
			SmartFile file = smartUpload.getFiles().getFile(0);
			String fileName = file.getFileName();
			String imgpath = "img/"+fileName;
			//获取其他属性
			String name = smartUpload.getRequest().getParameter("name");
			String typeid = smartUpload.getRequest().getParameter("typeid");
			String burden = smartUpload.getRequest().getParameter("burden");
			String brief = smartUpload.getRequest().getParameter("brief");
			String price = smartUpload.getRequest().getParameter("price");
			String price1 = smartUpload.getRequest().getParameter("price1");
			//封装到对象中
			Menu menu = new Menu(0, name, typeid, burden, brief, price, null, price1, null, imgpath);
			
			//调用service层添加方法
			int i = menuService.addMenu(menu);
			if (i>0) {
				//添加成功
				//将文件保存在指定的目录下
				smartUpload.save("/img");
				//smartUpload.save("E:\\");
				String addmsg = "<script>alert('添加成功');</script>";
				request.setAttribute("addmsg", addmsg);
				getMenuByPage(request,response);
			} else {
				//添加失败
				response.getWriter().write("<script>alert('添加失败');window.location.href='TypeServlet?action=forMenuAdd'</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	//分页查询
	public void getMenuByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//声明分页所用的变量
		int curPage1 = 0;
		int pageNumber1 = 0;
		//获取分页所要的信息
		String curPage = request.getParameter("curPage");//当前页
		String pageNumber = request.getParameter("pageNumber");//每页条数
		//对curPage进行判断；如果为null，则为第一页，反之为curPage页
		if(curPage == null){
			curPage1 = 1;
		}else {
			curPage1 = Integer.parseInt(curPage);
		}
		if(pageNumber == null){
			pageNumber1 = 3;
		}else {
			pageNumber1 = Integer.parseInt(pageNumber);
		}
		//将当前页和每页条数封装到page对象中
		Page page = new Page();
		page.setCurPage(curPage1);
		page.setPageNumber(pageNumber1);
		
		//调用分页查询方法
		Page pageCurrent = menuService.getMenuByPage(page);
		//把从数据库查询到的数据放到request域中
		request.setAttribute("pageCurrent", pageCurrent);
		//转发到menus.jsp
		request.getRequestDispatcher("admin/menus.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
