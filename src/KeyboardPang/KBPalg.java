package KeyboardPang;

import java.util.Random;

import Technology.Event.EventClassInfo;
import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

/**
 * @brief 이 게임에 등장하는 기초 몬스터 (터치하면 죽는다.) , 월드애 자동으로 등록한다. 이 클래스를 상속받으면 월드에 두번 추가가 될수 있기때문에 상속받지않는다.
 * */
public final class KBPalg extends GameObject{
	PointF point;
	boolean bDied;
	GameWorld world;
	
	public KBPalg(Bitmap bitmap,String id, float x, float y) {
		super(id, x, y);
		world = GameWorld.getInstance();
		SetBitmap(bitmap);
		
		//랜덤으로 위치 지정
		point = new PointF((Math.random() > 0.5 ? -1 : 1) *(float)Math.random(),(Math.random() > 0.5 ? -1 : 1) * (float)Math.random());
		bDied = false;
		
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
		world.Add((IControllable)this);
	}

	@Override
	public void onActionDown(float x, float y) {
		if(IsMe(x, y)){
			//선택한 객체가 나일때.. 월드에서 나를 제거한다.
			GameWorld world = GameWorld.getInstance();
			
			//죽는 모션 생성. 이 클래스는 자동 추가되며, 자동 삭제된다.
			//new KBPalgDied(GetX(),GetY());
			
			world.Remove((IDrawable)this);
			world.Remove((IUpdateable)this);
			world.Remove((IControllable)this);
		}
	}
	
	@Override
	public void onCollide(EventClassInfo ci) {
		
	}

	@Override
	public EventClassInfo CreateEventClassInfo() {
		return new EventClassInfo("KBPalg", this);
	}
	
	@Override
	public float Update(float timeDelta) {
		SetCurrentTime(timeDelta + GetCurrentTime());
		
		Bitmap bitmap = GetBitmap();
		Log.v("Monster", "" + GetCurrentTime());
		
		//만약 5초가 넘었다면 상태를 바꾼다.
		if(GetCurrentTime() > 5.0f){
			SetCurrentTime(0);
			Random random = new Random();
			if(Math.random() < 0.5){
				point.x = -1;
			}else{
				point.x = 1;
			}
			
			if(Math.random() < 0.5){
				point.y = -1;
			}else{
				point.y = 1;
			}
		}
		
		objectPos.x += point.x * timeDelta * 40;
		objectPos.y += point.y * timeDelta * 40;
		
		/* 화면을 넘어가면 다시 랜덤위치에서 생성된다. */
		Random rand = new Random();
		if(objectPos.x < -500 || objectPos.x > 2000){
			objectPos.x = rand.nextInt(400);
		}
		if(objectPos.y < -500 || objectPos.y > 2000){
			objectPos.y = rand.nextInt(400);
		}
		
		if(bitmap != null){
            super.set((int)objectPos.x, (int)objectPos.y, (int)objectPos.x + (int)bitmap.getWidth(), (int)objectPos.y + (int)bitmap.getHeight());
	    }else{
	        super.set((int)objectPos.x, (int)objectPos.y, (int)objectPos.x + 10, (int)objectPos.y + 10);
	    }
		
		return 0;
	}

	@Override
	public void Draw(Canvas canvas) {
		super.Draw(canvas);
	}
	
}
