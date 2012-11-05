package Technology.Control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @brief 이미지를 추가할수 있는 버튼이다. 이 컨트롤에 사용자 정의 IControl 이벤트를 처리하려면 setOnActionControl() 메소드를 사용하여 처리하기 바란다.
 * 한가지 더 참고할 사항은 버튼이 사용자 이벤트에 반응하려면 IControllable을 인터페이스를 등록해주어야 한다. 
 * 이 클래스는 더 이상 상속받을 것이 없다고 판단, 상속받지 못한다.
 * @version Tested Safe 1.0
 * */
public final class ImageGameButton extends GameButton {
	Bitmap nowBitmap = null;;
	Bitmap downBitmap = null,upBitmap = null;

	public ImageGameButton(Bitmap upBitmap,Bitmap downBitmap,int left, int top, int right, int bottom) {
		super(left, top, right, bottom);
		this.downBitmap = downBitmap;
		this.upBitmap = upBitmap;
		this.nowBitmap = upBitmap;
	}
	
	
	@Override
	public void onActionUp(float x, float y) {
		if(nowBitmap == null){
			super.onActionUp(x, y);
		}else{
			if(IsMeUp(x, y)){
				//여기가 실질적으로 버튼 이벤트가 발생한 곳.
				nowBitmap = upBitmap;
				if(GetUserController() != null) GetUserController().onActionUp(x, y);
			}
		}
		nowBitmap = upBitmap;
	}

	@Override
	public void onActionDown(float x, float y) {
		if(nowBitmap == null){
			super.onActionDown(x, y);
		}else{
			if(IsMeDown(x, y)){
				//실제 눌리진 곳.
				nowBitmap = downBitmap;
				if(GetUserController() != null) GetUserController().onActionDown(x, y);
			}
		}
	}
	
	/**
	 * @brief 마우스 버튼이 눌려지고 있을때 그리고 그 위에서 움직여지고 있을때
	 * */
	@Override
	public void onActionMove(float x, float y) {
		if(nowBitmap == null){
			super.onActionMove(x, y);
		}else{
			if(IsMeDown(x, y)){
				if(GetUserController() != null) GetUserController().onActionMove(x, y);
			}
		}
	}
	
	public int GetBitmapWidth()
	{
		return nowBitmap.getWidth();
	}
	public int GetBitmapHeight()
	{
		return nowBitmap.getHeight();
	}

	@Override
	public void Draw(Canvas canvas) {
		if(nowBitmap == null){
			super.Draw(canvas);
		}else{
			//현재 이미지를 그린다.
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			canvas.drawBitmap(nowBitmap,new Rect(0,0,GetBitmapWidth(),GetBitmapHeight()),new Rect(GetLeft(),GetTop(),GetRight(),GetBottom()),paint); 
		}
	}
	
}
