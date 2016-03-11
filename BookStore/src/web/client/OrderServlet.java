package web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessServiceImpl;
import domain.Cart;
import domain.User;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			User user = (User) request.getSession().getAttribute("user");
			if(user == null){
				request.setAttribute("message", "对不起，请先登录");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.createOrder(cart, user);
			request.setAttribute("message", "订单已生成");
			request.getSession().removeAttribute("cart");//清空购物车，这是我自己加上去的，因为点购买后，如果不清空购物车，前端点击查看购物车又出现了
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "订单生成失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
