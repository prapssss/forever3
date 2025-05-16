package com.forever3.controller;
 
import java.io.IOException;
 
 
import com.forever3.model.UserModel;
import com.forever3.service.RegisterService;
import com.forever3.util.ImageUtil;
import com.forever3.util.PasswordUtil;
import com.forever3.util.ValidationUtil;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
 
 
/**
 * RegisterController handles user registration requests and processes form
 * submissions. It also manages file uploads and account creation.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private final ImageUtil imageUtil = new ImageUtil();
    private final RegisterService registerService = new RegisterService();
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Validate and extract student model
            String validationMessage = validateRegistrationForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }
 
            UserModel userModel = extractUserModel(req);
            Boolean isAdded = registerService.addUser(userModel);
 
            if (isAdded == null) {
                handleError(req, resp, "Our server is under maintenance. Please try again later!");
            } else if (isAdded) {
                try {
                    if (uploadImage(req)) {
                        handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
                    } else {
                        handleError(req, resp, "Could not upload the image. Please try again later!");
                    }
                } catch (IOException | ServletException e) {
                    handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
                    e.printStackTrace(); // Log the exception
                }
            } else {
                handleError(req, resp, "Could not register your account. Please try again later!");
            }
        } catch (Exception e) {
            handleError(req, resp, "An unexpected error occurred. Please try again later!");
            e.printStackTrace(); // Log the exception
        }
    }
 
    private String validateRegistrationForm(HttpServletRequest req) {
        String first_name = req.getParameter("firstname");
        String last_name = req.getParameter("lastname");
        String user_name = req.getParameter("username");;
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String password = req.getParameter("password");
        String retypePassword = req.getParameter("retype-password");
 
        // Check for null or empty fields first
        if (ValidationUtil.isNullOrEmpty(first_name))
            return "First name is required.";
        if (ValidationUtil.isNullOrEmpty(last_name))
            return "Last name is required.";
        if (ValidationUtil.isNullOrEmpty(user_name))
            return "Username is required.";
        if (ValidationUtil.isNullOrEmpty(email))
            return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(number))
            return "Phone number is required.";
        if (ValidationUtil.isNullOrEmpty(password))
            return "Password is required.";
        if (ValidationUtil.isNullOrEmpty(retypePassword))
            return "Please retype the password.";
 
 
        // Validate fields
        if (!ValidationUtil.isAlphanumericStartingWithLetter(user_name))
            return "Username must start with a letter and contain only letters and numbers.";
        if (!ValidationUtil.isValidEmail(email))
            return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(number))
            return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isValidPassword(password))
            return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
        if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
            return "Passwords do not match.";
 
 
 
        try {
            Part image = req.getPart("image");
            if (!ValidationUtil.isValidImageExtension(image))
                return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
        } catch (IOException | ServletException e) {
            return "Error handling image file. Please ensure the file is valid.";
        }
 
        return null; // All validations passed
    }
 
    private UserModel extractUserModel(HttpServletRequest req) throws Exception {
        String first_name = req.getParameter("firstname");
        String last_name = req.getParameter("lastname");
        String user_name = req.getParameter("username");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String password = req.getParameter("password");
 
        // Assuming password validation is already done in validateRegistrationForm
        password = PasswordUtil.encrypt(user_name, password);
 
        Part image = req.getPart("image");
        String image_url = imageUtil.getImageNameFromPart(image);
 
        return new UserModel(first_name, last_name, user_name, email, number, password,
                image_url);
    }
 
    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("image");
        return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "customer");
    }
 
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws ServletException, IOException {
        req.setAttribute("success", message);
        req.getRequestDispatcher(redirectPage).forward(req, resp);
    }
 
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("firstname", req.getParameter("firstname"));
        req.setAttribute("lastname", req.getParameter("lastname"));
        req.setAttribute("username", req.getParameter("username"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("number", req.getParameter("number"));
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
 