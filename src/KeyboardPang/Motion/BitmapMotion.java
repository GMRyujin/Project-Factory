package KeyboardPang.Motion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import KeyboardPang.TextureBank;
import Technology.Game.GameWorld;
import Technology.Game.Sprite.Sprite;
import Technology.Game.Sprite.Sprite.SPRITE_PIVOT;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

/**
 * @brief 자동으로 월드에 추가되고 애니메이션이 모두 끝나면 자기자신을 삭제하는 오브젝트이다. 이 클래스를 상속받으면 월드에 두번 추가가 될수 있기때문에 상속받지않는다.
이 클래스는 완벽히 KBPalgDied 클래스와 하는 동작이 동일하다.
 * 
 * */
public final class BitmapMotion extends Sprite {
	GameWorld world = null;
	/**
	 * @method 여기에서 world에 자동 추가된다.
	 * */
	public BitmapMotion(Bitmap[] bitmap,float x,float y) {
		super(0.02f);
		world = GameWorld.getInstance();
		SetBitmapArray(bitmap);
		SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_LEFTTOP);
		SetTranslate(x, y);
		
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
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
			world.Remove((IUpdateable)this);
			world.Remove((IDrawable)this);
			return 0;
		}
	}
}
