package com.eshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FileUploadController
 *
 * @author liangyh
 * @email 10856214@163.com
 * @date 2018/5/10
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private static String UPLOADED_FOLDER = "D://upload//lib//";

    @GetMapping("")
    public String upload() {
        return "upload";
    }

    @GetMapping("/calendar")
    public String calendar() throws IOException {
        return "forward:/uflo/calendar";
    }

    @GetMapping("/central")
    public String central() {
        return "forward:/uflo/central";
    }

    @GetMapping("/designer")
    public String designer() {
        return "forward:/uflo/designer";
    }

    @GetMapping("/diagram")
    public String diagram() {
        return "forward:/uflo/diagram";
    }

    @GetMapping("/todo")
    public String todo() {
        return "forward:/uflo/todo";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            //获取当前类加载器上下文真实路径
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/demo/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}