<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>我的订单</title>
<meta content="" name=keywords />
<meta content="" name=description />
<link href="css/skin.css" rel="stylesheet" type="text/css" /> 
<script src="js/date.js" type="text/javascript"></script>
</head>

<body style='background: transparent'>
	<table width="900" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="left" valign="top"><jsp:include flush="fasle"
					page="top.jsp" /></td>
		</tr>
		<tr>
			<td height="50px"></td>

		</tr>

		<tr>
			<td align="center" valign="top" height="420px">

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="left" valign="top">

							<div align="center" width="120">
								<form action="${pageContext.request.contextPath }/UserOrderServlet?action=queryOrderByPage&userid=${user.id}&del=2" name="form1" method="post">
									<table id="table1" class="line_table"
										style="width: 500px; margin: 0; padding: 0" cellSpacing="0"
										cellPadding="0">
										<tbody style="margin: 0; padding: 0">
											<tr>
												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按菜品名称查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="menuname" size="20" value= ${criteria.menuName }> <input
													type="submit" value="查询" ></td>
											<tr>
												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按销售日期查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="date" size="20" value=${criteria.date }> <input type="submit"
													value="查询" ></td>
											</tr>
											<tr>
												<td class="line_table" align="center" colspan="3"><a
													href="UserOrderServlet?action=getAllOrderByPage&userid=${user.id}&del=2">我的所有订单</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="UserOrderServlet?action=queryOrderByPage&userid=${user.id}&del=0">未已派送订单</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="UserOrderServlet?action=queryOrderByPage&userid=${user.id}&del=1">已派送订单</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
											</tr>
									</table>
								</form>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" valign="top" height="20px"></td>
					</tr>
					<tr>
						<td align="left" valign="top">


							<div align="center">
								<table id="table2" class="line_table"
									style="width: 900px; margin: 0; padding: 0" cellSpacing="0"
									cellPadding="0">
									<tbody style="margin: 0; padding: 0">
										<tr>
											<td class="line_table" align="center" colspan="9"><span
												class="left_bt2">订单查询结果信息列表</span></td>
										</tr>
										<tr>
											<td class="line_table" align="center"><span
												class="left_bt2">菜品名称</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">真实姓名</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">订购电话</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">派送地址</span></td>
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
										</tr>
										
										<c:forEach items="${pageCurrent.data}" var="orderInfo">
										<tr>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuName}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.realName }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.phone }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.address }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuSum}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.price1 }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuSum*orderInfo.price1 }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.times }</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${(orderInfo.delivery==1)?'是':'否' }</span></td>
										</tr>
										</c:forEach>
										<tr>
								<td class="line_table" align="center" colspan="12" height="20">
								<span class="left_bt2">第${pageCurrent.curPage }页
										&nbsp;&nbsp;共${pageCurrent.totalPage }页
								</span>&nbsp;&nbsp; 
								    <a href="javascript:queryOrderByPage(1)">[首页]</a>
								    <a href="javascript:queryOrderByPage(${pageCurrent.totalPage})">[尾页]</a>&nbsp;&nbsp; 
								   
								   <!-- 上一页判断：如果当前页就是第一页，curPage=1；如果当前页不是第一页，curPage=${pageCurrent.curPage-1} -->
								    
								    <c:if test="${pageCurrent.curPage!=1}">
								    <a href="javascript:queryOrderByPage(${pageCurrent.curPage-1})">[上一页]</a>
								    </c:if>
								    <c:if test="${pageCurrent.curPage==1}">
								    <a href="javascript:void(0)">[上一页]</a>
								    </c:if>
									
									<c:if test="${pageCurrent.curPage!=pageCurrent.totalPage}">
									<a href="javascript:queryOrderByPage(${pageCurrent.curPage+1})">[下一页]</a>
								    </c:if>
								    <c:if test="${pageCurrent.curPage==pageCurrent.totalPage}">
									<a href="javascript:void(0)">[下一页]</a>
								    </c:if>
									
								</td>
							</tr>
								</table>
								<script type="text/javascript">
						function queryOrderByPage(curPage){
							
						 	var del="${sessionScope.del}";
						 	//var del="${del}";
						 	
							if(del=="0")
							{document.form1.action = "${pageContext.request.contextPath}/UserOrderServlet?action=queryOrderByPage&userid="+${user.id}+"&del=0"+"&curPage="+curPage;}
							else if(del=="1"){ 
								document.form1.action = "${pageContext.request.contextPath}/UserOrderServlet?action=queryOrderByPage&userid="+${user.id}+"&del=1"+"&curPage="+curPage;
							 }else if(del=="2"){
								 document.form1.action = "${pageContext.request.contextPath}/UserOrderServlet?action=queryOrderByPage&userid="+${user.id}+"&del=2"+"&curPage="+curPage;
							 }
							document.form1.submit();
						}
					
					</script>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10px">&nbsp;</td>
		</tr>
		<tr>
			<td height="50px" align="center" valign="middle"><jsp:include
					flush="fasle" page="copyright.jsp" /></td>
		</tr>
	</table>
</body>
</html>
