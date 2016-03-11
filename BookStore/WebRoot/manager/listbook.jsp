<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>显示所有书籍</title>
  </head>
  
  <body style="text-align:center;">
    <h2>图书信息</h2>
    <table border="1" width="60%" align="center">
    	<tr style="text-align:center">
  			<td>图书名称</td>
  			<td>作者</td>
  			<td>价格</td>
  			<td>图片</td>
  			<td>描述</td>
  			<td>操作</td>
  		</tr>
  		<c:forEach var="book" items="${page.list }">
  			<tr style="text-align:center">
  				<td>${book.name }</td>
	  			<td>${book.author }</td>
	  			<td>${book.price }</td>
	  			<td>
					<a href="${pageContext.request.contextPath }/images/${book.image}">查看图片</a>
				</td>
	  			<td>${book.description }</td>
	  			<td>
	  				<a href="#">修改</a>
	  				<a href="#">删除</a>
	  			</td>
  			</tr>
  		</c:forEach>
    </table>
    <br>
    当前第[${page.pagenum }]页 &nbsp;&nbsp;
    <c:forEach var="pagenum" begin="${page.startpage }" end="${page.endpage }">
    	[<a href="${pageContext.request.contextPath }/manager/BookServlet?method=list&pagenum=${pagenum}">${pagenum }</a>]
    </c:forEach>
    &nbsp;&nbsp;
    总共[${page.totalpage }]页，共[${page.totalrecord }]条记录
  </body>
</html>
