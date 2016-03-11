<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>购物车显示列表</title>
  </head>
  
  <body style="text-align:center;">
  	<c:if test="${user == null }">
  		对不起，请先登录
  	</c:if>
  	<c:if test="${user != null }">
  	<h2>购物车列表</h2>
    <table width="40%" border="1" align="center" style="text-align: center;">
    	<tr>
    		<td>书名</td>
    		<td>作者</td>
    		<td>单价</td>
    		<td>数量</td>
    		<td>小计</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="me" items="${cart.map }">
    		<tr>
    			<td>${me.value.book.name }</td>
	    		<td>${me.value.book.author }</td>
	    		<td>${me.value.book.price }</td>
	    		<td>${me.value.quantity }</td>
	    		<td>${me.value.price }</td>
	    		<td>
	    			<a href="#">删除</a>
	    		</td>
    		</tr>
    	</c:forEach>
    	
    	<tr>
    		<td colspan="1">总价</td>
    		<td colspan="5">${cart.price }</td>
    	</tr>
	</table>
	<a href="${pageContext.request.contextPath }/client/OrderServlet">购买</a>
	</c:if>
   </body>
</html>