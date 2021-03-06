package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.CustomerService;

/**
 * this servlet processes the request of loginForm. And send an appropriate response base on request data. 
 * */
@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get request information.
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// Perfoorm business logic. Return a bean as a result.
		// CustomerService service = new CustomerService();
		CustomerService service = CustomerService.getInstance();
		Customer customer = service.login(id, password);

		String page;
		if (customer == null) {
			page = "/view/loginFail.jsp";
			request.setAttribute("id", id);
		}
		else {
			page = "/view/loginSuccess.jsp";
			request.setAttribute("customer", customer);
		}

		// send appropriate response to client.
		RequestDispatcher dispatcher = request.getRequestDispatcher(page); // forwarding
		dispatcher.forward(request, response);
	}
}
