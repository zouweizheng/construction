package com.gprint.common;

import android.app.Application;

public class MyApplication extends Application {
	 /**
     * 寮曞彂寮傚父锛氬湪涓�浜涗笉瑙勮寖鐨勪唬鐮佷腑缁忓父鐪嬪埌Activity鎴栬�呮槸Service褰撲腑瀹氫箟璁稿闈欐�佹垚鍛樺睘鎬с�傝繖鏍峰仛鍙兘浼氶�犳垚璁稿鑾悕鍏跺鐨� null pointer寮傚父銆�
     */ 

    /**
     * 寮傚父鍒嗘瀽锛欽ava铏氭嫙鏈虹殑鍨冨溇鍥炴敹鏈哄埗浼氫富鍔ㄥ洖鏀舵病鏈夎寮曠敤鐨勫璞℃垨灞炴�с�傚湪鍐呭瓨涓嶈冻鏃讹紝铏氭嫙鏈轰細涓诲姩鍥炴敹澶勪簬鍚庡彴鐨凙ctivity鎴�
     * Service鎵�鍗犵敤鐨勫唴瀛樸�傚綋搴旂敤鍐嶆鍘昏皟鐢ㄩ潤鎬佸睘鎬ф垨瀵硅薄鐨勬椂鍊欙紝灏变細閫犳垚null pointer寮傚父
     */ 

    /**
     * 瑙ｅ喅寮傚父锛欰pplication鍦ㄦ暣涓簲鐢ㄤ腑锛屽彧瑕佽繘绋嬪瓨鍦紝Application鐨勯潤鎬佹垚鍛樺彉閲忓氨涓嶄細琚洖鏀讹紝涓嶄細閫犳垚null pointer寮傚父
     */ 
    private String token; 
    private String userAgent;

//    @Override 
//    public void onCreate() { 
//        // TODO Auto-generated method stub 
//        super.onCreate(); 
//    } 

    public String getToken() { 
        return token; 
    } 

    public void setToken(String token) { 
        this.token = token; 
    }
    
    public String getUserAgent() {
        return userAgent; 
    } 

    public void setUserAgent(String userAgent) { 
        this.userAgent = userAgent; 
    }

	
}
