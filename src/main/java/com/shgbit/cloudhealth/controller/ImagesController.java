package com.shgbit.cloudhealth.controller;

import com.shgbit.cloudhealth.config.ServerConfig;
import com.shgbit.cloudhealth.exception.ImageFile;
import com.shgbit.cloudhealth.exception.result.Result;
import com.shgbit.cloudhealth.exception.result.ResultEnum;
import com.shgbit.cloudhealth.exception.result.ResultUtil;
import com.shgbit.cloudhealth.service.ImagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
@Api("图片")
public class ImagesController {

    private final  static  Logger logger = LoggerFactory.getLogger(ImagesService.class.getSimpleName());
    @Autowired
    private ImagesService imagesService;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${localhost.ip}")
    private String ip;
    @Autowired
    private ServerConfig serverConfig;

    @ApiOperation(value = "上传图片", notes = "上传图片接口")
    @PostMapping(value = "/uploadImage")
    @ResponseBody
    public Result uploadImage(MultipartFile file) {
        if (file == null) {
            return ResultUtil.error(ResultEnum.FILENULL_ERROR);
        }
        //文件不能大于 1MB;
        if (file.getSize() > 1024 * 1024) {
            return ResultUtil.error(ResultEnum.FILEMAX_ERROR);
        }
        String fileName = file.getOriginalFilename();  // 文件名
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        logger.info(fileName);
        //拼接上传路径  图片存储地址
        String uploadPath = uploadFolder;
      /*  try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            uploadPath = path + "static/images/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        logger.info(uploadPath);
        File dest = new File(uploadPath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        //图片访问地址
        String imageAddress = "http://" + ip + ":" + serverConfig.getServerPort() + "/images/" + fileName;
        return ResultUtil.success(new ImageFile(imageAddress));
    }

}
