<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"> 
<title>-我的订餐车</title>
<meta content="" name=keywords />
<meta content="" name=description />
<link href="qiantai/css/skin.css" rel="stylesheet" type="text/css" />
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


							<div align="center">
								<table id="table2" class="line_table"
									style="width: 700px; margin: 0; padding: 0" cellSpacing="0"
									cellPadding="0">
									<tbody style="margin: 0; padding: 0">
										<tr>
											<td class="line_table" align="center" colspan="4"><span
												class="left_bt2">我的订餐信息列表</span></td>
										</tr>
										<tr>
											<td class="line_table" align="center" width="40%"><span
												class="left_bt2">菜品名称</span></td>
											<td class="line_table" align="center" width="20%"><span
												class="left_bt2">单价</span></td>
											<td class="line_table" align="center" width="20%"><span
												class="left_bt2">数量</span></td>
											<td class="line_table" align="center" width="20%"><span
												class="left_bt2">&nbsp;&nbsp;</span></td>
										</tr>

										<c:forEach items = "${carList }" var = "carItem">
										<tr>
											<td class="line_table" align="center" width="40%"><span
												class="left_txt">${carItem.name }</span></td>
											<td class="line_table" align="center" width="20%"><span
												class="left_txt">${carItem.price }</span></td>
											<td class="line_table" align="center" width="20%"><span
												class="left_txt">${carItem.sums }</span></td>
											<td class="line_table" align="center" width="20%"><a
												href="ShoppingCarServlet?action=removeOne&menuId=${carItem.menuId }&from=shoppingcar">取消</a></td>
										</tr>
										<!-- 设置属性var 属性值value -->
										<c:set var = "totalSums" value = "${carItem.sums+totalSums}"></c:set>
										<c:set var = "totalMoney" value = "${totalMoney+carItem.price*carItem.sums }"></c:set>
										</c:forEach>


										<tr>
											<td class="line_table" align="center" colspan="4"><span
												class="left_bt2">小&nbsp;&nbsp;计：</span>&nbsp; <span
												style="color: #ff0000;">${totalMoney}</span>&nbsp;&nbsp; <span
												class="left_bt2">元</span>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<span class="left_bt2">共：</span>&nbsp; <span
												style="color: #ff0000;">${totalSums}</span>&nbsp; <span
												class="left_bt2">份</span></td>

										</tr>
									<c:if test="${!empty carList }">
										<tr>
											<td class="line_table" align="center" colspan="4"
												valign="center"><a href="UserOrderServlet?action=addOrder"><img
													src="qiantai/images/canche_submit.gif" border="0" /></a>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

												<a href="ShoppingCarServlet?action=removeAll"><img
													src="qiantai/images/quxiao2.gif" border="0" /></a></td>

										</tr>
									</c:if>
								</table>
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
