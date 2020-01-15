package com.qushida.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.po.User;
import com.qushida.service.OrderService;
import com.qushida.service.impl.OrderServiceImpl;
import com.qushida.util.Page;

@WebServlet("/UserOrderServlet")
public class UserOrderServlet extends HttpServlet {
	private OrderService orderService = new OrderServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		try {
			Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void getAllOrderByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 声明分页所用的变量
		String id = request.getParameter("userid");
		//String de =request.getParameter("del");		
		int curPage1 = 0;
		int pageNumber1 = 0;
		

		// 获取分页所要信息
		String curPage = request.getParameter("curPage");// 当前页
		String pageNumber = request.getParameter("pageNumber");

		// 对curPage进行判断：如果为null，则为第一页，反之为curPage页
		if (curPage == null || "".equals(curPage)) {
			curPage1 = 1;
		} else {
			curPage1 = Integer.parseInt(curPage);
		}
		if (pageNumber == null || "".equals(pageNumber)) {
			pageNumber1 = 3;
		} else {
			pageNumber1 = Integer.parseInt(pageNumber);
		}
		// 将当前页和每页的条数封装到page对象里面
		Page page = new Page();
		page.setCurPage(curPage1);
		page.setPageNumber(pageNumber1);
		
		Page pageCurrent=null;
//       int d=Integer.parseInt(de);
//		if (d==0||d==1) {
//			pageCurrent = orderService.queryUserOrderByPage(page,null,Integer.parseInt(id),d);
//		}else {
//			pageCurrent = orderService.queryUserOrderByPage(page,null,Integer.parseInt(id),3);
//		}	
			
		pageCurrent = orderService.queryUserOrderByPage(page,null,Integer.parseInt(id),3);
		
			 
		
		
		// 把从数据库查询到的数据放到request域中
		request.setAttribute("pageCurrent", pageCurrent);
		// 转发到menus.jsp
		request.getRequestDispatcher("qiantai/order.jsp").forward(request, response);

	}

	protected void queryOrderByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				String de = request.getParameter("del");
				System.out.println("+++++++++++++++++++++++++++++++"+de);
		// 声明分页所用的变量
				String userid = request.getParameter("userid");
				String menuName = request.getParameter("menuname");
				String date = request.getParameter("date");
				
				OrderCriteria criteria = new OrderCriteria();
				
				criteria.setMenuName(menuName);
				criteria.setDate(date);
				int curPage1 = 0;
				int pageNumber1 = 0;
				

				// 获取分页所要信息
				String curPage = request.getParameter("curPage");// 当前页
				String pageNumber = request.getParameter("pageNumber");

				// 对curPage进行判断：如果为null，则为第一页，反之为curPage页
				if (curPage == null || "".equals(curPage)) {
					curPage1 = 1;
				} else {
					curPage1 = Integer.parseInt(curPage);
				}
				if (pageNumber == null || "".equals(pageNumber)) {
					pageNumber1 = 3;
				} else {
					pageNumber1 = Integer.parseInt(pageNumber);
				}
				// 将当前页和每页的条数封装到page对象里面
				Page page = new Page();
				page.setCurPage(curPage1);
				page.setPageNumber(pageNumber1);
				

				Page pageCurrent=null;
				int d=3;
				HttpSession session=request.getSession();
				if(de!=null&&de.equals("")==false)
					d=Integer.parseInt(de);
				System.out.println(d);
				if (d==0||d==1) {
					
					
					session.setAttribute("del", d);
					//request.setAttribute("del", de);
					System.out.println(de);
					pageCurrent = orderService.queryUserOrderByPage(page,criteria,Integer.parseInt(userid),d);
				}else {
					session.setAttribute("del", d);
					pageCurrent = orderService.queryUserOrderByPage(page,criteria,Integer.parseInt(userid),3);
				}
				// 调用分页查询方法
					// pageCurrent = orderService.queryUserOrderByPage(page,criteria,Integer.parseInt(userid));
				
				// 调用分页查询方法
				
				// 把从数据库查询到的数据放到request域中
				request.setAttribute("pageCurrent", pageCurrent);
				request.setAttribute("criteria", criteria);
				// 转发到menus.jsp
				request.getRequestDispatcher("qiantai/order.jsp").forward(request, response);


	}
	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//确定用户是否登陆
		User user = (User)session.getAttribute("user");
		if (user==null) {
			//未登陆
			out.write("<script>alert('请登陆后下单');window.location.href='qiantai/login.jsp'</script>");
			
		}else {
			//已登录
			List<ShoppingCar> carList = (List<ShoppingCar>)session.getAttribute("carList");
			int userId = user.getId();
			boolean result = orderService.addOrder(carList, String.valueOf(userId));
			if (result) {
				out.write("<script>alert('下单成功');window.location.href='qiantai/index.jsp'</script>");
			}else {
				out.write("<script>alert('下单失败');window.location.href='qiantai/index.jsp'</script>");
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
