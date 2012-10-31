package Technology.Game;

import android.util.Log;
import Technology.Interface.INameable;

/* �Ѿ� �۷��� */
public class Bullet extends MRGameObject {
	private String targetId;

	public Bullet(float animTime, String id, float x, float y) {
		super(animTime, id, x, y);
		// TODO Auto-generated constructor stub
	}
	
	void SetTargetId(String id){
		this.targetId = id;
	}
	
	@Override
	public float Update(float timeDelta) {
		// TODO Auto-generated method stub
		float sumTime = super.Update(timeDelta);
		GameWorld world = GameWorld.getInstance();
		
		INameable obj = null;
		obj = world.Find("player");
		if(obj != null){
			Log.v("Find", "OBJ : " + obj);
			if(obj instanceof BaseObject){
				BaseObject target = (BaseObject)obj;
				onActionDown((int)target.objectPos.x,(int)target.objectPos.y);
			}
		}
		
		return sumTime;
	}
}

