package com.yupi.picturebackend.Controller;

import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import com.yupi.picturebackend.annotation.AuthCheck;
import com.yupi.picturebackend.common.BaseResponse;
import com.yupi.picturebackend.common.ResultUtils;
import com.yupi.picturebackend.constant.UserConstant;
import com.yupi.picturebackend.exception.BusinessException;
import com.yupi.picturebackend.exception.ErrorCode;
import com.yupi.picturebackend.manager.CosManager;
import com.yupi.picturebackend.model.dto.picture.PictureUploadRequest;
import com.yupi.picturebackend.model.entity.User;
import com.yupi.picturebackend.model.vo.PictureVO;
import com.yupi.picturebackend.service.PictureService;
import com.yupi.picturebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<PictureVO> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);

        return ResultUtils.success(pictureVO);
    }

}
