package anhnpp.controller;

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
public class SearchCarController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchCarPageController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        HttpSession session = request.getSession();
        try {

            String name = request.getParameter("name").trim();
            String cate = request.getParameter("cate");
            String search = request.getParameter("search");
            String rentalDate = request.getParameter("rentalDate");
            String returnDate = request.getParameter("returnDate");
            String amount = request.getParameter("amount").trim();
            String pageSearch = request.getParameter("pageSearch");

            if (Validation.checkSearchCar(name, search, rentalDate, returnDate, amount) != null) {
                request.setAttribute("ValidateSearchCar", Validation.checkSearchCar(name, search, rentalDate, returnDate, amount));
                url = SUCCESS;
            } else {
                session.setAttribute("name", name);
                session.setAttribute("cate", cate);
                session.setAttribute("search", search);
                session.setAttribute("rentalDate", rentalDate);
                session.setAttribute("returnDate", returnDate);
                session.setAttribute("amount", amount);
                session.setAttribute("pageSearch", pageSearch);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Exception at SearchQuestionController: " + e.getMessage());
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
