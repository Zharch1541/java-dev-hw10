package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html, charset=UTF-8");

        String param = req.getParameter("timezone");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");

        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        dateFormat.setTimeZone(timeZone);
        Instant currentTime = Instant.now();

        if (param != null && !param.isEmpty()) {
            param = param.replace(" ", "+");
            timeZone = TimeZone.getTimeZone(param);
            dateFormat.setTimeZone(timeZone);
        }

        String formattedTime = dateFormat.format(Date.from(currentTime));

        PrintWriter out = resp.getWriter();
        out.print("<html><body>");
        out.print("<h3>Current time!</h3>");
        out.print("<p>" + formattedTime + "<p>");
        out.print("</body></html>");
        out.close();
    }
}
