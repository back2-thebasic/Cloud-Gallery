package com.yupi.picturebackend.service;

import com.yupi.picturebackend.model.dto.picture.PictureUploadRequest;
import com.yupi.picturebackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.picturebackend.model.entity.User;
import com.yupi.picturebackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

/**
* @author LENOVO
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2026-02-14 16:43:36
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

}
