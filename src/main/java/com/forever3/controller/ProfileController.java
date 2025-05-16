package com.forever3.controller;
 
import java.io.IOException;
 
import com.forever3.model.UserModel;
import com.forever3.service.ProfileService;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
 
/**
* ProfileController is responsible for handling profile-related requests.
* It interacts with the ProfileService to get and update user profile details.
*/
@WebServlet(urlPatterns = { "/profile", "/profile/update" })
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
        // Get userId from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
 
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        // Retrieve user profile
        UserModel user = profileService.getUserProfile(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Profile not found.");
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
 
    /**
     * Handles POST requests to update the user's profile.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get userId from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
 
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
 
        // Get updated profile details from request
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String userName = request.getParameter("user_name");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        String imageUrl = request.getParameter("image_url");
 
        // Simple validation (you can extend this as needed)
        if (firstName == null || lastName == null || userName == null || email == null || number == null) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            return;
        }
 
        // ✅ Correct: Fetch the current user from DB first
        UserModel user = profileService.getUserProfile(userId);
        if (user == null) {
            request.setAttribute("error", "Profile not found.");
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            return;
        }
 
        // ✅ Update fields
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setEmail(email);
        user.setNumber(number);
        user.setPassword(password);
        user.setImageUrl(imageUrl);
 
        // ✅ Now update profile
        boolean isUpdated = profileService.updateUserProfile(user);
 
        if (isUpdated) {
            request.setAttribute("message", "Profile updated successfully.");
            doGet(request, response);  // Re-fetch profile to reflect updates
        } else {
            request.setAttribute("error", "Failed to update profile.");
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        }
    }
}