package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.picturebackend.model.entity.Picture;
import com.yupi.picturebackend.service.PictureService;
import com.yupi.picturebackend.mapper.PictureMapper;
import org.springframework.stereotype.Service;

/**
* @author LENOVO
* @description 针对表【picture(图片)】的数据库操作Service实现
* @createDate 2026-02-14 16:43:36
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService{

}




