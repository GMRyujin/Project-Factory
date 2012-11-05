package Technology.Base;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.ridempang.R;

import Technology.Game.GameWorld;
import Technology.Util.BitmapLoader;
import Technology.Util.GameSound;
import Technology.Util.MusicPlayer;
import Technology.Util.TechVibrator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	protected Context mContext;
	SurfaceHolder mHolder;
	GameThread mThread;
	int width, height;

	Activity mActivity;

	boolean isTouchMove = false, isTouchDown = false, isTouchUp = false;

	ArrayList<PointF> touchMove = new ArrayList<PointF>();
	ArrayList<PointF> touchDown = new ArrayList<PointF>();
	ArrayList<PointF> touchUp = new ArrayList<PointF>();

	private boolean mFlag;
	private Handler mHandler;
	
	//로고를 한번만 출력하게 한다.
	boolean isShowLogo = false;

	// -------------------------------------
	// 占쏙옙占쏙옙 (占쏙옙체화占쏙옙占쏙옙 占쏙옙占쏙옙, 占쏙옙占싸그뤄옙 타占쏙옙틀占쏙옙 占쏙옙占쏙옙)
	// -------------------------------------
	public GameView(Context context, boolean isFullSceen, boolean isShowTitle) {
		super(context);

		mActivity = (Activity) context;
		mContext = context; // 占싸쇽옙占쏙옙 占싼억옙 占쏙옙 context占쏙옙 占쏙옙占쏙옙占�占쏙옙占쏙옙

		SetScreen(isFullSceen,isShowTitle);

		/* Sound 객체를 초기화하고 생성한다. 이로써 GameSound는 전역으로 사용가능하다. */
		GameSound.Init(context, 32);
		
		/* 바이브레이터를 구한다. */
		TechVibrator.init(mActivity);
		
		/* 뮤직 플레이어를 초기화한다. */
		MusicPlayer.Init(context);
		
		
		// mHolder를 가져온다.
		SurfaceHolder holder = getHolder(); // test
		holder.addCallback(this);
		mHolder = holder;
		
		/* 2초후에 꺼지게하는 핸들러 . 액티비티 활성화가 바뀌면 쓰레드처럼  이것도 꺼진다. 따라서 핸들러도 다시 생성해준다. */
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					mFlag = false;
					// 2초가 지나면 다시 Falg 를 false로 바꾼다.
					Log.d("", "handleMessage mFlag : " + mFlag);
				}
			}
		};
	}

	/**
	 * @brief 이 함수는 풀스크린과 타이틀을 설정할수있다.
	 * */
	public void SetScreen(boolean isFullSceen, boolean isShowTitle) {
		if (isFullSceen == true) {// 占쏙옙체화占쏙옙占쏙옙 占쏙옙占쏙옙
			mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		if (isShowTitle == true) {// 타티틀占쏙옙 占쏙옙占쏙옙
			mActivity.getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

	public Activity getActivity() {
		return mActivity;
	}

	/**
	 * @method 화면의 Width를 구합니다.
	 * */
	public int GetScreenWidth() {
		return width;
	}

	/**
	 * @method 화면의 Height를 구합니다.
	 * */
	public int GetScreenHeight() {
		return height;
	}

	/**
	 * 반드시 하위 클래스에서 오버라이딩하여 재구현해야한다. 그리고 자신의 쓰레드로 만들어야한다.
	 * */
	protected GameThread CreateCurrentThread() {
		return new GameThread();
	}
	
	private boolean isLoaded = false;//한번만 로드하게 하는 변수
	/**
	 *  Surface가 생성될때 자동으로 호출되눈 콜백 메서드
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		//액티비티 전환시 쓰레드는 정지되고 종료되므로 다시 만들어 준다.
		//쓰레드를 만들고 실행한다.
		mThread = CreateCurrentThread();// Thread Created Then
		if(!isLoaded){//만약 처음 쓰레드가 실행되는 거라면 Thread의 Initialize를 실행한다.
			mThread.onInitialize();
			isLoaded = true;
		}
		mThread.start();
	
		
		Log.v("AdP","surfaceCreated");
	}

	// -------------------------------------
	// 화면(Surface) 사이즈가 변경되면 자동으로 호출되는 콜백 메서드
	// -------------------------------------
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.v("AdP", "SurfaceChanged Event");
	}

	// -------------------------------------
	// Surface가 소멸될때 자동으로 호출되눈 콜백 메서드
	// -------------------------------------
	public void surfaceDestroyed(SurfaceHolder holder) {
		/** 
		 * @brief 아래 코드는 잘못됬다. 그 이유는 쓰레드는 서페이스가 파괴되면, 즉 액티비티가 백그라운드로 가게될때 쓰레드는 알아서 파괴된다.
		 * 그런데 아래 코드가 주석처리가 되지 않았을떄 이 메소드가 호출되게 된다면 아래 코드가 중복실행되어 쓰레드가 또 파괴되게 된다. 따라서 아래 코드늕 실행되면 안된다. 
		 * 
		 * @code
		 * boolean done = true;
		while (done) {
			try {
				mThread.join();// 쓰레드가 모두 끝날때까지 기다린다.
				done = false;
			} catch (InterruptedException e) { // 占쏙옙占싶뤄옙트占쏙옙 占쌩삼옙占싹몌옙?
				// 占쏙옙 占쏙옙호 占쏙옙占쏙옙 - 占싣뱄옙占싶듸옙 占쏙옙占쏙옙
			}
			//mHandler = null;
			mThread = null;
		} // while
		
		@endcode
		 */
		// getHandler().removeCallbacks(mThread);//콜백제거 ////TODO: 안해도되는듯 하다.
		Log.v("AdP", "SurfaceDestroyed Event");
	}

	// ================================== 쓰레드 컨트롤러  ==================================
	
	boolean bPause = false;
	/**
	 * @brief 쓰레드를 다시 시작한다. Not Surface Changed 아직 지원하지 않는다.
	 * */
	public void ThreadControl_Restart() {
		bPause = false;
	}
	/**
	 * @brief 쓰레드를 일시정지한다. 다시 시작할수 있다. Not Surface Changed 아직 지원하지 않는다.
	 * */
	public void ThreadControl_Pause() {
		bPause = true;
	}
	
	
	private int fps = 0;
	private Bitmap imgBack = null;// 지울떄 사용될 비트맵
	
	/**
	 * @brief Game을 실제로 업데이트하고, 그리는 주체이다. 
	 * 쓰레드는 액티비티의 전환이 일어날때마다(백그라운드 <-> 포어그라운드 전환시) 생성, 삭제되므로 가능한한 초기화 작업은 onInitialize메소드에서 작업하도록 한다.
	 * */
	public class GameThread extends Thread {
		public GameThread() {
			Log.v("AdP","Thread Created");
			
			/* 비트맵 로더를 초기화 환다. */
			BitmapLoader.getInstance().init(getResources());
			
			Display display = ((WindowManager) mContext
					.getSystemService(mContext.WINDOW_SERVICE))
					.getDefaultDisplay();
			width = display.getWidth(); // 화占쏙옙占쏙옙 占쏙옙
			height = display.getHeight(); // 화占쏙옙占쏙옙 占쏙옙占쏙옙
			
			/* 배경 설정 (배경이 있어야 이미지가 겹쳐져서 그려지지 않는다.)*/
			if(imgBack == null){
				InitClearColor(Color.BLACK);
			}
		}
		
		/**
		 * @brief Clear메소드 호출시 Clear할 색상을 지정합니다.
		 * */
		protected void InitClearColor(int color){
			Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					bitmap.setPixel(x, y, color);
				}
			}
			if(imgBack != null) imgBack.recycle(); 
			Bitmap temp = bitmap.copy(Bitmap.Config.ARGB_8888, true);
			imgBack = Bitmap.createScaledBitmap(temp, width, height, true);
			temp.recycle();
			bitmap.recycle();
		}
		
		/**
		 * @brief 화면을 지운다.
		 * */
		protected void Clear(Canvas canvas) {
			canvas.drawBitmap(imgBack, 0, 0, null);// 占싼몌옙占쏙옙占�占쏙옙占쏙옙占�Clear
													// 占쏙옙占쌓몌옙 占쏙옙.
		}
		
		/**
		 * @brief 이 쓰레드의 액티비티가 처음으로 생성되었을때 딱 한번 호출되는 함수이다. 여기에서 초기화 작업을 하면 된다.
		 * 
		 * 이 메소드는 엄연히 말해서 쓰레드가 처음으로 완전하게 생성되었을때 실행되는 메소드이다. 따라서 전체 프로글램 기간중 딱 한번만 호출된다.
		 * */
		protected void onInitialize()
		{
			
		}
		
		/**
		 * @brief 이 메소드는 오브젝트를 그리기위해 호출되는 메소드이다. 반드시 호출필요는 없다. 오버라이딩 하여 사용한다.
		 * */
		protected void Draw(Canvas canvas) {
			/* == Start Drawing Code == */
			Clear(canvas);
			/* Print FPS */
			PrintFPS(canvas);
			/* Test Code (占십울옙占쏙옙占쏙옙占�占쏙옙占썩를 占쏙옙占쎌도占쏙옙 占싼댐옙.) */
			// canvas.drawRect(new Rect(10,10,100,100), new Paint()); //이거 호출하니까
			// 렉걸린다. paint 인자를 줘야 안팅긴다.
			/* == End Drawing Code == */
		}
		
		/**
		 * @brief 이 메소드는 오브젝트를 업데이트 하기위해 호출되는 메소드이다. 반드시 호츌할 필요는 없다. 오버라이이딩 하여 사용한다.
		 *  */
		protected void Update(float timeDelta) {// (占쏙옙 占쏙옙占쏙옙 : timeDelta)
			Log.v("Update", "timeDelta : " + timeDelta);
		}

		/**
		 * @brief FPS를 출력한다. 
		 * GameThread private member
		 */
		protected final void PrintFPS(Canvas canvas) {
			/* Print FPS */
			Paint text = new Paint();
			text.setColor(Color.WHITE);
			text.setTextSize(15);
			canvas.drawText("FPS : " + GetFPS(), 0, 10, text);
			Log.v("FPS", "FPS : " + GetFPS());
		}

		
		private int GetFPS() {
			return fps;
		}
		
		/**
		 * [수정금지]
		 * Thread run(프레임 동기화 기술)
		 * */
		@SuppressWarnings("unchecked")
		public final void run() {
			Canvas canvas = null; // canvas占쏙옙 占쏙옙占쏙옙占�

			/* 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 1000ms == 1占쏙옙 */
			long deltaTime = 0;
			long updateTime = System.currentTimeMillis();
			long sumTime = 0;

			int temp_fps = 0;
			
			if(isShowLogo == false){
				/* TODO 무조건 Technology 로고가 보이게 한다. //약 2초간 로고를 보여준다. */
				Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tech_logo);
				try{
					canvas = mHolder.lockCanvas(); 
					synchronized (mHolder) { 
						canvas.drawBitmap(logoBitmap, 
								new Rect(0,0,logoBitmap.getWidth(),logoBitmap.getHeight()),
								new Rect(0,0,getWidth(),getHeight()),new Paint(Paint.ANTI_ALIAS_FLAG));
					}
				}finally {
					mHolder.unlockCanvasAndPost(canvas); 
				}
				
				try {
					sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logoBitmap.recycle();//메모리를 즉시 해제한다.
				isShowLogo = true;//로고를 한번만 출력하게 한다.
			}

			/* 로고 출력이 끝나고.. 게임의 루프이다. */
			try {
				while (!Thread.currentThread().isInterrupted()) {//액티비티가 백그라운드로 가게되면 인터럽트가 일어난다.
					// 쓰레드가 일시정지 하는 지점
					if (bPause) {// 만약 bPause이면 계속 정지한다. 인터럽트는 받을수있다.
						deltaTime = System.currentTimeMillis() - updateTime;// 쓰레드가 정지되도 정상적이니까, 시간계산을 계속해준다.
						updateTime = System.currentTimeMillis();
						continue;
					}
					// 만약 일시정지가 아니라면 완전한 정지 플래그에 false를 대입한다.
					deltaTime = System.currentTimeMillis() - updateTime;
					updateTime = System.currentTimeMillis();

					canvas = mHolder.lockCanvas(); 
					try {
						synchronized (mHolder) { 
							sumTime += deltaTime;
							if (sumTime < 1000) {
								temp_fps++;
							} else {
								fps = temp_fps;
								temp_fps = 0;
								sumTime -= 1000;
							}
							/**
							 * @brief 쓰레드의 동기화를 위해서 여기에서 월드의 이벤트 처리를 해준다. 이것을
							 *        하지않으면 팅긴다.
							 * 
							 * @code itor = touchDown.iterator(); synchronized
							 *       (itor) { while(itor.hasNext()){ Point p =
							 *       itor.next(); world.onActionDown(p.x, p.y);
							 *       } touchDown.clear(); }
							 * 
							 *       itor = touchMove.iterator(); synchronized
							 *       (itor) { while(itor.hasNext()){ Point p =
							 *       itor.next(); world.onActionMove(p.x, p.y);
							 *       } touchMove.clear(); }
							 * 
							 *       itor = touchUp.iterator(); synchronized
							 *       (itor) { while(itor.hasNext()){ Point p =
							 *       itor.next(); world.onActionUp(p.x, p.y); }
							 *       touchUp.clear(); }
							 * 
							 * @codeend
							 * */
							
							/*
							 * 쓰레드의 동기화를 위해서 여기에서 월드의 이벤트 처리를 해준다. 이것을 하지않으면
							 * 팅긴다.
							 */
							GameWorld world = GameWorld.getInstance();
							Iterator<PointF> itor;

							synchronized (touchDown) {
								itor = ((ArrayList<PointF>) touchDown.clone())
										.iterator();
								touchDown.clear();
							}
							while (itor.hasNext()) {
								PointF p = itor.next();
								world.onActionDown(p.x, p.y);
							}

							synchronized (touchMove) {
								itor = ((ArrayList<PointF>) touchMove.clone())
										.iterator();
								touchMove.clear();
							}
							while (itor.hasNext()) {
								PointF p = itor.next();
								world.onActionMove(p.x, p.y);
							}

							synchronized (touchUp) {
								itor = ((ArrayList<PointF>) touchUp.clone())
										.iterator();
								touchUp.clear();
							}
							while (itor.hasNext()) {
								PointF p = itor.next();
								world.onActionUp(p.x, p.y);
							}

							Update(deltaTime * 0.001f);// Update
							Draw(canvas);// Draw
						}
					} finally {
						mHolder.unlockCanvasAndPost(canvas); 
					}
				} // while
			} catch (Exception ex) {
				// 예상했던 예외이므로 넘긴다.
				Log.v("AdP","Therad Run End [By intterrep]" + ex.toString());
			}
			
			Log.v("AdP","Therad Run End");
		} // run
	} // End of Thread
	// -------------------- 쓰레드에서 사용하는 버튼 ----------------------------
	boolean isMultitouchEvent = false;
	
	/**
	 * @brief 멀티터치를 허용한다면 이 메소드를 이용해서 멀티터치를 설정하도록 한다.
	 * */
	public void setMultiTouch(boolean b){
		isMultitouchEvent = b;
	}
	
	public final synchronized boolean onTouchEvent(MotionEvent event) {
		// GameWorld world = GameWorld.getInstance();

		// synchronized (mHolder) {//쓰레드와 동기화를 위해서 반드시 필요하다. 이 것이 없으면 대량 몬스터가
		// 생길때 팅길수도있다. 하지만 만약 이것을 풀게 된다면 터치의 반응속도가 장난이 아니게 된다.
		// 내 생각에는 국이 이것을 하지않아도 쓰레드가 같이 호출하게 되는 임계영역을 동기화시키는 것이 낫다고 판단한다.
		// synchronized (world) {//
		// 큐를 사용해서 자식 쓰레드와 터치 이벤트를 동기화하였다.
		/* 멀티 터치 이벤트 */
		
		int keyAction = event.getAction();
		float x =  event.getX();
		float y =  event.getY();
		
		//Log.v("Debug","Touch : " + event.getX());
		
		if(isMultitouchEvent == true){
			//멀티 터치를 적용한다.
			if(event.getPointerCount() > 1){
				@SuppressWarnings("unused")
				int actionPointerId = keyAction & MotionEvent.ACTION_POINTER_ID_MASK;
				@SuppressWarnings("unused")
				int actionEvent = keyAction & MotionEvent.ACTION_MASK;
				
					
				switch (keyAction) {
				case MotionEvent.ACTION_MOVE:
					synchronized (touchMove) {
						//touchMove.add(new PointF(event.getX(0), event.getY(0)));
						//touchMove.add(new PointF(event.getX(1), event.getY(1)));
					}
					// world.onActionMove(x, y);
					break;
				case MotionEvent.ACTION_UP:
					synchronized (touchUp) {
						touchUp.add(new PointF(x, y));
					}
					// world.onActionUp(x, y);
					break;
				case MotionEvent.ACTION_DOWN:
					synchronized (touchDown) {
						touchDown.add(new PointF(x, y));
					}
					// world.onActionDown(x, y);
					break;
				}
				
				
				return true;
			}else{//갯수가 하나라면 아래 부분을 실행한다.
			}
		}
		
		//멀티터치가 아니거나, 멀티터치이지만 하나의 터치좌표만 존재하는 경우 이 부분이 실행된다.
		switch (keyAction) {
		case MotionEvent.ACTION_MOVE:
			synchronized (touchMove) {
				touchMove.add(new PointF(x, y));
			}
			// world.onActionMove(x, y);
			break;
		case MotionEvent.ACTION_UP:
			synchronized (touchUp) {
				touchUp.add(new PointF(x, y));
			}
			// world.onActionUp(x, y);
			break;
		case MotionEvent.ACTION_DOWN:
			synchronized (touchDown) {
				touchDown.add(new PointF(x, y));
			}
			// world.onActionDown(x, y);
			break;
		}
		
		return true;
	}
	
	/**
	 * 게임을 종료할떄 호출한다. (명확하게는 바탕화면으로 나간다.)
	 * */
	public void ExitGame()
	{
		System.exit(0);//현재로써는 어쩔수 없다.
	}

	/**
	 * @method 뒤로가기키, 메뉴버튼등 , 키를 눌렀을 경우에 대해서는 여기를 참조하도록한다. 여기로 메세지를 보내고 싶다면 반드시
	 *         MainActivity에서 OnKeyDown, LongPress, OnKeyUp 이벤트에서 여기를 호출해주어야 한다.
	 * @see 기본 기능은 뒤로가기키를 눌렀을떄 바로 종료한다. 만일 여기에서 객체를 참조한다면 synchronized
	 *      (mHolder)를 반드시 해주기바란다. 입력과 그리기,업데이트는 서로 다른 쓰레드에서 실행되기 때문이다.
	 * @see 반드시 동기화를 해주기 바란다. 이 메소드는 아직 완전하지 않다. 제대로된 캐싱해제을 해주는 것이 좋을것 같다.
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/* 사용자가 뒤로버튼을 눌렀을 경우 */
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			// 이 부분은 특정 키를 눌렀을때 실행 된다.
			// 만약 뒤로 버튼을 눌럿을때 할 행동을 지정하고 싶다면.. 기본 기능은 아무것도 없는 것으로 한다. 만일 이 기능을
			// 바꾸고 싶다면 오버라이드하여 사용하도록 한다.
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if (!mFlag) {
					Toast.makeText(mContext, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.",
							Toast.LENGTH_SHORT).show();
					mFlag = true;
					mHandler.sendEmptyMessageDelayed(0, 1000 * 2);
					// 2초 후에 handleMessage에 메시지를 전달한다.
				} else {// 종료한다.
					//mThread.interrupt(); // 쓰레드 종료 (이걸 하니까 정지 에러가 사라졌다.)
					mActivity.onBackPressed();// 에러해결했다.
				}

				// System.exit(0); //이건 사용하지 않는다.
				// 여기에 뒤로 버튼을 눌렀을때 해야할 행동을 지정한다
				// return false;
				// 여기서 리턴값이 중요한데; 리턴값이 true 이냐 false 이냐에 따라 행동이 달라진다.
				// true 일경우 back 버튼의 기본동작인 종료를 실행하게 된다.
				// 하지만 false 일 경우 back 버튼의 기본동작을 하지 않는다.
				// back 버튼을 눌렀을때 종료되지 않게 하고 싶다면 여기서 false 를 리턴하면 된다.
				// back 버튼의 기본동작을 막으면 어플리케이션을 종료할 방법이 없기때문에
				// 따로 종료하는 방법을 마련해야한다.
			}
		}
		// return super.onKeyDown( keyCode, event );//이거 하면 에러난다.
		return false;
	}

	/**
	 * @method 뒤로가기키, 메뉴버튼등 , 키를 눌렀을 경우에 대해서는 여기를 참조하도록한다. 여기로 메세지를 보내고 싶다면 반드시
	 *         MainActivity에서 OnKeyDown, LongPress, OnKeyUp 이벤트에서 여기를 호출해주어야 한다.
	 * @see 기본 기능은 뒤로가기키를 눌렀을떄 아무행동도 하지 않는다. 만일 여기에서 객체를 참조한다면 synchronized
	 *      (mHolder)를 반드시 해주기바란다. 입력과 그리기,업데이트는 서로 다른 쓰레드에서 실행되기 때문이다.
	 * @see 반드시 동기화를 해주기 바란다.
	 * */
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// return super.onKeyLongPress(keyCode, event);//이거 하면 에러난다.
		return false;
	}

	/**
	 * @method 뒤로가기키, 메뉴버튼등 , 키를 눌렀을 경우에 대해서는 여기를 참조하도록한다. 여기로 메세지를 보내고 싶다면 반드시
	 *         MainActivity에서 OnKeyDown, LongPress, OnKeyUp 이벤트에서 여기를 호출해주어야 한다.
	 * @see 기본 기능은 뒤로가기키를 눌렀을떄 아무행동도 하지 않는다. 만일 여기에서 객체를 참조한다면 synchronized
	 *      (mHolder)를 반드시 해주기바란다. 입력과 그리기,업데이트는 서로 다른 쓰레드에서 실행되기 때문이다.
	 * @see 반드시 동기화를 해주기 바란다.
	 * */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// return super.onKeyUp(keyCode, event);//이거 하면 에러난다.
		return false;
	}
} // End of GameView

