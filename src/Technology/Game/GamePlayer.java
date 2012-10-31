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

	//이 클래스는 다른 오브젝트가 부딛히면 그 오브젝트를 없앤다.
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
				/* 문제가 있음 (아래코드) */
				//world.Remove((RigidObject)obj);
			}
		}
	}
}

