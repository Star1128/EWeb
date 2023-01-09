package com.ethan.eweb.controller;

import com.ethan.eweb.prop.BaseProp;
import com.ethan.eweb.response.CommonState;
import com.ethan.eweb.response.ResponseResult;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author Ethan 2023/1/8
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    private static void printHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "  ==> " + value);
        }
    }

    @PostMapping({"/upload/single"})
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        printHeader(request);
        return saveFile(file);
    }

    @PostMapping({"/upload/multiple"})
    public ResponseResult uploadFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        printHeader(request);
        if (files != null && files.length > 0) {
            System.out.println("Files Count ==> " + files.length);
            try {
                for (MultipartFile file : files) {
                    saveFile(file);
                }
                ResponseResult result = new ResponseResult(CommonState.UPLOAD_SUCCESS);
                StringBuilder builder = new StringBuilder();
                for (MultipartFile file : files) {
                    builder.append(BaseProp.UPLOAD_PATH).append(file.getOriginalFilename()).append(" ");
                }
                result.setMsg("成功上传" + files.length + "个文件，路径为：" + builder);
                return result;
            } catch (Exception exn) {
                exn.printStackTrace();
            }
        }

        return new ResponseResult(CommonState.UPLOAD_FAILED);
    }

    @PostMapping({"/upload/param"})
    public ResponseResult uploadFileWithParam(@RequestParam("file") MultipartFile file, @RequestParam("description") String description, HttpServletRequest request) {
        printHeader(request);
        ResponseResult responseResult = saveFile(file);
        if (responseResult.isSuccess()) {
            responseResult.setData("文件描述为：" + description);
        }
        return responseResult;
    }

    @GetMapping({"/download/{fileName}"})
    public ResponseResult download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        try {
            int i = Integer.parseInt(fileName);
            if (i < 1 || i > BaseProp.COUNT_IMGS) {
                return new ResponseResult(CommonState.DOWNLOAD_FAILED);
            }
        } catch (Exception exn) {
            exn.printStackTrace();
            return new ResponseResult(CommonState.DOWNLOAD_FAILED);
        }

        fileName = fileName + ".png";
        ClassPathResource resource = new ClassPathResource("static/imgs/" + fileName);
        long length = resource.contentLength();
        System.out.println("Content Length ==> " + length);
        response.setContentType("application/x-msdownload;");
        response.addHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
        response.addHeader("Content-Length", String.valueOf(length));

        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            InputStream is = resource.getInputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            return new ResponseResult(CommonState.DOWNLOAD_SUCCESS);
        } catch (Exception exn) {
            exn.printStackTrace();
            return new ResponseResult(CommonState.DOWNLOAD_FAILED);
        }
    }

    private ResponseResult saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseResult(CommonState.UPLOAD_FAILED);
        } else {
            String fileName = file.getOriginalFilename();
            File dest = new File(BaseProp.UPLOAD_PATH + fileName);
            if (!dest.getParentFile().exists()) {
                boolean mkdir = dest.getParentFile().mkdirs();
            }

            try {
                // 这里有坑：必须明确指定绝对路径，否则会出现 FileNotFound
                file.transferTo(dest.getAbsoluteFile());
                ResponseResult result = new ResponseResult(CommonState.UPLOAD_SUCCESS);
                result.setMsg("成功上传，路径为：" + dest);
                return result;
            } catch (Exception exn) {
                exn.printStackTrace();
                return new ResponseResult(CommonState.UPLOAD_FAILED);
            }
        }
    }
}
