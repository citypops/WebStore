package citypops.webstore.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyResultException() {

        return new ResponseEntity<>(404, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException() {

        return new ResponseEntity<>(403, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException e) {

        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> handleServletException() {

        return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FieldNotValidException.class)
    public ResponseEntity<?> handleFieldNotValidException(FieldNotValidException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> globalHandler() {
        return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}