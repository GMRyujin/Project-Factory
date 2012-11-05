package RidemPang.Object;

import Technology.Game.GameWorld;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;
import android.util.Log;

public class RythemNote extends RythemBaseObject {
	float dy=0;

	public RythemNote(float cx, float cy, Bitmap[] bitmap,final float dy) {
		super("note", cx, cy,bitmap, 0.3f ,100, 50);
		{
			GameWorld world = GameWorld.getInstance();
			this.dy = dy;
			
			addWorld();
		}
	}
	
	@Override
	public void onActionUp(float x, float y) {
		if (IsMe(x, y)) {
			Log.v("RTest", "OnClick");
		}
	}

	@Override
	public void onActionMove(float x, float y) {
		if (IsMe(x, y)) {
			Log.v("RTest", "OnMove");
		}
	}

	@Override
	public void onActionDown(float x, float y) {
		if (IsMe(x, y)) {
			Log.v("RTest", "OnDown");
			GameWorld world = GameWorld.getInstance();
			removeWorld();
		}
	}
	@Override
	public float Update(float timeDelta) {
		super.Update(timeDelta);
		SetPos(GetX(),GetY() + dy);
		return 0;
	}
	
}
