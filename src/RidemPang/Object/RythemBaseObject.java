package RidemPang.Object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.renderscript.BaseObj;
import android.util.Log;
import Technology.Control.AnimatedGameButton;
import Technology.Event.EventClassInfo;
import Technology.Game.BaseObject;
import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Game.RigidBody;
import Technology.Game.Sprite.Sprite;
import Technology.Game.Sprite.SpriteEx;
import Technology.Interface.ICollisionable;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.INameable;
import Technology.Interface.IUpdateable;

/**
 * @brief 리듬게임의 베이스 오브젝트이다.
 * */
public class RythemBaseObject extends BaseObject  implements IDrawable, IControllable{
	// TODO : 터치를 하면 왜 오브젝트가 생성된 순서대로 없어지는 지 확인해야한다.
	SpriteEx spriteEx = null;
	Paint paint;
	RectF rect;
	
	int width,height;
	
	IUpdateable update = null;
	IControllable contonller = null;
	ICollisionable collide = null;
	
	public int meA;
	static int a = 0;
	
	public RythemBaseObject(String id,float cx,float cy,Bitmap[] bitmap, float animTime,int width,int height) 
	{
		super(id,cx,cy);
		spriteEx = new SpriteEx(animTime);
		spriteEx.SetBitmapArray(bitmap);
		spriteEx.SetTranslate(0, 0);
		
		paint =  new Paint(Paint.ANTI_ALIAS_FLAG);
		rect = new RectF(cx,cy,cx+width,cy+height);
		this.width = width;
		this.height = height;
		
		Log.v("Debug","생성 " + (a) );
		meA = a;
		a++;
	}

	/**
	 * @brief 월드에 자식능 추가한다.
	 * */
	public void addWorld(){
		
		Log.v("Debug","등록 " +  this.hashCode() );
		GameWorld world = GameWorld.getInstance();
		world.Add((IDrawable)this);
		world.Add((IUpdateable)this);
		world.Add((IControllable)this);
	}
	/**
	 * @brief 월드에 자식능 삭제한다.
	 * */
	public void removeWorld(){
		GameWorld world = GameWorld.getInstance();
		Log.v("Debug","제거" + this.hashCode());
		world.Remove((IDrawable)this);
		world.Remove((IUpdateable)this);
		world.Remove((IControllable)this);
	}
	
	 /**
     * @method 현재 들어온 좌표가 현재 객체와 충돌하는가 ? <이 클래스는 GameObject에서 부터 재정의 되었다.> 
     * */
    public boolean IsMe(float x,float y)
    {
    	//if(GetBitmap() == null)	return false;	//비트맵은 GameObject에게 없고, SpriteEx에게 있기때문에 주석처리 하였다.
    	
    	int mx,my;
    	mx = (int)this.GetX();
    	my = (int)this.GetY();
    	if(mx <= x && x <= mx + width){
    		if(my <= y && y <= my + height){
    			Log.v("Debug","터치 " + meA );
    			return true;
    		}
    	}
    	
    	return false;
    }
	
	public void setOnActionController(IControllable i){
		contonller = i;
	}
	public void setOnUpdater(IUpdateable i){
		update = i;
	}
	public void setOnCollider(ICollisionable i){
		collide = i;
	}
	
	@Override
	public void onActionUp(float x, float y) {
		if(contonller != null)	contonller.onActionUp(x, y);
	}

	@Override
	public void onActionDown(float x, float y) {
		if(contonller != null)	contonller.onActionDown(x, y);
	}

	@Override
	public void onActionMove(float x, float y) {
		if(contonller != null)	contonller.onActionMove(x, y);
	}

	@Override
	public void onCollide(EventClassInfo ci) {
		if(collide != null)	collide.onCollide(ci);
	}

	@Override
	public void Draw(Canvas canvas) {
		rect = new RectF(GetX(),GetY(),GetX()+width,GetY()+height);
		spriteEx.Draw(canvas, rect ,paint);
	}
	
	@Override
	public float Update(float timeDelta) {
		spriteEx.Update(timeDelta);
		if(update != null) update.Update(timeDelta);
		return timeDelta;
	}
}
