package com.yys.szcp.controller;

import com.yys.szcp.config.jwt.JwtIgnore;
import com.yys.szcp.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("sys/image")
public class UploadImgController {


    @Value("${pathDirImg}")
    private String pathDirImg;


    @RequestMapping(value = "uploadImage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @JwtIgnore
    public ResultUtil uploadImage(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String prefix = "";
        String dateStr = "";
        String monthStr = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                dateStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                String filepath = pathDirImg + File.separator + dateStr + "_" + monthStr + "." + prefix;
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }

        return ResultUtil.success((File.separator + "sys" + File.separator + "image" + File.separator + "downLoadImage" + File.separator + dateStr + "_" + monthStr + "." + prefix).replace("\\", "/"));
    }


    @RequestMapping(value = "downLoadImage/{img}", method = {RequestMethod.GET, RequestMethod.POST})
    @JwtIgnore
    public void downLoadBanner(@PathVariable("img") String img, HttpServletRequest request, HttpServletResponse response) {
        OutputStream os = null;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(new File(pathDirImg + File.separator + img)));
            response.setContentType("image/png");
            os = response.getOutputStream();

            if (image != null) {
                ImageIO.write(image, img.substring(img.lastIndexOf(".") + 1), os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
