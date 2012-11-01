package RidemPang.Base;

import java.util.Random;

import com.example.ridempang.*;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import KeyboardPang.Motion.BitmapMotion;
import KeyboardPang.Motion.DrawBitmap;
import RidemPang.Object.RythemBaseObject;
import Technology.Base.GameView;
import Technology.Base.GameView.GameThread;
import Technology.Control.AnimatedGameButton;
import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import Technology.Util.BitmapLoader;
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

	/* 리듬�의 �제 구현부� 변�는 �래�언�다. �레�안�언�면 초기�� �기�문�다 . */
	/* 리듬게임 */
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
						//new BitmapMotion(BitmapLoader.getInstance().get("EffectRedPang"), note.GetX(), note.GetY());
						//GameWorld.getInstance().Remove((IDrawable) note);
						//GameWorld.getInstance().Remove((IControllable) note);
						//GameWorld.getInstance().Remove((IUpdateable) note);
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
						GameWorld.getInstance().Remove((IDrawable) note);
						GameWorld.getInstance().Remove((IControllable) note);
						GameWorld.getInstance().Remove((IUpdateable) note);
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
		//	note.addWorld();
			GameWorld.getInstance().Add((IDrawable) note);
			GameWorld.getInstance().Add((IControllable) note);
			GameWorld.getInstance().Add((IUpdateable) note);
		}

		@Override
		protected void onInitialize() {
			InitClearColor(Color.WHITE);
			GameWorld world = GameWorld.getInstance();
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
		}

		@Override
		protected void Update(float timeDelta) {
			GameWorld world = GameWorld.getInstance();
			BitmapLoader loader = BitmapLoader.getInstance();
			currentTime += timeDelta;// �재 게임�간. (게임진행�에 �용�다.)

			if (isStart) {// 게임�작�면 모든 버튼�거�다. (�작 버튼�렸�때)
				// 게임�로 진행�다.
				if (isStarted == false) {// �번�진행�다.
					isStarted = true;
<<<<<<< HEAD
					// 로비�서
=======
					// 로비에서
>>>>>>> origin/ExRythemGame
					world.Remove((IDrawable) background);
					world.Remove((IUpdateable) background);

					world.Remove((IDrawable) gameLogo);
					world.Remove((IUpdateable) gameLogo);

					world.Remove((IDrawable) gameStartButton);
					world.Remove((IControllable) gameStartButton);
					world.Remove((IUpdateable) gameStartButton);

					world.Remove((IDrawable) gameExitButton);
					world.Remove((IControllable) gameExitButton);
					world.Remove((IUpdateable) gameExitButton);

					world.Add((IDrawable) touchLayer);
					world.Add((IUpdateable) touchLayer);

					world.Add((IDrawable) redEffect);
					world.Add((IUpdateable) redEffect);

					rythemRedNote.addWorld();
					
					backButton = new AnimatedGameButton(
							loader.get("BackButton"),
							loader.get("BackButton"), 0.02f, 0.02f, 
							getWidth()-100, 0,getWidth(),50);
					backButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
<<<<<<< HEAD
							// TODO 게임�작�다.
=======
							// TODO 게임을 시작한다.
>>>>>>> origin/ExRythemGame
							if(backButton.IsMe(x, y)){
								TechVibrator.getInstance().vibrate(500);
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


					currentTime = 0;
<<<<<<< HEAD
				}// �번맸출�는 곅데�트�서)
					// 게임�작�면 걔속 �기부분이 �출�다.
=======
				}// 한번만 호출하는 곳(업데이트에서)
					// 게임이 시작되면 걔속 여기부분이 호출된다.
>>>>>>> origin/ExRythemGame
				Random rand = new Random();
				if(currentTime % 5 < 0.09){
					CreateNote(rand.nextInt(getWidth()-50), 0, "BlueNote",(int)(currentTime*0.1) + 1);
				}
				

<<<<<<< HEAD
			} else {// 게임멈췄�때 �� 로고�태�때 (게임�서 메인�로 갔을
				if (isMainStarted == false) {
					isMainStarted = true;
					// 메인�작�었�때 �번맸출�는 �
=======
			} else {// 게임이 멈췄을때 혹은 로고상태일때 (게임에서 메인으로 갔을때)
				if (isMainStarted == false) {
					isMainStarted = true;
					// 메인이 시작되었을때 한번만 호출되는 곳
>>>>>>> origin/ExRythemGame
					rythemRedNote = new RythemBaseObject("note", 50, 50,
							loader.get("RedNote"), 0.1f, 200, 100);
					rythemRedNote.setOnActionController(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							if (rythemRedNote.IsMe(x, y)) {
								Log.v("RTest", "OnClick");
								TechVibrator.getInstance().vibrate(500);
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
									rythemRedNote.GetY() + 10);
							return 0;
						}
					});
					
					rythemBlueNote = new RythemBaseObject("note", 50, 50,
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
									rythemBlueNote.GetY() + 10);
							return 0;
						}
					});
					rythemBlueNote.addWorld();
					
<<<<<<< HEAD
=======
					
>>>>>>> origin/ExRythemGame
					rythemGreenNote = new RythemBaseObject("note", 50, 50,
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
					rythemGreenNote.addWorld();
					
					
					
					//
					redEffect = new AnimatedGameButton(loader.get("RedEffect"),
							loader.get("RedEffect"), 0.02f, 0.02f, 0,
							getHeight() - 200, 100, getHeight());

					greenEffect = new AnimatedGameButton(
							loader.get("EffectGreen"),
							loader.get("EffectGreen"), 0.02f, 0.02f, 0,
							getHeight() - 200, 100, getHeight());

					//�치�이�성
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

					//게임�작 버튼
					gameStartButton = new AnimatedGameButton(
							loader.get("StartButton"),
							loader.get("StartButton"), 0.02f, 0.02f, 150, 1050,
							600, 1200);
					gameStartButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							// TODO 게임�작�다.
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

					
<<<<<<< HEAD
					/* �로 가�버튼 */
=======
					/* 뒤로 가기 버튼 */
>>>>>>> origin/ExRythemGame
					/*gameExitButton = new AnimatedGameButton(
							loader.get("ExitButton"), loader.get("ExitButton"),
							0.02f, 0.02f, 150, 650, 600, 850);
					gameExitButton.setOnActionControl(new IControllable() {
						@Override
						public void onActionUp(int x, int y) {
							// TODO 게임종료�다.
							ExitGame();
						}

						@Override
						public void onActionMove(int x, int y) {

						}

						@Override
						public void onActionDown(int x, int y) {
						}
					});*/
					//world.Add((IDrawable) gameExitButton);
					//world.Add((IControllable) gameExitButton);
					//world.Add((IUpdateable) gameExitButton);
				}
<<<<<<< HEAD
				// 메인 �태�때 계속 �행�는 �
				
			}//
			//무조걤행�는 �
=======
				// 메인 상태일때 계속 실행되는 곳
				
			}//끝
			//무조건 실행되는 곳
>>>>>>> origin/ExRythemGame
			
			GameWorld.getInstance().Update(timeDelta);
		}

		@Override
		protected void Draw(Canvas canvas) {
			Clear(canvas);
			GameWorld.getInstance().Draw(canvas);
		}
	}

}
