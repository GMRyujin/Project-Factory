package RidemPang.Base;

import java.util.Random;
import java.util.Timer;

import com.example.ridempang.*;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import KeyboardPang.KBPalg;
import KeyboardPang.Motion.BitmapMotion;
import KeyboardPang.Motion.DrawBitmap;
import RidemPang.Object.RythemBaseObject;
import RidemPang.Object.RythemNote;
import RidemPang.Object.TechTimer;
import Technology.Base.GameView;
import Technology.Base.GameView.GameThread;
import Technology.Control.AnimatedGameButton;
import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import Technology.Util.BitmapLoader;
import Technology.Util.NumberPrinter;
import Technology.Util.TechVibrator;

public class RidemPangView extends GameView {
	public RidemPangView(Context context, boolean isFullSceen,
			boolean isShowTitle) {
		super(context, isFullSceen, isShowTitle);
	}

	@Override
	protected GameThread CreateCurrentThread() {
		return new RidemPangGameThread();
	}

	/* 리듬팡의 실제 구현부분, 변수는 아래에 선언한다. 쓰레드안에 선언하면 초기화가 되기때문이다 . */
	boolean isStart = false;
	boolean isStarted = false;
	boolean isMainStarted = false;

	AnimatedGameButton background;
	AnimatedGameButton gameLogo;
	AnimatedGameButton gameStartButton;
	AnimatedGameButton gameExitButton;
	AnimatedGameButton touchLayer;
	

	AnimatedGameButton greenEffect;
	AnimatedGameButton redEffect;

	private RythemBaseObject rythemRedNote;
	private RythemBaseObject rythemBlueNote;
	private RythemBaseObject rythemGreenNote;
	
	private AnimatedGameButton backButton;

	float currentTime = 0;

	class RidemPangGameThread extends GameView.GameThread {
		

		public void CreateNote(float x, float y, String noteName,final int dy) {
			final RythemBaseObject note = new RythemBaseObject("note", x, y,
					BitmapLoader.getInstance().get(noteName), 0.4f, 200, 100);
			note.setOnActionController(new IControllable() {
				@Override
				public void onActionUp(int x, int y) {
					if (note.IsMe(x, y)) {
						Log.v("RTest", "OnClick");
					}
				}

				@Override
				public void onActionMove(int x, int y) {
					if (note.IsMe(x, y)) {
						Log.v("RTest", "OnMove");
					}
				}

				@Override
				public void onActionDown(int x, int y) {
					if (note.IsMe(x, y)) {
						Log.v("RTest", "OnDown");
						note.removeWorld();
					}
				}
			});
			note.setOnUpdater(new IUpdateable() {
				@Override
				public float Update(float timeDelta) {
					note.SetPos(note.GetX(),note.GetY() + dy);
					return 0;
				}
			});
			note.addWorld();
		}
		

		@Override
		protected void onInitialize() {
			InitClearColor(Color.WHITE);
			GameWorld.getInstance();
			BitmapLoader loader = BitmapLoader.getInstance();

			loader.put("StartButton", R.drawable.start_button0000);
			loader.put("StartButton", R.drawable.start_button0001);

			loader.put("ExitButton", R.drawable.exit_button0000);
			loader.put("ExitButton", R.drawable.exit_button0001);
			loader.put("ExitButton", R.drawable.exit_button0002);
			loader.put("ExitButton", R.drawable.exit_button0003);

			loader.put("Logo", R.drawable.rythem_pang0000);
			loader.put("Logo", R.drawable.rythem_pang0001);
			loader.put("Logo", R.drawable.rythem_pang0002);
			loader.put("Logo", R.drawable.rythem_pang0003);
			loader.put("Logo", R.drawable.rythem_pang0004);
			loader.put("Logo", R.drawable.rythem_pang0005);
			/*
			 * loader.put("Logo",R.drawable.we_are_logo0001);
			 * loader.put("Logo",R.drawable.we_are_logo0002);
			 * loader.put("Logo",R.drawable.we_are_logo0003);
			 */

			loader.put("TouchLayer", R.drawable.touch_layer0000);
			loader.put("Background", R.drawable.background_small);
			loader.put("EffectGreen", R.drawable.bloom_effect_green0000);
			loader.put("RedEffect", R.drawable.bloom_effect_red0000);

			loader.put("BioletNote", R.drawable.biolet_note0000);
			loader.put("BioletNote", R.drawable.biolet_note0001);
			loader.put("BioletNote", R.drawable.biolet_note0002);
			loader.put("BioletNote", R.drawable.biolet_note0003);

			loader.put("BlueNote", R.drawable.blue_note0000);
			loader.put("BlueNote", R.drawable.blue_note0001);
			loader.put("BlueNote", R.drawable.blue_note0002);
			loader.put("BlueNote", R.drawable.blue_note0003);

			loader.put("RedNote", R.drawable.read_note0001);
			loader.put("RedNote", R.drawable.read_note0002);
			loader.put("RedNote", R.drawable.read_note0003);
			loader.put("RedNote", R.drawable.read_note0003);

			loader.put("EffectRedPang", R.drawable.sprite_red_effect_pang0000);
			
			loader.put("BackButton", R.drawable.back_button0000);
			
			loader.put("GameNumber0",R.drawable.number0);
			loader.put("GameNumber1",R.drawable.number1);
			loader.put("GameNumber2",R.drawable.number2);
			loader.put("GameNumber3",R.drawable.number3);
			loader.put("GameNumber4",R.drawable.number4);
			loader.put("GameNumber5",R.drawable.number5);
			loader.put("GameNumber6",R.drawable.number6);
			loader.put("GameNumber7",R.drawable.number7);
			loader.put("GameNumber8",R.drawable.number8);
			loader.put("GameNumber9",R.drawable.number9);
			
			
			NumberPrinter printer = NumberPrinter.getInstance("Score",100,10,30,50);
			printer.SetNumberImage(0, 1, "GameNumber0");
			printer.SetNumberImage(1, 1, "GameNumber1");
			printer.SetNumberImage(2, 1, "GameNumber2");
			printer.SetNumberImage(3, 1, "GameNumber3");
			printer.SetNumberImage(4, 1, "GameNumber4");
			printer.SetNumberImage(5, 1, "GameNumber5");
			printer.SetNumberImage(6, 1, "GameNumber6");
			printer.SetNumberImage(7, 1, "GameNumber7");
			printer.SetNumberImage(8, 1, "GameNumber8");
			printer.SetNumberImage(9, 1, "GameNumber9");
			//printer.SetPrintNumber(1234567890);
			//printer.AddWorld();
		}

		@Override
		protected void Update(float timeDelta) {
			GameWorld world = GameWorld.getInstance();
			BitmapLoader loader = BitmapLoader.getInstance();
			currentTime += timeDelta;// 현재 게임의 시간. (게임이 진행된 후에 사용한다.)

			if (isStart) {// 게임이 시작되면 모든 버튼을 제거한다. (시작 버튼이 눌렸을때)
				// 게임으로 진행한다.
				if (isStarted == false) {// 한번만 진행한다.
					isStarted = true;
					// 로비에서
					world.Clear();
					
					// 메인이 시작되었을때 한번만 호출되는 곳
					rythemRedNote = new RythemBaseObject("RedNote", 50, 50,
							loader.get("RedNote"), 0.1f, 200, 100);
					rythemRedNote.setOnActionController(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							if (rythemRedNote.IsMe(x, y)) {
								Log.v("RTest", "OnClick");
								new BitmapMotion(BitmapLoader.getInstance()
										.get("EffectRedPang"), rythemRedNote
										.GetX(), rythemRedNote.GetY());
								rythemRedNote.removeWorld();
							}
						}

						@Override
						public void onActionMove(int x, int y) {
							if (rythemRedNote.IsMe(x, y)) {
								Log.v("RTest", "OnMove");
							}
						}

						@Override
						public void onActionDown(int x, int y) {
							if (rythemRedNote.IsMe(x, y)) {
								Log.v("RTest", "OnDown");
							}
						}
					});
					rythemRedNote.setOnUpdater(new IUpdateable() {
						@Override
						public float Update(float timeDelta) {
							rythemRedNote.SetPos(rythemRedNote.GetX(),
									rythemRedNote.GetY() + 1);
							return 0;
						}
					});
					
					rythemBlueNote = new RythemBaseObject("BlueNote", 150, 50,
							loader.get("BlueNote"), 0.1f, 200, 100);
					rythemBlueNote.setOnActionController(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							if (rythemBlueNote.IsMe(x, y)) {
								Log.v("RTest", "OnClick");
								new BitmapMotion(BitmapLoader.getInstance()
										.get("EffectRedPang"), rythemBlueNote
										.GetX(), rythemBlueNote.GetY());
								rythemBlueNote.removeWorld();
							}
						}

						@Override
						public void onActionMove(int x, int y) {
							if (rythemBlueNote.IsMe(x, y)) {
								Log.v("RTest", "OnMove");
							}
						}

						@Override
						public void onActionDown(int x, int y) {
							if (rythemBlueNote.IsMe(x, y)) {
								Log.v("RTest", "OnDown");
							}
						}
					});
					rythemBlueNote.setOnUpdater(new IUpdateable() {
						@Override
						public float Update(float timeDelta) {
							rythemBlueNote.SetPos(rythemBlueNote.GetX(),
									rythemBlueNote.GetY() + 1);
							return 0;
						}
					});
					
					
					
					rythemGreenNote = new RythemBaseObject("VioletNote", 100, 50,
							loader.get("BioletNote"), 0.1f, 200, 100);
					rythemGreenNote.setOnActionController(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							if (rythemGreenNote.IsMe(x, y)) {
								Log.v("RTest", "OnClick");
								new BitmapMotion(BitmapLoader.getInstance()
										.get("EffectRedPang"), rythemGreenNote
										.GetX(), rythemGreenNote.GetY());
								rythemGreenNote.removeWorld();
							}
						}

						@Override
						public void onActionMove(int x, int y) {
							if (rythemGreenNote.IsMe(x, y)) {
								Log.v("RTest", "OnMove");
							}
						}

						@Override
						public void onActionDown(int x, int y) {
							if (rythemGreenNote.IsMe(x, y)) {
								Log.v("RTest", "OnDown");
							}
						}
					});
					rythemGreenNote.setOnUpdater(new IUpdateable() {
						@Override
						public float Update(float timeDelta) {
							rythemGreenNote.SetPos(rythemGreenNote.GetX(),
									rythemGreenNote.GetY() + 1);
							return 0;
						}
					});

					rythemRedNote.addWorld();
					rythemBlueNote.addWorld();
					rythemGreenNote.addWorld();
					
					backButton = new AnimatedGameButton(
							loader.get("BackButton"),
							loader.get("BackButton"), 0.02f, 0.02f, 
							getWidth()-100, 0,getWidth(),50);
					backButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							// TODO 게임을 시작한다.
							if(backButton.IsMe(x, y)){
								TechVibrator.getInstance().vibrate(500);
								
								//게임의 설정을 초기화 한다.
								GameWorld.getInstance().Clear();
								isMainStarted = false;
								isStart = false;
								isStarted = false;
								
							}
						}
						@Override
						public void onActionMove(int x, int y) {

						}

						@Override
						public void onActionDown(int x, int y) {
						}
					});
					world.Add((IDrawable) backButton);
					world.Add((IControllable) backButton);
					world.Add((IUpdateable) backButton);
					
					TechTimer timer = new TechTimer(1,0);
					timer.setOnUpdater(new IUpdateable() {
						@Override
						public float Update(float timeDelta) {
							Random rand = new Random();
							//CreateNote(rand.nextInt(getWidth()-50),0,"BlueNote",currentTime > 10 ? 10 : (int)currentTime);
							new RythemNote(50, 0, BitmapLoader.getInstance().get("BlueNote"),5);
							//new KBPalg(BitmapLoader.getInstance().get("BlueNote")[0], "Plag",rand.nextInt(getWidth()-50),20);
							Log.v("Test","Timer Test");
							return 0;
						}
					});
					timer.StartTimer();

					NumberPrinter.getInstance("Score").AddWorld();

					currentTime = 0;
				}// 한번만 호출하는 곳(업데이트에서)
					// 게임이 시작되면 걔속 여기부분이 호출된다.
				
				//if(currentTime % 5 < 0.09){
					//new RythemNote(rand.nextInt(getWidth()-50), 0, loader.get("BlueNote"),5);
					//new KBPalg(loader.get("BlueNote")[0], "Plag",rand.nextInt(getWidth()-50),20);
					//CreateNote(rand.nextInt(getWidth()-50),0,"BlueNote",currentTime > 10 ? 10 : (int)currentTime);
				//}
				

			} else {// 게임이 멈췄을때 혹은 로고상태일때 (게임에서 메인으로 갔을때)
				if (isMainStarted == false) {
					isMainStarted = true;
					
					redEffect = new AnimatedGameButton(loader.get("RedEffect"),
							loader.get("RedEffect"), 0.02f, 0.02f, 0,
							getHeight() - 200, 100, getHeight());

					greenEffect = new AnimatedGameButton(
							loader.get("EffectGreen"),
							loader.get("EffectGreen"), 0.02f, 0.02f, 0,
							getHeight() - 200, 100, getHeight());

					//터치레이어 생성
					touchLayer = new AnimatedGameButton(
							loader.get("TouchLayer"), loader.get("TouchLayer"),
							0.02f, 0.02f, 0, getHeight() - 100, getWidth(),
							getHeight());

					background = new AnimatedGameButton(
							loader.get("Background"), loader.get("Background"),
							5, 5, 0, 0, getWidth(), getHeight());

					world.Add((IDrawable) background);
					world.Add((IUpdateable) background);

					gameLogo = new AnimatedGameButton(loader.get("Logo"),
							loader.get("Logo"), 0.1f, 0.2f, 30, 170, 700, 450);
					world.Add((IDrawable) gameLogo);
					world.Add((IUpdateable) gameLogo);

					//게임의 시작 버튼
					gameStartButton = new AnimatedGameButton(
							loader.get("StartButton"),
							loader.get("StartButton"), 0.02f, 0.02f, 150, 400,
							600, 600);
					gameStartButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							// TODO 게임을 시작한다.
							TechVibrator.getInstance().vibrate(500);
							isStart = true;
						}

						@Override
						public void onActionMove(int x, int y) {

						}

						@Override
						public void onActionDown(int x, int y) {
						}
					});
					world.Add((IDrawable) gameStartButton);
					world.Add((IControllable) gameStartButton);
					world.Add((IUpdateable) gameStartButton);

					
					// 뒤로 가기 버튼 
					gameExitButton = new AnimatedGameButton(
							loader.get("ExitButton"), loader.get("ExitButton"),
							0.02f, 0.02f, 150, 650, 600, 850);
					gameExitButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							// TODO 게임을 종료한다.
							ExitGame();
						}

						@Override
						public void onActionMove(int x, int y) {

						}

						@Override
						public void onActionDown(int x, int y) {
						}
					});
					world.Add((IDrawable) gameExitButton);
					world.Add((IControllable) gameExitButton);
					world.Add((IUpdateable) gameExitButton);
					
					//NumberPrinter.getInstance("Score").AddWorld();
				}
				// 메인 상태일때 계속 실행되는 곳
			}//끝
			//무조건 실행되는 곳
			//NumberPrinter.getInstance("Score").Update(timeDelta);
			//TODO TEST
			GameWorld.getInstance().Update(timeDelta);
		}

		@Override
		protected void Draw(Canvas canvas) {
			Clear(canvas);
			//TODO TEST
			GameWorld.getInstance().Draw(canvas);
			//NumberPrinter.getInstance("Score").Draw(canvas);
		}
	}

}
