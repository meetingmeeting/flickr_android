package com.albert.flickrrecents.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.albert.flickrrecents.MainActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import android.support.v4.util.LruCache;

/**
 * Created by albert on 9/17/17.
 */

public class VolleyControl {
    private static VolleyControl mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyControl(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static VolleyControl getInstance(Context context){
        if(mInstance == null){
            mInstance = new VolleyControl(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }

}
