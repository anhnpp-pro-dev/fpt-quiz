package anhnpp.controller;

import anhnpp.dao.CarDAO;
import anhnpp.dao.CategoryCarDAO;
import anhnpp.dto.CarDTO;
import anhnpp.dto.CategoryCarDTO;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class SearchCarPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        try {
            CategoryCarDAO cateDao = new CategoryCarDAO();
            List<CategoryCarDTO> listCate = cateDao.getAllCategoryCar();
            request.setAttribute("ListCate", listCate);

            CarDAO carDao = new CarDAO();
            LinkedHashMap<CategoryCarDTO, List<CarDTO>> listCarByCate = new LinkedHashMap<>();

            String name = (String) session.getAttribute("name");
            String cate = (String) session.getAttribute("cate");
            String search = (String) session.getAttribute("search");
            String rentalDate = (String) session.getAttribute("rentalDate");
            String returnDate = (String) session.getAttribute("returnDate");
            String amount = (String) session.getAttribute("amount");
            String pageSearch = (String) session.getAttribute("pageSearch");
            int numPageSearch = 0;

            if (search.equals("name")) {
                // 1 Trang 1 cate
                int page = Integer.parseInt(pageSearch);
                for (CategoryCarDTO categoryCarDTO : listCate) {
                    List<CarDTO> listCar = carDao.getCars(categoryCarDTO.getCateId() + "", name, rentalDate, returnDate, amount);
                    if (listCar.size() > 0) {
                        numPageSearch++;
                        page--;
                        if (page == 1) {
                            listCarByCate.put(categoryCarDTO, listCar);
                        }
                    }
                }
                // 1 Trang 20 car
            } else {
                int numSubjectInPage = 20;
                List<CarDTO> listCar = carDao.getCars(cate, name, rentalDate, returnDate, amount);
                if (listCar.size() > 0) {
                    listCarByCate.put(cateDao.getCategoryCarByCateId(cate), listCar);
                    int surplus = listCar.size() % numSubjectInPage;
                    if (surplus != 0) {
                        numPageSearch = (listCar.size() / numSubjectInPage) + 1;
                    } else {
                        numPageSearch = listCar.size() / numSubjectInPage;
                    }
                }
            }
            request.setAttribute("ListCarByCate", listCarByCate);
            request.setAttribute("NumPageSearch", numPageSearch);
            request.setAttribute("PageSearch", pageSearch);

        } catch (Exception e) {
            log("Exception at SearchCarPageController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("searchCar.jsp").forward(request, response);
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
