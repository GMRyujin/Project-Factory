package Technology.Util;

import android.graphics.Canvas;
import Technology.Game.Sprite.SpriteEx;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

/**
 * @brief 숫자를 출력해주는 객체이다.
 * */
public class NumberPrinter implements IUpdateable, IDrawable {
	IUpdateable updater = null;
	
	SpriteEx[] sp = new SpriteEx[10];
	
	float x,y,width,height;
	
	public NumberPrinter(float x,float y,float width,float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void SetNumberImage(int num,int animTime,String loaderKey){
		sp[num] = new SpriteEx(animTime);
		sp[num].SetBitmapArray(BitmapLoader.getInstance().get(loaderKey));
	}
	
	@Override
	public void Draw(Canvas canvas) {
		// TODO 숫자들을 그린다.
	}
	@Override
	public float Update(float timeDelta) {
		// TODO 숫자들을 업데이트 한다.
		return 0;
	}
}
