
import HTML.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/patients"}, loadOnStartup = 1)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HTML html = new HTML();

        html.addtoBody(new TagHeader());
        if (req.getParameter("search") == null || req.getParameter("search").length() == 0) {
            TagForm form = new TagForm("/Dbob/patients", "get");
            form.addChild(new TagInput("text", "search"));

            html.addtoBody(form);
            resp.getWriter().write(html.toString());
        } else {
            html.addtoBody(new TagDiv(req.getParameter("search")));
            resp.getWriter().write(html.toString());
        }
        System.out.println(req.getServletPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);
        resp.getWriter().write(body);
    }
}
