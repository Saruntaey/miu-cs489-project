package edu.miu.cs489.dentalms.exception;

import edu.miu.cs489.dentalms.exception.patient.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(PatientNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
