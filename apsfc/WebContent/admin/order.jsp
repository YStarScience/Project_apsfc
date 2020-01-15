<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
</head>

<body>
	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">


				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="12"><span
									class="left_bt2">销售订单查询结果信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">用户ID</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">真实姓名</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">联系方式</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">家庭住址</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">菜品名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">合计(元)</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">订购时间</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">是否派送</span></td>
								<td class="line_table" align="center" colspan="2"><span
									class="left_bt2">确认订单</span></td>
							</tr>
							<c:forEach items="${pageCurrent.data }" var="orderInfo">
								<tr>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.userId }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.realName }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.phone }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.address }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.menuName }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.menuSum }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.price1 }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.menuSum*orderInfo.price1 }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${orderInfo.times }</span></td>
									<td class="line_table" align="center"><span
										class="left_txt">${(orderInfo.delivery)==1?'是':'否' }</span></td>
									<c:if test="${orderInfo.delivery == 0 }">
										<td class="line_table" align="center"><a
											href="${pageContext.request.contextPath}/AdminOrderServlet?action=updateOrder&orderId=${orderInfo.orderId}&curPage=${pageCurrent.curPage }">确认</a></td>

										<c:choose>
											<c:when test="${(pageCurrent.curPage==pageCurrent.totalPage)&&(pageCurrent.rows%pageCurrent.pageNumber==1)}">	
												<td class="line_table" align="center"><a
													href="${pageContext.request.contextPath}/AdminOrderServlet?action=deleteOrder&orderId=${orderInfo.orderId}&curPage=${pageCurrent.curPage-1}">取消</a></td> 
												
											</c:when>
											<c:otherwise>
												<td class="line_table" align="center"><a
													href="${pageContext.request.contextPath}/AdminOrderServlet?action=deleteOrder&orderId=${orderInfo.orderId}&curPage=${pageCurrent.curPage}">取消</a></td> 
											</c:otherwise>
										</c:choose>



									</c:if>

								</tr>
							</c:forEach>

							<tr>
								<td class="line_table" align="center" colspan="12" height="20">
									<span class="left_bt2">第${pageCurrent.curPage }页
										&nbsp;&nbsp;共${pageCurrent.totalPage }页 </span>&nbsp;&nbsp; <a
									href="${pageContext.request.contextPath}/AdminOrderServlet?action=getAllOrderByPage&curPage=1">[首页]</a>
									<a
									href="${pageContext.request.contextPath}/AdminOrderServlet?action=getAllOrderByPage&curPage=${pageCurrent.totalPage}">[尾页]</a>&nbsp;&nbsp;
									<!-- 判断：如果当前页为第一页，curpage=1,不跳转；当前页不为第一页，curpage= ${pageCurrent.curPage-1}-->
									<c:if test="${pageCurrent.curPage!=1}">
										<a
											href="${pageContext.request.contextPath}/AdminOrderServlet?action=getAllOrderByPage&curPage=${pageCurrent.curPage-1}">[上一页]</a>
									</c:if> <c:if test="${pageCurrent.curPage==1}">
										<a href="javascript:void(0)">[上一页]</a>
									</c:if> <c:if test="${pageCurrent.curPage!=pageCurrent.totalPage}">
										<a
											href="${pageContext.request.contextPath}/AdminOrderServlet?action=getAllOrderByPage&curPage=${pageCurrent.curPage+1}">[下一页]</a>
									</c:if> <c:if test="${pageCurrent.curPage==pageCurrent.totalPage}">
										<a href="javascript:void(0)">[下一页]</a>
									</c:if>

								</td>
							</tr>
					</table>
				</div>




			</td>
		</tr>
	</table>
	${updateMsg } 
	${deleteMsg }
</body>

</html>