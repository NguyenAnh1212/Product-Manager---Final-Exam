import DAO.category.CategoryDAO;
import DAO.category.ICategoryDAO;
import DAO.product.IProductDAO;
import DAO.product.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
//                showFormEditProduct(request, response);
                break;
            case "delete":
//                showFormDeleteProduct(request,response);
                break;
            default:
                showAllproduct(request, response);
                break;
        }

    }

    private void showFormAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productDAO.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
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
//                addNewProduct(request,response);
                break;
            case "edit" :
//                editProduct(request,response);
                break;
            case "delete":
//                deleteProduct(request,response);
            default:
        }

    }
}
