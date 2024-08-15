package bg.softuni.GimyApi.web;

import bg.softuni.GimyApi.exceptions.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(InvalidUserException.class)
    public Map<String, String> handleInvalidUserException(InvalidUserException exception) {
        HashMap<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return map;
    }
}
