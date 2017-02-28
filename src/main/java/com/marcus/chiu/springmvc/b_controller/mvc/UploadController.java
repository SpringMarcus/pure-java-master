package com.marcus.chiu.springmvc.b_controller.mvc;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public AjaxResponse giftCertificateUpload(@RequestParam("file") MultipartFile file) throws Exception {

        AjaxResponse response = new AjaxResponse();

        try {

            File realFile = File.createTempFile("giftCertificateUpload", "csv");
            realFile.deleteOnExit();
            file.transferTo(realFile);

            String fileName = file.getOriginalFilename();

            response.setFileName(fileName);
            response.setSuccess(true);

        } catch(IOException e) {
            response.setSuccess(false);
        }

        return response;
    }
}

@Data
class AjaxResponse {
    private Boolean success;
    private String fileName;
}
