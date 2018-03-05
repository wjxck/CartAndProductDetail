package com.example.disanzhouzhoukaomoni.utils;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.disanzhouzhoukaomoni.R;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by dell on 2018-03-03  09:22
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //配置磁盘缓存
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(this.getCacheDir())//缓存图片基路径
                .setBaseDirectoryName(getString(R.string.app_name))//文件夹名
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig)
                .build();
        //初始化Fresco
        Fresco.initialize(this, imagePipelineConfig);
    }
}
