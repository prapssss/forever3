package com.forever3.controller.admin;

import com.forever3.model.ProductModel;
import com.forever3.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet(asyncSupported = true, urlPatterns = {"/addproduct"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
                 maxFileSize = 5 * 1024 * 1024,    // 5MB
                 maxRequestSize = 10 * 1024 * 1024) // 10MB
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/admin/addproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String itemName = request.getParameter("itemName");
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));

         // Handle uploaded image
            Part imagePart = request.getPart("imageFile");
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            // Upload path inside webapp folder
            String uploadPath = getServletContext().getRealPath("/resources/images/system");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Save the file
            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);

            // Store this relative path in DB
            String imageUrl = "resources/images/system/" + fileName;


            // Create ProductModel
            ProductModel product = new ProductModel();
            product.setItemName(itemName);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setItemPrice(itemPrice);
            product.setStockQuantity(stockQuantity);
            product.setSupplierId(supplierId);
            product.setImageUrl(imageUrl);

            // Save to database
            productService.addProduct(product);

            // Redirect
            response.sendRedirect("productmanagement");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to add product. Please check inputs.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/addproduct.jsp").forward(request, response);
        }
    }
}
