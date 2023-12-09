package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.services.exception.ObjectNotFoundException;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ParaHuntersErrorController implements ErrorController {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleError(RuntimeException e){

        ModelAndView model = new ModelAndView("error/error");
        model.addObject("message", e.getMessage());

        return model;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.class)
    public ModelAndView handle500(HttpServerErrorException.InternalServerError e){
        return new ModelAndView("error/500");
    }
}
