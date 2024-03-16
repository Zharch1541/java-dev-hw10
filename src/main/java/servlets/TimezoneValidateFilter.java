package servlets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String param = req.getParameter("timezone");

        if (param != null && !param.isEmpty()) {
            param = param.replace(" ", "+");
            if (isValidTimezone(param)) {
                chain.doFilter(req, res);
            } else {
                res.sendError(400, "Invalid timezone");
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    public boolean isValidTimezone(String param) {
        TimeZone zone = TimeZone.getTimeZone(param);
        System.out.println("zone = " + zone);
        return !zone.getID().equals("GMT");
    }
}
