package com.qushida.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qushida.po.Menu;
import com.qushida.po.ShoppingCar;
import com.qushida.service.MenuService;
import com.qushida.service.impl.MenuServiceImpl;

/**
 * Servlet implementation class S
 */
@WebServlet("/ShoppingCarServlet")
public class ShoppingCarServlet extends HttpServlet {
	private MenuService menuService = new MenuServiceImpl();
	private static final long serialVersionUID = 1L;
	private Object attribute;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取action的值
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			add(request, response);
			break;
		case "removeAll":
			removeAll(request, response);
			break;
		case "removeOne":
			removeOne(request, response);
			break;
		default:
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//添加到购物车
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//数据存到哪--》session域中
		//数据是什么格式-->List<?>-->类-->包含一条信息
		//得到单击菜品的信息-->构建成ShoppingCar的一个对象-->menuId、菜品名称、价格、数量
		//从session中获取购物车信息
		HttpSession session = request.getSession();
		List<ShoppingCar> carList = (List<ShoppingCar>) session.getAttribute("carList");
		//获取menuId-->
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		boolean flag = true;
		if (carList==null) {
			//session中无购物车信息
			carList = new ArrayList<>();
		}else {
			//已经存在购物车
			//遍历购物车-->得到购物车的每个条目，如果有一个条目的菜品id就是menuId-->数量+1
			for(ShoppingCar carItem:carList){
				//carItem
				if (carItem.getMenuId()==menuId) {
					//购物车中存在此商品
					carItem.setSums(carItem.getSums()+1);
					flag = false;
					break;
				}
			}
		}
		if(flag){
			//调用menuSevice中的selectById得到此菜单的所有信息
			Menu menu = menuService.selectById(menuId);
			//菜名
			String name = menu.getName();
			//价格
			float price = Float.parseFloat(menu.getPrice1());
			//构建一个购物车条目信息
			ShoppingCar shoppingCar = new ShoppingCar(menuId, name, price, 1);
			carList.add(shoppingCar);
		}
		//设置到session
		session.setAttribute("carList", carList);
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
	}
	//从购物车中删除一个条目
	protected void removeOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取来源
		String from = request.getParameter("from");
		
		String path = request.getContextPath()+"/qiantai/index.jsp";//重定向的路径
		if (from!=null) {
			path = request.getContextPath()+"/qiantai/shoppingcar.jsp";//重定向的路径
		}	
		//得到要删除的商品的id
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		//获取session
		HttpSession session = request.getSession();
		//获取购物车的信息
		List<ShoppingCar> carList = (List<ShoppingCar>) session.getAttribute("carList");
		//遍历购物车信息-->找到要删除的条目（商品id）
		for(ShoppingCar carItem:carList){
			//carItem-->menId与传过来的menuId比较
			if (menuId==carItem.getMenuId()) {
				//当前的条目就是要删除的
				carList.remove(carItem);
				break;
			}
		}
		//需不需要将新的购物车设置到session中
		//重定向
		
		
		response.sendRedirect(path);
		
	}
	
	//清空购物车
	protected void removeAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//移除session中的carList
		session.removeAttribute("carList");
		//重定向到qiantai/index.jsp
		response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp");
	}
	

}
