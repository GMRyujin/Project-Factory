package Technology.Game;

import android.graphics.Rect;
import android.graphics.Region;
import android.provider.MediaStore.Images.ImageColumns;
import Technology.Event.EventClassInfo;
import Technology.Interface.ICollisionable;
import Technology.Interface.IControllable;
import Technology.Interface.IUpdateable;

/**
 * @class 이 추상클래스는 충돌에 관여한다. 클래스들은 충돌이 발생하면 OnCollide로 이벤트가 발생한다.
 * */
public abstract class RigidBody extends Region implements ICollisionable{
	public RigidBody(){
		super();
	}
	public RigidBody(int left,int top,int right,int bottom){
		super(left,top,right,bottom);
	}
	public RigidBody(Rect rect){
		super(rect);
	}
	public RigidBody(float left,float top,float right,float bottom){
		super((int)left,(int)top,(int)right,(int)bottom);
	}
	
	public boolean CheckColliosion(Region r){
		int left,top,right,bottom;
		Rect rect = new Rect();
		r.getBounds(rect);
		left = rect.left;
		top = rect.top;
		right = rect.right;
		bottom = rect.bottom;
		
		Rect now = new Rect();
		getBounds(now);
		
		//�� 4���� ���� �Ѱ��� ���ԵǾ� �ִٸ� �浹�Ѱ��̴�. Ȥ�� r�� �� 4���� ������ �� ū���
		if(now.bottom < top)	return false;
		if(now.top > bottom)	return false;
		if(now.right < left)	return false;
		if(now.left > right)	return false;
		
		return true;
	}
	
	/* must implements */
	public abstract String GetClassName();
	/**
	 * @method 충돌 발생시에 이 메소드로 충돌 이벤트가 들어온다. 반드시 구현해야한다.
	 * */
	public abstract void onCollide(EventClassInfo ci);
	public abstract EventClassInfo CreateEventClassInfo();
}

