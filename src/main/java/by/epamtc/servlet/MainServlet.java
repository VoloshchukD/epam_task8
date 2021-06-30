package by.epamtc.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {

    private static final CommandProvider provider = CommandProvider.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = provider.getCommand(commandName);
        command.execute(req, resp);
    }

}