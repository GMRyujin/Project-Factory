package Technology.Game;

import Technology.Game.Sprite.Sprite;
import Technology.Game.Sprite.Sprite.SPRITE_PIVOT;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;

/* Rotate 가능한 게임 오브젝트 
 * @ 그려질수 있다.
 * @ 업데이트 될수 있다. 
 * [사용법]
 * RotateableGameObject 생성
 * Call SetBitmap 
 * */
public class RotateableGameObject extends BaseObject implements IDrawable, IControllable{
	
	Sprite sp;
	
	public RotateableGameObject(float animTime,String id, float x, float y) {
		super(id, x, y);
		// TODO Auto-generated constructor stub
		sp = new Sprite(animTime);
	}
	

	public void SetBitmapArray(Bitmap[] arr){
		sp.SetBitmapArray(arr);
	}
	
	public void SetPivot(float x,float y){
		sp.SetPivot(x, y);
	}
	public void SetPivotCenter(){
		sp.SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_CENTER);
	}
	
	protected void UpdateRotate(int x,int y){
		float angle = 0;
		
		/*각도 계산을 한다. p는 정규화된 마우스 좌표*/
		PointF p = new PointF();
		p.set(x,y);
		float len = p.length();
		p.x = p.x/len;
		p.y = p.y/len;
		
		PointF pr = new PointF(objectPos.x - p.x,objectPos.y - p.y);
		
		float r = pr.length();	//각도를 구하기 위한 코드
		//@ = cos-1 (p.x/r)
		//@ = sin-1 (p.y/r)
		float angleSeta = 0;
		
		//p(마우스 좌표) - ObjectPos
		angle = (float) (Math.atan((y - objectPos.y)/(x - objectPos.x))*(180/Math.PI));
		if(x - objectPos.x > 0){
			
		}else{
			angle = (float) (Math.atan((objectPos.y - y)/(objectPos.x - x))*(180/Math.PI));
		}
		
		if((x > objectPos.x)  && y < objectPos.y){	//MousePos :  1사분면
			angleSeta = 0;
		}else if(x < objectPos.x  && y < objectPos.y ){	//(2사분면
			angleSeta = 180;
		}else if(x < objectPos.x && y > objectPos.y){	//3사분면
			angleSeta = 180;
		}else if(x > objectPos.x && y > objectPos.y){	//4사분면
			angleSeta = 0;
		}else{											//무한대를 방지함.
			angle = -45;
			angleSeta = 0;
		}
		
		Log.v("MousePos",x + " , " + y);
		Log.v("ObjectPos",objectPos.x + " , " + objectPos.y);
		Log.v("coord","coord y : " + (y - objectPos.y) + " x : " + (x - objectPos.x));
		Log.v("Angle","Angle : " +  angle );
		Log.v("AngleSeta","AngleSeta : " +  angleSeta );
		
		//변환.
		sp.SetRotate(angle + angleSeta);
	}
	
	public void onActionUp(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void onActionDown(int x, int y) {
		// TODO Auto-generated method stub
		UpdateRotate(x,y);
	}

	public void onActionMove(int x, int y) {
		// TODO Auto-generated method stub
		UpdateRotate(x,y);
	}
	
	@Override
	public float Update(float timeDelta) {
		// TODO Auto-generated method stub
		float sumTime = super.Update(timeDelta);
		sp.Update(timeDelta);
		return sumTime;
	}

	@Override
	public void Draw(Canvas canvas) {
		// TODO Auto-generated method stub
		sp.Draw(canvas);
	}
}

