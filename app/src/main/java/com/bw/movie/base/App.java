package com.bw.movie.base;

import android.app.Application;
import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/5 10:07
 */
public class App extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setMaxCacheSize(1024*1024*10)
                .setBaseDirectoryPath(getExternalCacheDir().getAbsoluteFile())
                .setBaseDirectoryName("Image")
                .build();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        //初始化
        Fresco.initialize(this,build);
    }

    public static Context getContext() {
        return context;
    }
}
