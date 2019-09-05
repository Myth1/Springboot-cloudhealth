package com.shgbit.cloudhealth.controller;

import com.shgbit.cloudhealth.config.ServerConfig;
import com.shgbit.cloudhealth.exception.ImageFile;
import com.shgbit.cloudhealth.exception.result.Result;
import com.shgbit.cloudhealth.exception.result.ResultEnum;
import com.shgbit.cloudhealth.exception.result.ResultUtil;
import com.shgbit.cloudhealth.model.TakeMedicineRemind;
import com.shgbit.cloudhealth.service.TakeMedicineService;
import com.shgbit.cloudhealth.util.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.shgbit.cloudhealth.exception.result.ResultEnum.LOGINID_TAKEMEDICINEREMIND_NOTEXIST_ERROR;

@Controller
public class TakeMedicineController {
    public static final Logger logger = LoggerFactory.getLogger(TakeMedicineController.class.getSimpleName());

    @Autowired
    private TakeMedicineService takeMedicineService;

    @Autowired
    private ServerConfig serverConfig;

    @Deprecated
    @ApiOperation(value = "上传服药提醒", notes = "上传服药提醒接口")
    @ApiImplicitParam(name = "takeMedicineRemind", value = "TakeMedicineRemind", required = true, dataType = "TakeMedicineRemind", paramType = "body")
    @PostMapping(value = "/addTakeMedicineRemind")
    @ResponseBody
    public Result addTakeMedicineRemind(@RequestBody TakeMedicineRemind takeMedicineRemind) {

        String createTime = DateUtils.getDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        takeMedicineService.insertTakeMedicineRemind(new TakeMedicineRemind()
                .setLoginId(takeMedicineRemind.getLoginId())
                .setTakeMedicineRemindId(UUID.randomUUID().toString())
                .setFinished(false)
                .setImgAddress(takeMedicineRemind.getImgAddress())
                .setFirstTimeTakeMedicine(takeMedicineRemind.getFirstTimeTakeMedicine())
                .setFrequencyTakeMedicine(takeMedicineRemind.getFrequencyTakeMedicine())
                .setAppNotify(takeMedicineRemind.isAppNotify())
                .setMedicineName(takeMedicineRemind.getMedicineName())
                .setCreateTime(createTime)
                .setUpdateTime(createTime));
        return ResultUtil.success();
    }

    @Deprecated //废弃
    @ApiOperation(value = "服药提醒中药品图片", notes = "上传药品图片接口")
    @PostMapping(value = "/uploadTakeMedicineRemindImage")
    @ResponseBody
    public Result uploadTakeMedicineRemindImage(MultipartFile file) {
       /* if (file == null) {
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
        String uploadPath = "";
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            uploadPath = path + "static/images/medicine_images/";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        String imageAddress = serverConfig.getUrl() + "/images/medicine_images/" + fileName;*/
        return ResultUtil.success(new ImageFile(""));
    }

    @Deprecated
    @ApiOperation(value = "查群服药提醒", notes = "查询服药提醒接口")
    @PostMapping(value = "/queryTakeMedicineRemind")
    @ResponseBody
    public Result queryTakeMedicineRemind(String loginId) {
        List<TakeMedicineRemind> takeMedicineReminds = takeMedicineService.queryTakeMedicineRemind(loginId);
        return ResultUtil.success(takeMedicineReminds);
    }

    @Deprecated
    @ApiOperation(value = "更新服药提醒", notes = "更新服药提醒接口")
    @PostMapping(value = "/updateTakeMedicineRemind")
    @ResponseBody
    public Result updateTakeMedicineRemind(@RequestBody TakeMedicineRemind takeMedicineRemind) {
        //先查询 loginId 更新服药id 存不存在
        logger.info("getLoginId", takeMedicineRemind.getLoginId());
        List<TakeMedicineRemind> takeMedicineReminds = takeMedicineService.queryTakeMedicineRemind(takeMedicineRemind.getLoginId());
        if (takeMedicineReminds == null || takeMedicineReminds.size() == 0) {
            return ResultUtil.error(LOGINID_TAKEMEDICINEREMIND_NOTEXIST_ERROR);
        }
        boolean isExistTakeMedicineRemindId = false;
        for (TakeMedicineRemind takeMedicineRemindsItem : takeMedicineReminds) {
            if (takeMedicineRemindsItem.getTakeMedicineRemindId().equals(takeMedicineRemind.getTakeMedicineRemindId())) {
                isExistTakeMedicineRemindId = true;
            }
        }
        if (!isExistTakeMedicineRemindId) {
            return ResultUtil.error(ResultEnum.TAKEMEDICINEREMIND_NOTEXIST_ERROR);
        }

        String updateTime = DateUtils.getDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        takeMedicineRemind.setUpdateTime(updateTime);
        takeMedicineService.updateTakeMedicineRemind(takeMedicineRemind);
        return ResultUtil.success();
    }

    @Deprecated
    @ApiOperation(value = "删除服药提醒", notes = "删除服药提醒接口")
    @PostMapping(value = "/deteleTakeMedicineRemind")
    @ResponseBody
    public Result deteleTakeMedicineRemind(String loginId, String takeMedicineRemindId) {
        //先查询 loginId 更新服药id 存不存在
        List<TakeMedicineRemind> takeMedicineReminds = takeMedicineService.queryTakeMedicineRemind(loginId);
        if (takeMedicineReminds == null || takeMedicineReminds.size() == 0) {
            return ResultUtil.error(LOGINID_TAKEMEDICINEREMIND_NOTEXIST_ERROR);
        }
        boolean isExistTakeMedicineRemindId = false;
        for (TakeMedicineRemind takeMedicineRemindsItem : takeMedicineReminds) {
            if (takeMedicineRemindsItem.getTakeMedicineRemindId().equals(takeMedicineRemindId)) {
                isExistTakeMedicineRemindId = true;
            }
        }
        if (!isExistTakeMedicineRemindId) {
            return ResultUtil.error(ResultEnum.TAKEMEDICINEREMIND_NOTEXIST_ERROR);
        }
        takeMedicineService.deteleTakeMedicineRemind(new TakeMedicineRemind().setLoginId(loginId).setTakeMedicineRemindId(takeMedicineRemindId));
        return ResultUtil.success();
    }
}
