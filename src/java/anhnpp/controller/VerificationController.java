package anhnpp.controller;

import anhnpp.dao.RegistrationDAO;
import anhnpp.model.MailService;
import static anhnpp.model.RandomCode.random_Code;
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
public class VerificationController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String url = ERROR;
        String verifiAction = request.getParameter("verifiAction");
        String verifiCode = request.getParameter("verifiCode").trim();
        String currnentPage = request.getParameter("currentPage").trim();

        String action = "";
        try {
            RegistrationDAO regisDao = new RegistrationDAO();
            String email = (String) session.getAttribute("email");
            if (verifiAction.equals("submit")) {

                String codeVerification = regisDao.getVerificationCode(email);
                if (codeVerification.equals(verifiCode)) {
                    regisDao.setSttUser(email);
                    action = "openLogin";
                    session.setAttribute("action", action);
                    session.setAttribute("email", email);
                    url = currnentPage;
                } else {
                    request.setAttribute("errorVerificationCode", "Verification code incorrect");
                    action = "openVerifi";
                    session.setAttribute("action", action);
                    url = currnentPage;
                }

            } else if (verifiAction.equals("resend")) {
                String codeVerification = random_Code(8);
                MailService.sendVerificationCode(email, codeVerification);
                regisDao.setVerificationCode(email, codeVerification);
                action = "openVerifi";
                session.setAttribute("action", action);
                url = currnentPage;
            }
        } catch (Exception e) {
            log("Exception at VerificationController: " + e.getMessage());
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
