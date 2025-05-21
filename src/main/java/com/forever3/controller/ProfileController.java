package com.forever3.controller;
 
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.forever3.model.UserModel;
import com.forever3.service.ProfileService;
import com.forever3.util.PasswordUtil;
 
/**
* ProfileController is responsible for handling profile-related requests.
* It interacts with the ProfileService to get and update user profile details.
*/
@WebServlet(urlPatterns = { "/profile", "/profile/update" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024)
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProfileService profileService;
 
    /**
     * Constructor initializes the ProfileService.
     */
    public ProfileController() {
        this.profileService = new ProfileService();
    }
 
    /**
     * Handles GET requests to display the user's profile.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
 
        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to view your profile");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        UserModel user = profileService.getUserProfile(customerId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        } else {
            session.setAttribute("errorMessage", "Profile not found");
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
 
    /**
     * Handles POST requests to update the user's profile.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId");
 
        if (customerId == null) {
            session.setAttribute("errorMessage", "Please login to update your profile");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        // Get current user profile
        UserModel currentUser = profileService.getUserProfile(customerId);
        if (currentUser == null) {
            session.setAttribute("errorMessage", "Profile not found");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
 
        // Update user details
        currentUser.setFirstName(request.getParameter("first_name"));
        currentUser.setLastName(request.getParameter("last_name"));
        currentUser.setEmail(request.getParameter("email"));
        currentUser.setNumber(request.getParameter("number"));
 
        // Handle password update if provided
        String newPassword = request.getParameter("password");
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            currentUser.setPassword(PasswordUtil.encrypt(currentUser.getUserName(), newPassword));
        }
 
        // Handle image upload
        Part imagePart = request.getPart("profileImage");
        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/resources/images/profiles");
            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
 
            String filePath = uploadPath + java.io.File.separator + fileName;
            imagePart.write(filePath);
 
            currentUser.setImageUrl("/resources/images/profiles/" + fileName);
        }
 
        // Update profile
        boolean isUpdated = profileService.updateUserProfile(currentUser);
 
        if (isUpdated) {
            session.setAttribute("successMessage", "Profile updated successfully");
        } else {
            session.setAttribute("errorMessage", "Failed to update profile");
        }
 
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}