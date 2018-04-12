package citypops.webstore.controller;

import citypops.webstore.domain.User;
import citypops.webstore.exception.FieldNotValidException;
import citypops.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUserById(username);
    }

    @PostMapping
    public void addUser(@RequestBody @Valid User user, BindingResult result) {

        if(result.hasErrors()) {
            FieldError error = result.getFieldError();
            throw new FieldNotValidException(error.getDefaultMessage());
        }
        userService.addUser(user);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(@RequestBody @Valid User user, BindingResult result) {

        if(result.hasErrors()) {
            FieldError error = result.getFieldError();
            throw new FieldNotValidException(error.getDefaultMessage());
        }
        userService.updateUser(user);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException() {
        return new ResponseEntity<>("Account with this username or email already exist. These two should be unique.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handle(SQLIntegrityConstraintViolationException e) {
        String message;
        if(e.getErrorCode() == 1451) {
            message = "Cannot delete account with orders. These must be deleted first.";
        } else {
            message = e.getMessage();
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}