package Technology.Control;

import Technology.Game.Sprite.SpriteEx;
import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * @brief 애니메이션된 버튼이다. 이 버튼은 터치가 된 상태와 터치가 되지 않은 상태의 애니메이션을 한다. GameButton을 상속받았으므로 원하는 이벤트 처리를 해줄수 있다.
 * */
public class AnimatedGameButton extends GameButton {
	SpriteEx upSprite;
	SpriteEx downSprite;
	SpriteEx nowSprite;
	
	public AnimatedGameButton(Bitmap[] animBitmapUp,Bitmap[] animBitmapDown,float animTimeUp,float animTimeDown,int left, int top, int right, int bottom) {
		super(left, top, right, bottom);
		
		/* Set Position */
		if(animBitmapUp != null){
			upSprite = new SpriteEx(animTimeUp);
			upSprite.SetBitmapArray(animBitmapUp);
			upSprite.SetTranslate(0, 0);
		}
		if(animBitmapDown != null){
			downSprite = new SpriteEx(animTimeDown);
			downSprite.SetBitmapArray(animBitmapDown);
			downSprite.SetTranslate(0, 0);
		}		
		nowSprite = upSprite;//기본설정.
	}
	
	@Override
	public void onActionUp(float x, float y) {
		super.onActionUp(x, y);
		if(upSprite == null || downSprite == null){
			
		}else{
			if(IsMeUp(x, y)){
				nowSprite = upSprite;
			}
		}
		nowSprite = upSprite;
	}

	@Override
	public void onActionDown(float x, float y) {
		super.onActionDown(x, y);
		if(upSprite == null || downSprite == null){
			
		}else{
			if(IsMeDown(x, y)){
				nowSprite = downSprite;
			}
		}
	}
	
	
	@Override
	public void onActionMove(float x, float y) {
		super.onActionMove(x, y);
		if(upSprite == null || downSprite == null){
			
		}else{
			if(IsMeDown(x, y)){
				nowSprite = downSprite;
			}
		}
	}

	@Override
	public float Update(float timeDelta) {
		nowSprite.Update(timeDelta);//반드시 스프라이트는 Update를 해준다.
		return super.Update(timeDelta);
	}

	@Override
	public void Draw(Canvas canvas) {
		if(nowSprite == null){
			super.Draw(canvas);
		}else{
			//스프라이트 그려주기.
			nowSprite.Draw(canvas,GetRect(),null);
		}
	}
}
