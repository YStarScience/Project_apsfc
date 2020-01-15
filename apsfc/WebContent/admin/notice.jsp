<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<!-- 确认删除 -->
<script type="text/javascript">
	
	function deleteNotice(id){
		if(confirm("确认要删吗？")){
			window.location.href="NoticeServlet?action=deleteNotice&id="+id;
		};
	}
	
</script>
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
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">公告信息列表</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">标题</span></td>
								<td class="line_table" align="center" width="40%"><span
									class="left_bt2">内容</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">发布时间</span></td>
								<td class="line_table" align="center" width="10%"></td>
							</tr>
							
							<c:if test="${noticelist!=null}">
							<c:forEach items="${noticelist}" var="notice">
							<tr>
								<td class="line_table" align="center" width="20%"><span
									class="left_txt">${notice.name}</span></td>
								<td class="line_table" align="center" width="40%"><span
									class="left_txt">${notice.content}</span></td>
								<td class="line_table" align="center" width="20%"><span
									class="left_txt">${notice.times}</span></td>
								<td class="line_table" align="center" width="20%"><a
									href="#" onclick="deleteNotice(${notice.id})"target="main">删除</a></td>
							</tr>
							</c:forEach>	
							</c:if>
							
					</table>
				</div>

			</td>

		</tr>
	</table>
</body>
<!-- EL表达式取出Servlet中存放的内容 -->
${addmsg}
${deletemsg}

</html>
