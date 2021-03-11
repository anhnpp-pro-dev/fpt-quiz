package anhnpp.filter;

import anhnpp.dto.RegistrationDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KING (Nguyễn Phan Phước Anh)
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    private final List<String> GUEST;
    private final List<String> ADMIN;
    private final List<String> USER;

    public AuthFilter() {
        GUEST = new ArrayList<>();
        GUEST.add("home.jsp");
        GUEST.add("searchCar.jsp");
        GUEST.add("notFoundPage.jsp");
        GUEST.add("");
        GUEST.add("HomePageController");
        GUEST.add("LoginController");
        GUEST.add("RegisterController");
        GUEST.add("VerificationController");
        GUEST.add("SearchCarPageController");
        GUEST.add("SearchCarController");

        ADMIN = new ArrayList<>();
        ADMIN.add("notFoundPage.jsp");

        USER = new ArrayList<>();
        USER.add("home.jsp");
        USER.add("searchCar.jsp");
        USER.add("notFoundPage.jsp");
        USER.add("");
        USER.add("HomePageController");
        USER.add("LoginController");
        USER.add("SearchCarPageController");
        USER.add("SearchCarController");
        USER.add("LogoutController");

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        String resoure = uri.substring(uri.lastIndexOf("/") + 1);

        log("AuthFiler: " + resoure);

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("USER") == null) {
            if (GUEST.contains(resoure)) {
                if (resoure.equals("") || resoure.equals("home.jsp")) {
                    httpRequest.getRequestDispatcher("HomePageController").forward(request, response);
                } else {
                    chain.doFilter(request, response);
                }
            } else if (resoure.contains(".jpg")) {

            } else {
                httpResponse.sendRedirect("notFoundPage.jsp");
            }
        } else {
            RegistrationDTO user = (RegistrationDTO) session.getAttribute("USER");
            if (user.getRole() == 2 && USER.contains(resoure)) {
                if (resoure.equals("") || resoure.equals("home.jsp")
                        || resoure.equals("LoginController")) {
                    httpRequest.getRequestDispatcher("HomePageController").forward(request, response);
                } else if (resoure.equals("car.jsp")) {
                    httpRequest.getRequestDispatcher("SearchCarPageController").forward(request, response);
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                httpResponse.sendRedirect("notFoundPage.jsp");
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
