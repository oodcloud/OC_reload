package cn.oddcloud.www.oc_reload.Utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;


/**
 * LRU图片缓存
 * @author grumoon
 *
 */
public class LruImageCache implements ImageLoader.ImageCache {

	private LruCache<String, Bitmap> lruCache;
	  
    public LruImageCache() {  
        int maxSize = 10 * 1024 * 1024;  
        lruCache = new LruCache<String, Bitmap>(maxSize/4) {
            @Override  
            protected int sizeOf(String key, Bitmap bitmap) {  
                return bitmap.getRowBytes() * bitmap.getHeight();  
            }  
        };  
    }  
  
    @Override  
    public Bitmap getBitmap(String url) {  
        return lruCache.get(url);  
    }  
  
    @Override  
    public void putBitmap(String url, Bitmap bitmap) {  
    	lruCache.put(url, bitmap);  
    }  

}
