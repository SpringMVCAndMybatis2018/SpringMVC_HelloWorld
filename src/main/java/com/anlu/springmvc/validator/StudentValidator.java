package com.anlu.springmvc.validator;

import com.anlu.springmvc.model.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {

        Student student = (Student) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "name", "required.name","名字不能为空");

        //此方法可以加四个参数,第一个表单域field,
        //区分是哪个表单出错,第二个errorCode错误码,
        //第三个制定了资源文件中占位符,第四个具体错误返回信息
        //简写版可以把2,3参数去掉


        if(student!=null){
            if(student.getAge()==null){
                errors.rejectValue("age",null,null,"age is null");
                return;
            }

            if(student.getAge().toString().length()<1||student.getAge().toString().length()>3){
                errors.rejectValue("age",null,null,"请问你是个妖怪吗?");
            }
        }

    }
}
