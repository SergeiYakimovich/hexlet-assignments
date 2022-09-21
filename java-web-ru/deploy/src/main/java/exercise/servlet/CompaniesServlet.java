package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        String str = request.getParameter("search");
        PrintWriter out = response.getWriter();
        if (str != null && str.length() != 0) {
            companies = companies.stream()
                    .filter(item -> item.contains(str))
                    .collect(Collectors.toList());
        }
        if (companies.size() == 0) {
            out.println("Companies not found");
            return;
        }
        companies.stream()
                .forEach(item -> out.println(item));
        // END
    }
}
