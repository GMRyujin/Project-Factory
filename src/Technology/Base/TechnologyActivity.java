package Technology.Base;

import KeyboardPang.GameMain;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;


/**
 * @mainpage Technology Main Page  ver 0.4
 * 
 * 쓰레드 : 이 프레임워크에서 액티비티가 백그라운드로가게되면 쓰레드가 죽는다. 그리고 다시 포어그라운드로 나오게 되면 쓰레드를 생성한다.
 * 따라서 게임을 만들때 쓰레드의 생성자 안에 무엇을 하는 것은 각별한 주의 가 필요하다.
 * 
 * 이를 위해서 최초 실행을 위해 Initialize 메소드가 존재한다.
 * */


/**
 * @brief 이 클래스는 이 객체를 생성했을경우 이 프로그램이 돌아가기위한 최소한의 프레임워크를 가지고있다.
 * 이 클래스에서 구현한 클래스들은 기능의 확장이 아닌 기능의 변경이기 때문에 오버라이드시 상위 객체의 메소드들을 호출할 필요가 없다.
 * 이 클래스를 상속받는 것은 선택사항이다.
 * */
public class TechnologyActivity extends Activity{
	RelativeLayout layout;
	GameView view;
	
	 /**
     * @brief 어떤 상황에서든 이 메소드는 반드시 호출해야한다. 액티비티의 환경을 설정하는 것이다.
     * */
     public void InitTechnologyActivity(){
    	 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	 layout = new RelativeLayout(this);
    }
    
    /**
     * @brief 이 메소드를 통해서 게임 뷰를 레이아웃에 등록할수 있다.
     * */
    public void SetGameView(GameView view){
    	this.view = view;
    	this.view.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    	layout.addView(this.view);
    }
    
    /**
     * @brief 모든 설정이 끝나면 이 메소드를 호출하여 레이아웃을 등록해준다.
     * */
    public void ApplyCurrentLayout(){
    	setContentView(layout);
    }
    
    /**
     * @brief 이 클래스의 현재 레이아웃을 가져온다.
     * */
    public RelativeLayout GetCurrentLayout()
    {
    	return layout;
    }
  
	/* 광고 세팅 */
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
    }
    
	@Override
	protected void onPause() {
		Log.v("AdP","OnPause Envet");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.v("AdP","onStop Envet");
		super.onStop();
	}

	@Override
	protected void onRestart() {
		Log.v("AdP","OnRestart Envet");
		super.onRestart(); 
	}

	@Override
	protected void onResume() {
		Log.v("AdP","onResume Envet");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.v("AdP","OnStart Envet");
		super.onStart();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /* Control 설정 */
    @Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
    	return view.onKeyLongPress(keyCode, event);
	}

	@Override
    public boolean onKeyDown( int keyCode, KeyEvent event ){
		Log.v("AdP","Pause Envet");
    	return view.onKeyDown(keyCode, event);
    }
    
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event){
		return view.onKeyUp(keyCode, event);
	}
	
}
