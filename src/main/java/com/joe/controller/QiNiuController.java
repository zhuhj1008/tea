package com.joe.controller;

import com.joe.dto.ApiResult;
import com.joe.service.QiNiuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛请求控制
 * create by Joe on 2018-05-28 12:51
 **/
@Slf4j
@Api(tags={"七牛接口"})
@RestController
@RequestMapping("/qiNiu")
public class QiNiuController {

    @Autowired
    private QiNiuService qiNiuService;

    /**
     * 获取七牛上传文件token（有效时间：三小时）
     */
    @PostMapping("/getUploadToken")
    @ApiOperation(value = "获取七牛上传文件token", notes = "获取七牛上传文件token")
    public ApiResult getUploadToken() {
        String uploadToken;
        try {
            uploadToken = qiNiuService.getUploadToken();
        } catch (Exception ex) {
            log.error("获取七牛云token失败。异常信息：{}。", ex);
            return ApiResult.getFailEntity("request upload token fail");
        }
        log.info("获取七牛云token成功，token：{}", uploadToken);

        return ApiResult.getSuccessEntity(uploadToken);
    }
}
