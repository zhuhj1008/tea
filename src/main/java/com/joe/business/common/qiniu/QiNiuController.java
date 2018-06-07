package com.joe.business.common.qiniu;

import com.joe.business.commodity.controller.CommodityController;
import com.joe.util.mvc.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 七牛请求控制
 * create by Joe on 2018-05-28 12:51
 **/
@Controller
@RequestMapping("/qiNiu")
public class QiNiuController {

    private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    QiNiuService qiNiuService;

    /**
     * 获取七牛上传文件token
     * （有效时间：三小时）
     *
     * @return token
     */
    @RequestMapping("/getUploadToken")
    @ResponseBody
    public Object getUploadToken() {
        String uploadToken;
        try {
            uploadToken = qiNiuService.getUploadToken();
        } catch (Exception ex) {
            logger.error("request upload token fail");
            return ResponseEntity.getFailEntity("request upload token fail");
        }
        logger.info("client request upload token, token is {}", uploadToken);

        return ResponseEntity.getSuccessEntity("请求成功",uploadToken);
    }
}
