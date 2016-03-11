<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>客户端显示订单</title>
  </head>
  
  <body style="text-align:center;">
    <h2>订单列表</h2>
    <table width="60%" border="1" align="center" style="text-align: center;">
    	<tr>
    		<td>订单号</td>
    		<td>订单人</td>
    		<td>订单时间</td>
    		<td>订单总价</td>
    		<td>订单状态</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="order" items="${orders }">
    		<tr>
    			<td>${order.id }</td>
	    		<td>${order.user.username }</td>
	    		<td>${order.ordertime }</td>
	    		<td>${order.price }</td>
	    		<td>${order.state==true?'已发货':'未发货' }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/client/ClientOrderDetailServlet?orderid=${order.id}">查看明细</a>
	    			<a href="#">删除</a>
	    		</td>
    		</tr>
    	</c:forEach>  	
	</table>
  </body>
</html>
