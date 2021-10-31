package servlet;

import com.google.gson.Gson;
import entity.JsonFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@WebServlet(
        name = "JsonServlet",
        urlPatterns = {"/json"}
)
public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String data = req.getReader().lines().collect(Collectors.joining());
        JsonFile file = gson.fromJson(data, JsonFile.class);
        String path = "json/" + file.getName() + ".json";
        String content = Files.readString(Paths.get(path));
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        out.print(gson.toJson(content));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        JsonFile file = gson.fromJson(data, JsonFile.class);
        Writer writer = Files.newBufferedWriter(Paths.get("json/" + file.getName() + ".json"));
        gson.toJson(file.getContent(), writer);
        writer.close();
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        JsonFile file = gson.fromJson(data, JsonFile.class);
        Writer writer = Files.newBufferedWriter(Paths.get("json/" + file.getName() + ".json"));
        gson.toJson(file.getContent(), writer);
        writer.close();
        resp.setStatus(204);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        JsonFile file = gson.fromJson(data, JsonFile.class);
        Files.deleteIfExists(Paths.get("json/" + file.getName() + ".json"));
    }
}
