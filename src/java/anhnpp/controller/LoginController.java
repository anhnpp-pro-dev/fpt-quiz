package anhnpp.controller;

import anhnpp.dao.RegistrationDAO;
import anhnpp.dto.RegistrationDTO;
import anhnpp.dto.ValidateRegistrationDTO;
import anhnpp.model.Capcha;
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
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String url = ERROR;
        String email = request.getParameter("emailL").trim();
        String password = request.getParameter("passwordL").trim();
        String currnentPage = request.getParameter("currentPage").trim();
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        String action = "";
        try {
            if (Validation.checkLogin(email, password) != null) {
                request.setAttribute("ValidateRegistration", Validation.checkLogin(email, password));
                session.removeAttribute("email");
                action = "openLogin";
                session.setAttribute("action", action);
                url = currnentPage;
            } else {
                RegistrationDAO regisDao = new RegistrationDAO();
                RegistrationDTO user = regisDao.getRegistration(email, password);

                if (user == null) {
                    ValidateRegistrationDTO validate = new ValidateRegistrationDTO(null, "Invalid user name or password");
                    request.setAttribute("ValidateRegistration", validate);
                    session.setAttribute("email", email);
                    action = "openLogin";
                    session.setAttribute("action", action);
                    url = currnentPage;

                } else {
                    boolean capcha = Capcha.verify(gRecaptchaResponse);

                    if (capcha) {
                        if (user.getStatus().equals("new")) {
                            action = "openVerifi";
                            session.setAttribute("action", action);
                            session.setAttribute("email", user.getEmail());
                            url = currnentPage;
                        } else if (user.getStatus().equals("act")) {
                            if (user.getRole() == 2) {
                                action = "openWelcome";
                                request.setAttribute("action", action);
                                session.setAttribute("USER", user);
                                session.removeAttribute("email");
                                url = currnentPage;
                            }
                        }
                    } else {
                        ValidateRegistrationDTO validate = new ValidateRegistrationDTO(null, "Check capcha");
                        request.setAttribute("ValidateRegistration", validate);
                        action = "openLogin";
                        session.setAttribute("action", action);
                        session.setAttribute("email", user.getEmail());
                        url = currnentPage;
                    }
                }
            }
        } catch (Exception e) {
            log("Exception at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
