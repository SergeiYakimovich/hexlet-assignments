package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        if (page == null || page.isEmpty()) {
            page = "1";
        }
        int count = (Integer.parseInt(page) - 1) * 10;
        if (count < 0) {
            count = 0;
            page = "1";
        }
        List<Map<String, String>> articles = new ArrayList<>();
        String query = "SELECT id, title FROM articles ORDER BY id LIMIT ? OFFSET ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 10);
            statement.setInt(2, count);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title")
                        )
                );
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        request.setAttribute("articles", articles);
        String pageNext = "articles?page=" + Integer.toString(Integer.parseInt(page) + 1);
        String pagePrev = "articles?page=" + Integer.toString(Integer.parseInt(page) - 1);
        request.setAttribute("pagePrev", pagePrev);
        request.setAttribute("pageNext", pageNext);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);
        List<Map<String, String>> articles = new ArrayList<>();
        String query = "SELECT title, body FROM articles WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                                "body", rs.getString("body"),
                                "title", rs.getString("title")
                        )
                );
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (articles.size() == 0) {
            response.sendError(404);

        }
        request.setAttribute("articles", articles);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
