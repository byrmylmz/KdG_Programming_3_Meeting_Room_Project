package be.kdg.event.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Order(1)
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RoomNotFoundException.class)
    public String handleRoomNotFoundException(RoomNotFoundException e, Model model, HttpServletRequest request) {
        logger.error("RoomNotFoundException occurred at URL: {}, Error: {}", request.getRequestURL().toString(), e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("url", request.getRequestURL().toString());
        return "errors/generic";
    }

    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(DataAccessException e, Model model) {
        logger.error("Database exception occurred: {}", e.getMessage(), e);
        logger.debug("Detailed log: Database exception stack trace", e);
        model.addAttribute("errorMessage", "A database error occurred. Please try again later.");
        return "errors/database";
    }


}