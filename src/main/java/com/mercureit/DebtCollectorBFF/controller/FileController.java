package com.mercureit.DebtCollectorBFF.controller;

import com.mercureit.DebtCollectorBFF.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/segmentation")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/read")
    public String readFile() throws IOException {
        System.out.println("read success");
        return fileService.readFile();
    }

    @PostMapping("/write")
    public void writeFile(@RequestBody String content) throws Exception {
        fileService.writeFile(content);
        System.out.println("write success");
    }

    @GetMapping("/filename")
    public String getFileName() throws IOException {
        System.out.println("filename success");
        return fileService.getFileName();
    }
}
