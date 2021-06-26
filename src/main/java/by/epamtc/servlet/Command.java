package by.epamtc.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    Logger logger = LogManager.getLogger();

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
