package Technology.Util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * @biref 음악을 플레이 할때 사용하는 플레이어이다.
 * 
 * 사용방법은 우선 put 메소드를 통해서 사운드를 저장한다. 그리고 get 메소드를 통해서 미디어플레이어를 얻은후 실행한다.  
 * */
public class MusicPlayer {
	static Map<String,MediaPlayer> map = new HashMap<String,MediaPlayer>();
	static Context sContext = null;
	
	/**
	 * @brief key로 지정한 미디어플레이어를 가져온다.
	 * */
	public static MediaPlayer get(String key){
		return map.get(key);
	}
	
	/**
	 *  @brief 사운드를 로드하고, key를 지정한다. 이 키는 나중에 이 미디어플레이어 객체를 얻을 때 필요한 것이다. 
	 */
	public static void put(String key,int r){
		MediaPlayer mp  = MediaPlayer.create(sContext, r);
		map.put(key, mp);
	}
	
	/* GameView에서 기본적으로 호출해준다. */
	public static void Init(Context context){
		sContext = context;
	}
}
