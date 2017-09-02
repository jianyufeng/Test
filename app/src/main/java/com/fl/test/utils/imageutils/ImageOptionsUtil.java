package com.fl.test.utils.imageutils;

import org.xutils.image.ImageOptions;

/**
 * Created by Administrator on 2016/3/25.
 * 创建imageOptions的单例模式
 * 可以在下载是设置缓存
 * 设置默认及失败图片
 * 设置下载完成时的动画等
 */
public class ImageOptionsUtil {
    private static ImageOptions options = null;
    public static ImageOptions getImagesOption() {
        if (options == null) {
            options = new ImageOptions.Builder()
                    .setUseMemCache(true)
//                    .setFailureDrawableId(R.mipmap.ic_launcher)
//                    .setAnimation(AnimationUtils.loadAnimation(x.app().getApplicationContext(), R.anim.animation_image_item))//设置下载图片的动画
                    .build();
        }
        return options;
    }

}
