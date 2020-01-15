package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushida.po.Menu;
import com.qushida.po.Notice;
import com.qushida.service.MenuService;
import com.qushida.service.NoticeService;
import com.qushida.service.impl.MenuServiceImpl;
import com.qushida.service.impl.NoticeServiceImpl;
import com.qushida.util.Page;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MenuService menuService = new MenuServiceImpl();
	private NoticeService noticeService = new NoticeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码格式
		request.setCharacterEncoding("utf-8");
		// 设置响应类型
		response.setContentType("text/html;charset=utf-8");

		// 1.分页查询所有菜品

		// 调用MenuService中getMenuByPage(Page page)

		// 获取当前页

		String curPage = request.getParameter("pageIndex");

		if (curPage == null || "".equals(curPage)) {
			curPage = "1";
		}

		// 获取一页几条
		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null || "".equals(pageNumber)) {
			pageNumber = "6";
		}

		// 构建Page
		Page page = new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));

		page = menuService.getMenuByPage(page);
		List<Menu> menulist = menuService.selectBySums1();
		// 2.查询所有公告
		// 调用NoticeService中selectAllNotice()
		List<Notice> notices = noticeService.getAllNotice();

		// 3.将以上两个数据设置到请求域request中
		request.setAttribute("notices", notices);
		request.setAttribute("page", page);
		request.setAttribute("menutop", menulist);
		// 4.请求转发到qiantai/index.jsp
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
