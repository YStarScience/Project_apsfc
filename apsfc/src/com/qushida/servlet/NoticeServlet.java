package com.qushida.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushida.po.Notice;
import com.qushida.service.NoticeService;
import com.qushida.service.impl.NoticeServiceImpl;




@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private NoticeService noticeService = new NoticeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//从请求中获取动作并进行判断
		String action = request.getParameter("action");
		switch (action) {
		case "getAllNotice":
			getAllNotice(request, response);
			break;
		case "addNotice":
			addNotice(request, response);
			break;
		case "deleteNotice":
			deleteNotice(request, response);
			break;
		case "selectNoticeById":
			selectNoticeById(request, response);
			break;
		default:
			break;
		}

	}
	//通过id获取公告
	public void selectNoticeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("noticeId");
		int id1 = Integer.parseInt(id);
		Notice notice = noticeService.selectById(id1);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/qiantai/notice.jsp").forward(request, response);
		
	}

	//删除公告
	public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求中的参数
		String id = request.getParameter("id");
		//将id转换成int类型
		int id1 = Integer.parseInt(id);
		//调用service层方法
		int i = noticeService.deleteNotice(id1);
		if (i>0) {
			//删除成功
			String  deletemsg = "<script>alert('删除成功')</script>";
			request.setAttribute("deletemsg", deletemsg);
			getAllNotice(request, response);
		} else {
			//失败
			String  deletemsg = "<script>alert('删除失败')</script>";
			request.setAttribute("deletemsg", deletemsg);
			getAllNotice(request, response);
		}
		
	}

	//添加新公告
	public void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求中的参数
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times = sdf.format(new Date());
		/*Notice notice = new Notice();
		notice.setName(name);
		notice.setContent(content);
		//Notice notice = new Notice(null,name,content,null)
*/		
		//调用service层方法
		int i = noticeService.addNotice(name, content, times);
		//int i = noticeService.addNotice(name, content);
		//int i = noticeService.addNotice(notice);
		
		if(i>0){
			//添加成功
			String  addmsg = "<script>alert('添加成功')</script>";
			request.setAttribute("addmsg", addmsg);
			getAllNotice(request, response);
		}else{
			//失败
			response.getWriter().write("<script>alert('添加失败，请重新添加');window.location.href='admin/notice_add.jsp'</script>");
			
		}
		
	}


	public void getAllNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用Service层方法
		List<Notice> noicelist = noticeService.getAllNotice();
		//把查询到的公告存到request域中
		request.setAttribute("noticelist", noicelist);
		//转发到用来显示的页面notice.jsp
		//获得调度器后使用forward进行转发
		request.getRequestDispatcher("admin/notice.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
