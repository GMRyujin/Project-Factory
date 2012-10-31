package Technology.Util;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

/**
 * @brief 진동을 주는 바이브레이터  래퍼함수이다. getInstance 메서드를 통해 바이브레이터를 얻을수있다.
 * 
 * 이 클래스를 사용하기 위해서는 AndroidManifest 파일에 밑의 퍼미션을 추가해주기 바란다.
 * <uses-permission android:name="android.permission.VIBRATE"/>
 * */
public class TechVibrator{
	static Vibrator me = null;
	
	public static Vibrator getInstance(){
		return me;
	}
	
	/**
	 * @brief 이 클래스를 초기화한다. -> 바이브레이터를 구한다.
	 * */
	public static void init(Activity act){
		me = (Vibrator)act.getSystemService(Context.VIBRATOR_SERVICE);
	}
}
