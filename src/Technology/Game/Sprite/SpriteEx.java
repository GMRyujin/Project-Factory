package Technology.Game.Sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * @brief Sprite 보다 기능이 더많은 확장 버전이다.
 * */
public class SpriteEx extends Sprite {

	public SpriteEx(float animTime) {
		super(animTime);
	}
	
	/**
	 * @brief 현재 자신이 그리는 비트맵을 가져온다. 
	 * 반드시 Draw 메소드에서만 쓰기바란다. 
	 * @return 현재 그리려고 하는 비트맵
	 * */
	public Bitmap GetCurrentBitmap()
	{
		return tempBitmap;
	}
	/**
	 * @brief 현재 자신이 그리는 비트맵의 너비를 가져온다.
	 * 반드시 Draw 메소드에서만 쓰기바란다. 
	 * @return 현재 그리려고 하는 비트맵 너비
	 * */
	public int GetCurrentBitmapWidth()
	{
		return tempBitmap.getWidth();
	}
	/**
	 * @brief 현재 자신이 그리는 비트맵을 높이를 가져온다. 
	 * 반드시 Draw 메소드에서만 쓰기바란다. 
	 * @return 현재 그리려고 하는 비트맵 높이
	 * */
	public int GetCurrentBitmapHeight()
	{
		return tempBitmap.getHeight();
	}
	
	public void Draw(Canvas canvas,Rect dst,Paint paint){
		assert(currentBitmap == null);
		
		//Log.v("TempBitmap",""+ tempBitmap.getWidth());
		//Log.v("TempBitmap",""+ tempBitmap.getHeight());
		
		if(tempBitmap != null){
			//Log.v("TempBitmap",""+ tempBitmap.getWidth());
			//Log.v("TempBitmap",""+ tempBitmap.getHeight());
			canvas.drawBitmap(tempBitmap,new Rect(0,0,
					tempBitmap.getWidth(),tempBitmap.getHeight()),
					dst,
					paint);
		}
	}
	
	public void Draw(Canvas canvas,RectF dst,Paint paint){
		assert(currentBitmap == null);
		
		Log.v("TempBitmap",""+ tempBitmap.getWidth());
		Log.v("TempBitmap",""+ tempBitmap.getHeight());
		
		if(tempBitmap != null){
			Log.v("TempBitmap",""+ tempBitmap.getWidth());
			Log.v("TempBitmap",""+ tempBitmap.getHeight());
			canvas.drawBitmap(tempBitmap,new Rect(0,0,
					tempBitmap.getWidth(),tempBitmap.getHeight()),
					dst,
					paint);
		}
	}
	
	public void Draw(Canvas canvas,Rect src,Rect dst,Paint paint){
		assert(currentBitmap == null);
		
		if(tempBitmap != null){
			canvas.drawBitmap(tempBitmap,
					src,
					dst,
					paint);
		}
	}
	
	public void Draw(Canvas canvas,Rect src,RectF dst,Paint paint){
		assert(currentBitmap == null);
		
		if(tempBitmap != null){
			canvas.drawBitmap(tempBitmap,
					src,
					dst,
					paint);
		}
	}
}
