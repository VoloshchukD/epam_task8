package by.epamtc.servlet;

import by.epamtc.servlet.command.CommandProvider;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {

    private static final CommandProvider provider = CommandProvider.getInstance();

    public void init() {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        System.out.println(commandName);
        Command command = provider.getCommand(commandName);
        command.execute(req, resp);
    }

    public void destroy() {
    }

}