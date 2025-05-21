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

@WebServlet("/editproduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024)
public class EditProductController extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductModel product = productService.getProductById(productId);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/admin/editproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String description = request.getParameter("description");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));

            // Image handling
            Part imagePart = request.getPart("imageFile"); 
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String imageUrl = request.getParameter("existingImage");

            if (fileName != null && !fileName.isEmpty()) {
                // Update the path to store images in /resources/images/system
                String uploadPath = getServletContext().getRealPath("/resources/images/system");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                String filePath = uploadPath + File.separator + fileName;
                imagePart.write(filePath);

                // Update the path saved in DB or model
                imageUrl = "resources/images/system/" + fileName;
            }


            ProductModel product = new ProductModel();
            product.setItemId(itemId);
            product.setItemName(itemName);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setItemPrice(itemPrice);
            product.setStockQuantity(stockQuantity);
            product.setSupplierId(supplierId);
            product.setImageUrl(imageUrl);

            productService.updateProduct(product);
            response.sendRedirect("productmanagement");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update product.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/editproduct.jsp").forward(request, response);
        }
    }
}
