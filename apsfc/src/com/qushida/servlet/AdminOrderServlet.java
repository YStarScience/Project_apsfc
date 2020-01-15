package com.qushida.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushida.po.OrderCriteria;
import com.qushida.service.OrderService;
import com.qushida.service.impl.OrderServiceImpl;
import com.qushida.util.Page;
import com.qushida.vo.OrderStatistic;

@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 属性注入
	private OrderService orderService = new OrderServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");// getAllOrderByPage
		// 调用名称为action的方法且参数HttpServletRequest，HttpServletResponse
		try {
			Method method = getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 分页查询所有订单
	protected void getAllOrderByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.curPage
		String curPage = request.getParameter("curPage");
		System.out.println("in getAllOrderByPage-----------------------------------------------");
		System.out.println(curPage);
		if (curPage == null || "".equals(curPage)) {
			curPage = "1";
		}
		// 2.pageNumber
		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null || "".equals(pageNumber)) {
			pageNumber = "3";
		}
		// Page->curPage,pageNumber
		Page page = new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));
		// 调用OrderService-》queryOrderByPage(Page)
		Page pageCurrent = orderService.queryOrderByPage(page,null);

		request.setAttribute("pageCurrent", pageCurrent);
		// 请求转发到admin/order.jsp
		System.out.println("in pageCurrent-----------------------------------------------");
		System.out.println(pageCurrent.getCurPage());
		request.getRequestDispatcher("admin/order.jsp").forward(request, response);

	}

	// 取消订单
	protected void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取订单id
		String orderId = request.getParameter("orderId");
		String curPage =request.getParameter("curPage");
		// 调用方法
		boolean deleteRes = orderService.deleteOrderById(Integer.parseInt(orderId));
		String deleteMsg = "<script>alert('删除订单失败')</script>";
		if (deleteRes) {
			// 修改成功 配送成功
			deleteMsg = "<script>alert('删除订单成功')</script>";
		}
		// 设置到request域中
		request.setAttribute("deleteMsg", deleteMsg);
		request.removeAttribute("curPage");
		request.setAttribute("curPage", curPage);
		getAllOrderByPage(request, response);
	}

	// 修改订单
	protected void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取订单id
		String orderId = request.getParameter("orderId");
		// 调用方法
		boolean updateRes = orderService.updateOrderById(Integer.parseInt(orderId));
		String updateMsg = "<script>alert('配送失败')</script>";
		if (updateRes) {
			// 修改成功 配送成功
			updateMsg = "<script>alert('配送成功')</script>";
		}
		// 设置到request域中
		request.setAttribute("updateMsg", updateMsg);
		getAllOrderByPage(request, response);
	}

	// 根据条件分页查询订单
	protected void queryOrderByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取查询信息
		String userId = request.getParameter("userid");
		String menuName = request.getParameter("menuname");
		String date = request.getParameter("date");
		
		//构建查询条件对象
		OrderCriteria criteria = new OrderCriteria();
		if (userId!=null&&!"".equals(userId.trim())) {
			criteria.setUserId(Integer.parseInt(userId));
		}
		if (menuName!=null&&!"".equals(menuName.trim())) {
			criteria.setMenuName(menuName);
		}
		if (date!=null&&!"".equals(date.trim())) {
			criteria.setDate(date);
		}
		// 1.curPage
		String curPage = request.getParameter("curPage");
		if (curPage == null || "".equals(curPage)) {
			curPage = "1";
		}
		// 2.pageNumber
		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null || "".equals(pageNumber)) {
			pageNumber = "1";
		}
		// Page->curPage,pageNumber
		Page page = new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));
		// 调用OrderService-》queryOrderByPage(Page)
		Page pageCurrent = orderService.queryOrderByPage(page,criteria);
		request.setAttribute("criteria", criteria);
		request.setAttribute("pageCurrent", pageCurrent);
		// 请求转发到admin/order.jsp
		request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
	}

	protected void queryOrderByDelivery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List list = orderService.queryOrderByDelivery();
		request.setAttribute("deliverylist", list);
		request.getRequestDispatcher("/admin/order_statistic.jsp").forward(request, response);
	}
	
}
