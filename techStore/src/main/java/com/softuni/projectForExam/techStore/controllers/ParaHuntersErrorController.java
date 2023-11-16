package com.softuni.projectForExam.techStore.controllers;

import org.apache.logging.log4j.message.Message;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ParaHuntersErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, String message){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String path = "";

        if (status != null){
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()){
                path = "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                path = "error/500";
            }
        }
        path = "error/error";
        return model;
    }
}
