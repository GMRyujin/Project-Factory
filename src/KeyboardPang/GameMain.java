/**
 * @brief 이 패키지는 키보드 팡의 메인 오브젝트들의 집합이다.
 * */
package KeyboardPang;

import java.util.Random;

import com.example.ridempang.R;

import KeyboardPang.Motion.BitmapMotion;
import KeyboardPang.Motion.GameParticleObject;
import KeyboardPang.Motion.RandomBitmapObject;
import RidemPang.Object.RythemNote;
import Technology.Base.GameView;
import Technology.Control.AnimatedGameButton;
import Technology.Control.GameButton;
import Technology.Control.GameProgessBar;
import Technology.Control.GameProgessBar.DrawMode;
import Technology.Control.ImageGameButton;
import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import Technology.Util.BitmapLoader;
import Technology.Util.GameSound;
import Technology.Util.PrintDebug;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

/**
 * @brief 게임의 메인이다.
 * */
public class GameMain extends GameView {
	public GameMain(Context context, boolean isFullSceen, boolean isShowTitle) {
		super(context, isFullSceen, isShowTitle);
		
	}
	
	@Override
	protected GameThread CreateCurrentThread() {
		return new KeyboardPangThread();
	}
	
	boolean isLoaded= false;
	/**
	 * @brief 게임에서 실질적으로 돌아가는 쓰레드 루프이다. 이 루프를 변경하면 게임의 루프를 변형하는 것이다.
	 * */
	class KeyboardPangThread extends GameThread
	{
		float gameTime = 0;
		/**
		 * @brief 게임의 메인 루프역할을 하는 쓰레드입니다.
		 * */
		public KeyboardPangThread()
		{
		}
		
		@Override
		protected void onInitialize() {
			/*GameWorld world =  GameWorld.getInstance();
			// 스프라이트 
			// Texture 읽어들이기 
			//palg의 죽는 애니메이션 읽기
			TextureBank.LoadResource(getResources(),GetScreenWidth(),GetScreenHeight());

			 //Sound Load 
			GameSound.getInstance().Load("IceDrag", R.raw.ice_block_drag_01);
			GameSound.getInstance().Load("exp", R.raw.cinematic_hit_explosion);
			//GameSound.getInstance().Load("winterEffect", R.raw.small_waterfall_a_thin_waterfall_in_fast_running_stream_with_long_drop_);
			
			// 이미지를 다 읽었으면 오브젝트 생성 
			new GamePlayer("player", 0, 0);//자동으로 월드에 등록된다. IControllable 만 등록된다. (단순히 모션 생성)
			
			// Test Code 
			new GameParticleObject(5.0f, TextureBank.LoadHouseBitmap(), 400, 400, 0,0, 0.0f, 0.5f, 0.0f);
			
			
			
			final GameProgessBar bar = new GameProgessBar(200,0,TextureBank.LoadProgressBarTitleDemo(),TextureBank.LoadProgressBarDemo()
					, 0.1f,0.1f, 100, 700
					, 700, 800, 40,40 ,100 ,30 );
			bar.SetMode(DrawMode.Persent);
			bar.SetFont(Style.FILL,20, Color.BLACK);
			
			world.Add((IDrawable)bar);
			world.Add((IUpdateable)bar);
			
			
			
		////ImageGameButton btn = new ImageGameButton(TextureBank.LoadStartButtonUp(),TextureBank.LoadStartButtonDown(),50,50,400,200);
			GameButton btn = new AnimatedGameButton(TextureBank.LoadPalgDiedBitmap(),TextureBank.LoadUserPunch(),0.1f,0.1f,50,50,400,200);
			btn.setOnActionControl(new IControllable() {
				@Override
				public void onActionUp(int x, int y) {
					GameSound.getInstance().Play("exp", 0.11f, 0.11f, 0,2);//소리효과 추가
					Log.v("MouseEvent", "Up");
					bar.SetProgress(0);
					ThreadControl_Pause();
					ThreadControl_Restart();
				}
				@Override
				public void onActionMove(int x, int y) {
					Log.v("MouseEvent", "Move");
				}
				@Override
				public void onActionDown(int x, int y) {
					Log.v("MouseEvent", "Down");
				}
			});
			world.Add((IDrawable)btn);
			world.Add((IControllable)btn);
			world.Add((IUpdateable)btn);*/
			
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
		public void Update(float timeDelta) {
			/* Main Code*/ 
			gameTime += timeDelta;//시간을 축적한다.
			BitmapLoader loader = BitmapLoader.getInstance();
			if(gameTime > 5.0f){
				//new KBPalg(TextureBank.LoadPalgBitmap(), "monster",(float) Math.random()*500, (float)Math.random()*500);
				//new BitmapMotion(TextureBank.userPunch, 10, 10);//펀치 추가
				//new RandomBitmapObject(TextureBank.LoadWallBreak(),10, 10,3);//벽이미지 추가
				//GameSound.getInstance().Play("IceDrag", 0.11f, 0.11f, 0,2);//소리효과 추가
				//GameSound.getInstance().Play("exp", 0.11f, 0.11f, 0,2);//소리효과 추가
				
				//new RandomBitmapObject(TextureBank.LoadWallBreak(),10, 50,3);//벽이미지 추가
				//new BitmapMotion(TextureBank.palgDiedAnim, 10, 20);//펀치가 렉이었다.
				//new BitmapMotion(TextureBank.LoadUserPunch(), 20, 50);//펀치가 렉이었다.
				
				Random rand = new Random();
				if(gameTime % 5 < 0.09){
					new RythemNote(rand.nextInt(getWidth()-50), 0, loader.get("BlueNote"),1);
					//new KBPalg(loader.get("BlueNote")[0], "Plag",rand.nextInt(getWidth()-50),20);
				}
				
				gameTime = 0;
			}
			
			GameWorld.getInstance().Update(timeDelta);
		}
		@Override
		public void Draw(Canvas canvas) {
			/* Main Code */
			Clear(canvas);
			//현재 월드의 객체를 출력한다.
			PrintDebug.PrintWorldObjectNumberInfo(canvas, GameWorld.getInstance(), Color.BLACK, 15, 0, 15);
			GameWorld.getInstance().Draw(canvas);
			/* Test Code */
		}
		
	}
}
