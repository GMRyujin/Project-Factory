package Technology.Game;

import android.util.Log;
import Technology.Event.EventClassInfo;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

public class GamePlayer extends GameObject{

	public GamePlayer(String id, float x, float y) {
		super(id, x, y);
		// TODO Auto-generated constructor stub
	}

	//�� Ŭ������ �ٸ� ������Ʈ�� �ε����� �� ������Ʈ�� ���ش�.
	@Override
	public void onCollide(EventClassInfo ci) {
		// TODO Auto-generated method stub
		super.onCollide(ci);
		
		Log.v("PlayerMessage",ci.GetId());
		
		
		if(ci.GetClassName().equals("GameObject")){
			if(ci.GetId().equals("obj")){
				Log.v("PlayerMessage","Remove");
				GameWorld world = GameWorld.getInstance();
				GameObject obj = (GameObject)ci.GetClass();
				world.Remove((IDrawable)obj);
				world.Remove((IUpdateable)obj);
				/* ������ ���� (�Ʒ��ڵ�) */
				//world.Remove((RigidObject)obj);
			}
		}
	}
}

