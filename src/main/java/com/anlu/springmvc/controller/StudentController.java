package com.anlu.springmvc.controller;

import com.anlu.springmvc.model.Student;
import com.anlu.springmvc.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/FormHandling")
public class StudentController {


    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.replaceValidators(new StudentValidator());
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("form", "student", new Student());
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb")Student student, ModelMap model){
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "result";
    }
    @RequestMapping(value = "/addStudentValidator", method = RequestMethod.POST)
    public String addStudentValidator(@ModelAttribute("student")@Validated Student student, BindingResult result, ModelMap model){
      if(result.hasErrors()){
          return "form";
      }
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "result";
    }
}
