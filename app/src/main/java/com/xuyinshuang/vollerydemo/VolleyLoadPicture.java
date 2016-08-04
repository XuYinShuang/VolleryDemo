package com.xuyinshuang.vollerydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by K on 2016/7/22.
 */
public class VolleyLoadPicture {

    //图片载入程序
    private ImageLoader mImageLoader = null;
    //Bitmap储藏室
    private BitmapCache mBitmapCache;

    private ImageLoader.ImageListener one_listener;

    //返回图片对象的方法
    public VolleyLoadPicture(Context context,ImageView imageView){
        one_listener = ImageLoader.getImageListener(imageView, 0, 0);
        //请求排列
        RequestQueue
                mRequestQueue = Volley.newRequestQueue(context);
        mBitmapCache = new BitmapCache();
        mImageLoader = new ImageLoader(mRequestQueue, mBitmapCache);
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    public void setmImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    public ImageLoader.ImageListener getOne_listener() {
        return one_listener;
    }

    public void setOne_listener(ImageLoader.ImageListener one_listener) {
        this.one_listener = one_listener;
    }

    class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        private int sizeValue;
        //
        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    sizeValue = value.getRowBytes() * value.getHeight();
                    return sizeValue;
                }

            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }



}
