package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;
import com.forever3.util.ImageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
/**
 * @author Prapti Adhikari
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/addproduct"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 5 * 1024 * 1024,    // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // Service layer for handling product operations
    private ProductService productService = new ProductService();
    // Utility class for handling image uploads
    private ImageUtil imageUtil = new ImageUtil();
    /**
     * Handles GET requests: Shows the "Add Product" form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 // Forward request to JSP page for rendering add product form
        request.getRequestDispatcher("/WEB-INF/pages/admin/addproduct.jsp").forward(request, response);
    }
    /**
     * Handles POST requests: Processes the submitted product data and saves it.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	// Ensure proper encoding for form inputs
            request.setCharacterEncoding("UTF-8");
            // Retrieve and parse product details from form parameters
            String itemName = request.getParameter("itemName");
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));

         // Handle image file upload
            Part imagePart = request.getPart("imageFile");
            String rootPath = getServletContext().getRealPath("/");
            String imageName = imageUtil.uploadImage(imagePart, rootPath, "system");
         // Check if image upload was successful
            if (imageName == null) {
                throw new IOException("Failed to upload image");
            }
            // Create and populate a new product model instance
            ProductModel product = new ProductModel();
            product.setItemName(itemName);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setItemPrice(itemPrice);
            product.setStockQuantity(stockQuantity);
            product.setSupplierId(supplierId);
            product.setImageUrl(imageName); // Save just the filename
         // Save product to the database
            productService.addProduct(product);
            // Redirect to product management page upon success
            response.sendRedirect("productmanagement");
        
        } catch (SQLException | NumberFormatException e) {
        	 // Handle SQL or parsing errors and show an error message
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to add product. Please check inputs.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/addproduct.jsp").forward(request, response);
        }
    }
}
