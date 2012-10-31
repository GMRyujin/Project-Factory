package Technology.Frame;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import Technology.Interface.IUpdateable;

/*
 * �� Ŭ������ ������ AnimationFrame�� �������� �׸��� ������ �Ѵ�.
 * */
public class AnimationController implements IUpdateable{
	AnimationFrame frame;
	
	public AnimationController(){
		frame = null;
	}
	
	public void SetFrame(AnimationFrame frame){
		this.frame = frame;
	}
	
	public void Draw(Canvas canvas,int x,int y){
		Rect src = frame.GetCurrentBitmapRect();
		Point size = frame.GetSize();
		Rect dst = new Rect(x,y,size.x+x,size.y+y);
		
		canvas.drawBitmap(frame.GetCurrentBitmap(), src, dst,null);
	}
	
	public float Update(float timeDelta) {
		return frame.Update(timeDelta);
	}
	
}

