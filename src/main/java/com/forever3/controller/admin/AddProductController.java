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

@WebServlet(asyncSupported = true, urlPatterns = {"/addproduct"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 5 * 1024 * 1024,    // 5MB
    maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class AddProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();
    private ImageUtil imageUtil = new ImageUtil();

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

            // Upload image
            Part imagePart = request.getPart("imageFile");
            String rootPath = getServletContext().getRealPath("/");
            String imageName = imageUtil.uploadImage(imagePart, rootPath, "system");

            if (imageName == null) {
                throw new IOException("Failed to upload image");
            }

            ProductModel product = new ProductModel();
            product.setItemName(itemName);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setItemPrice(itemPrice);
            product.setStockQuantity(stockQuantity);
            product.setSupplierId(supplierId);
            product.setImageUrl(imageName); // ✅ Save just the filename

            productService.addProduct(product);
            response.sendRedirect("productmanagement");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to add product. Please check inputs.");
            request.getRequestDispatcher("/WEB-INF/pages/admin/addproduct.jsp").forward(request, response);
        }
    }
}
