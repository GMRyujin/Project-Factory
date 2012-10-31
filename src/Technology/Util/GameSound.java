package Technology.Util;

import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaRecorder.AudioSource;
import android.media.SoundPool;
import android.media.audiofx.AudioEffect;
import android.view.SoundEffectConstants;

/**
 * @brief 사운드들을 로드하고, 실행하는 클래스입니다. 이 클래스는 기능이 더이상 추가되지 않는다고 판단, 상속받지 않는다.
  이 클래스는 싱글톤 패턴으로 정의되었으며, 전역에서 어디에서든지 호출할수 있습니다. *Init은 시스템 초기에 초기화 됩니다. (GameView 생성자안에서 Init함수가 불리워진다.)
 * @version 0.1ver
 * */
public final class GameSound {
	static GameSound gs = null;
	
	SoundPool pool = null;
	Map<String,Integer> map;
	Context mContext;
	
	/**
	 * @methop 사운드 객체를 불러옵니다.
	 * */
	public static GameSound getInstance()
	{
		if(gs == null){//반드시 Init함수를 호출해주어야 한다.
			Assert.fail();	
		}
		return gs;
	}
	
	
	/**
	 * @method 반드시 이 함수를 먼저 호출해주어야 한다. 이 함수를 먼저 호출하지 않으면 getInstance() 메소드 사용시 에러가 날 것이다. 
	 * 이 함수는 GameView 클래스의 생성자안에서 사용된다.
	 * @param Context 
	 * */
	public static void Init(Context context){
		gs = new GameSound(context,32);
	}
	
	/**
	 * @method 반드시 이 함수를 먼저 호출해주어야 한다. 이 함수를 먼저 호출하지 않으면 getInstance() 메소드 사용시 에러가 날 것이다. 
	 * 이 함수는 GameView 클래스의 생성자안에서 사용된다.
	 * @param Context
	 * @param maxStreams : 최대 출력 음원 갯수. 이 인자를 전달하지 않으면 기본값은 32개이다.
	 * */
	public static void Init(Context context,int maxStreams){
		gs = new GameSound(context,maxStreams);
	}
	
	
	
	/**
	 * @method 사운드를 로드합니다. 
	 * @param key : 사운드를 플레이할 키.
	 * @param res : 리소스 정보 (Integer값)
	 * */
	public void Load(String key,int res)
	{
		int soundValue = pool.load(mContext,res,1);
		map.put(key, soundValue);
	}

	/**
	 * @method 선택한 키를 이용해 사운드를 재생합니다.
	 * @param key : Load할때 지정한 Key
	 * @param leftVolume,rightVolume : 불륨
	 * @param loop : 반복에 대한 정보로서, 0은 반복없음, 1은 1번 반복( 총 두 번 재생 ), -1은 무한 반복입니다.
	 * @param rate : 재생 속도입니다. 2를 쓰면 2배로 빨리 재생됩니다.
	 * */
	public int Play(String key,float leftVolume,float rightVolume,int loop,float rate){
		int soundValue = map.get(key);
		return pool.play(soundValue, leftVolume, rightVolume, 0, loop, rate);
	}
	
	/**
	 * @method Play되고 있는 배경 사운드를 정지한다.
	 * @param streamID :  Method에서 리턴된 값
	 * */
	public void Stop(int streamID){
		pool.stop(streamID);
	}
	
	/**
	 * @method GameSound 객체를 초기화하고, SoundPool 객체를 초기화한다. 기본 최대 출력 갯수는 32개이다. 
	 * */
	private GameSound(Context context,int maxStreams)
	{
		map = new HashMap<String,Integer>();
		pool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
		mContext = context;
	}	
}
