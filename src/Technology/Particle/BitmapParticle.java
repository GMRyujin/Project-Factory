package Technology.Particle;

import javax.microedition.khronos.opengles.GL;

import Technology.Interface.IParticle;
import android.graphics.*;

/**
 * @class 비트맵을 그릴수 있는 파티클이다.
 * */
public class BitmapParticle extends BaseParticle {
	Bitmap myImg = null;
	float size = 0;
	
	public BitmapParticle(BaseEmitter base) {
		super(base);
		myImg = null;
		SetSize(1);
	}
	
	public BitmapParticle(BaseEmitter base, Bitmap bitmap){
		super(base);
		SetBitmap(bitmap);
		SetSize(1);
	}
	
	public BitmapParticle(BaseEmitter base, Bitmap bitmap,float size){
		super(base);
		SetBitmap(bitmap);
		SetSize(size);
	}
	
	
	public void SetBitmap(Bitmap bitmap){
		myImg = bitmap;
	}
	
	public void SetSize(float s){
		size = s;
	}
	
	/**
	 * 파생클래스는 반드시 이 메소드를 구현해야한다. 만약 변형이 안된다면 이 메소드에서 인자전달이 잘못된 거이다. 반드시 모든 멤버 변수의 인자전달을 하라.
	 * */
	@Override
	public IParticle clone() {
		return new BitmapParticle(this.base,myImg,size);
	}

	
	@Override
	public void Draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postScale(size,size);
		matrix.postRotate(life);
		matrix.postTranslate(GetX(), GetY());
		//GLES10.glTranslatef(GetX(), GetY(), 1.0f);
		//GLES10.glScalef(size, size, 1);
		Paint paint = new Paint();
		canvas.drawBitmap(myImg,matrix, paint);
	}

}
