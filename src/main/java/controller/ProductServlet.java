package controller;

import DAO.category.CategoryDAO;
import DAO.category.ICategoryDAO;
import DAO.product.IProductDAO;
import DAO.product.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ICategoryDAO categoryDAO = new CategoryDAO();
    private IProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showFormAddProduct(request,response);
                break;
            case "edit":
                showFormEditProduct(request, response);
                break;
            case "delete":
                showFormDeleteProduct(request,response);
                break;
            default:
                showAllproduct(request, response);
                break;
        }

    }

    private void showFormDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
        request.setAttribute("product", existingProduct);
        request.setAttribute("categories", categoryDAO.findAll());
        dispatcher.forward(request, response);
    }

    private void showFormEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.findById(id);
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("product", existingProduct);
        request.setAttribute("categories",categoryDAO.findAll());
        rd.forward(request, response);
    }

    private void showFormAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        request.setAttribute("categories",categoryDAO.findAll());
        requestDispatcher.forward(request, response);
    }

    private void showAllproduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                addNewProduct(request,response);
                break;
            case "edit" :
                updateProduct(request,response);
                break;
            case "delete":
                try {
                    deleteProduct(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            break;
        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")) ;
        productDAO.delete(id);
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");

        Product product = productDAO.findById(id);
        Category category = product.getCategory();

        Product updateProduct = new Product(id, name, price, quantity, color, description, category);
        productDAO.update(updateProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");

        String idString = request.getParameter("category");
        int cat_id = Integer.parseInt(idString);
        Category category = categoryDAO.findById(cat_id);

        Product product = new Product(name, price, quantity, color, description, category);
        productDAO.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
