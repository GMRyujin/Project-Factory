package KeyboardPang;

import KeyboardPang.Motion.BitmapMotion;
import KeyboardPang.Motion.EffectBitmap;
import KeyboardPang.Motion.RandomBitmapObject;
import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Util.GameSound;


/**
 * @brief 이 객체는 생성되면 자동으로 월드에 등록한다. 이 클래스를 상속받으면 월드에 두번 추가가 될수 있기때문에 상속받지않는다.
 * */
public final class GamePlayer implements IControllable {
	public GamePlayer(String id, float x, float y) {
		GameWorld world = GameWorld.getInstance();
		world.Add((IControllable)this);
	}
	
	@Override
	public void onActionDown(float x, float y) {
		// TODO Auto-generated method stub
		//super.onActionDown(x, y);
		//new BitmapMotion(TextureBank.userPunch, x, y);//펀치 추가
		//new RandomBitmapObject(TextureBank.LoadWallBreak(),x, y,3);//벽이미지 추가
		//new BitmapMotion(TextureBank.palgDiedAnim, x, y);//펀치가 렉이었다.
		//new BitmapMotion(TextureBank.palgDiedAnim, x, y);//펀치가 렉이었다.
		
		GameSound.getInstance().Play("exp", 0.11f, 0.11f, 0,2);//소리효과 추가
		//new EffectBitmap(TextureBank.LoadBlackBitmap());//이펙트 효과 주기
		//new EffectBitmap(TextureBank.LoadGrayBitmap());//이펙트 효과 주기
	}

	@Override
	public void onActionMove(float x, float y) {
	}
	

	@Override
	public void onActionUp(float x, float y) {
	}
}
