package anhnpp.controller;

import anhnpp.dao.RegistrationDAO;
import anhnpp.dto.ValidateRegistrationDTO;
import anhnpp.model.MailService;
import static anhnpp.model.RandomCode.random_Code;
import anhnpp.model.Validation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class RegisterController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String url = ERROR;
        String email = request.getParameter("email").trim();
        String fullname = request.getParameter("fullname").trim();
        String phone = request.getParameter("phone").trim();
        String address = request.getParameter("address").trim();
        String password = request.getParameter("password").trim();
        String confirm = request.getParameter("confirm").trim();
        String currnentPage = request.getParameter("currentPage").trim();

        String action = "";
        try {
            RegistrationDAO regisDao = new RegistrationDAO();
            if (Validation.checkRegister(email, fullname, phone, address, password, confirm) != null) {
                request.setAttribute("ValidateRegistration", Validation.checkRegister(email, fullname, phone, address, password, confirm));
                action = "openRegister";
                session.setAttribute("action", action);
                url = currnentPage;
            } else {
                String codeVerification = random_Code(8);
                if (regisDao.register(email, fullname, phone, address, password, codeVerification)) {
                    MailService.sendVerificationCode(email, codeVerification);
                    session.setAttribute("email", email);
                    action = "openLogin";
                    session.setAttribute("action", action);
                    url = currnentPage;
                } else {
                    action = "openFailRegister";
                    session.setAttribute("action", action);
                    url = currnentPage;
                }
            }
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                ValidateRegistrationDTO validate = new ValidateRegistrationDTO("Email already exists", null);
                request.setAttribute("ValidateRegistration", validate);
                action = "openRegister";
                session.setAttribute("action", action);
                url = currnentPage;
            } else {
                log("Exception at RegisterController: " + e.getMessage());
            }
        } finally {
            if (action.equals("openLogin")) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
