package KeyboardPang;

import android.graphics.Canvas;
import Technology.Game.GameWorld;
import Technology.Game.Sprite.Sprite;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

/**
 * @class 이 클래스는 단순히 Palg의 죽는 모션이다. 이 클래스는 생성 순간 월드에 등록되며, 애니메이션을 다 돌았다면 자동 삭제된다. 이 객체를 상속받으면 추가가 두번되기 때문에 상속하지 않는다.
 * */
public final class KBPalgDied extends Sprite {
	GameWorld world = null;
	/**
	 * @method 여기에서 world에 자동 추가된다.
	 * */
	public KBPalgDied(float x,float y) {
		super(0.02f);
		world = GameWorld.getInstance();
		//SetBitmapArray(TextureBank.LoadPalgDiedBitmap());
		SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_LEFTTOP);
		SetTranslate(x, y);
		world.Add((IDrawable)this);
		world.Add((IUpdateable)this);
	}
	
	/**
	 * @method 루프를 다돌면 그리지않는다.
	 * */
	@Override
	public void Draw(Canvas canvas) {
		if(IsEnd() == false){
			super.Draw(canvas);
		}
	}
	/**
	 * @method 여기서 루프를 다돌면 월드에서 삭제한다.
	 * */
	@Override
	public float Update(float timeDelta) {
		if(IsEnd() == false){
			return super.Update(timeDelta);
		}else{
			world.Remove((IDrawable)this);
			world.Remove((IUpdateable)this);
			return 0;
		}
	}
}
