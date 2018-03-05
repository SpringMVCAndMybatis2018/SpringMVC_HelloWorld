package com.anlu.springmvc.controller;

import com.anlu.springmvc.model.FileModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.multi.MultiToolTipUI;
import java.io.File;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("fileUpload", "fileUpload", file);
        return modelAndView;
    }
    @RequestMapping(value="/fileUploadPage", method = RequestMethod.POST)
    public String fileUpload(FileModel file, BindingResult result, ModelMap model)throws Exception {
        try
        {
            if(result.hasErrors()){
                System.out.println("validation error");
                return "fileuploadPage";
            }else{
                MultipartFile multipartFile = file.getFile();
                String uploadPath = "F:\\upload\\"+ File.separator + "temp" + File.separator;
                //Now do something with file...
                FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getFile().getOriginalFilename()));
                String fileName = multipartFile.getOriginalFilename();
                model.addAttribute("fileName", fileName);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "fileuploadPage";
    }


}
