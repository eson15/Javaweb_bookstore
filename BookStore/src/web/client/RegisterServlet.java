package web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.BusinessServiceImpl;
import utils.WebUtils;
import domain.User;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String cellphone = request.getParameter("cellphone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			User user = new User();
			user.setAddress(address);
			user.setCellphone(cellphone);
			user.setEmail(email);
			user.setId(WebUtils.makeID());
			user.setPassword(password);
			user.setPhone(phone);
			user.setUsername(username);
			
			BusinessServiceImpl service = new BusinessServiceImpl();
			service.registerUser(user);
			request.setAttribute("message", "注册成功");
			request.getRequestDispatcher("/message.jsp").forward(request, response);//这里要跳转到首页，并且显示欢迎您，，，待修改
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "注册失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
