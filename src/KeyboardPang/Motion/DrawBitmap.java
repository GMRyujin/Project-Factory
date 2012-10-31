package KeyboardPang.Motion;

import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;

/**
 * @brief 비트맵을 단순히 띄운다. 이 비트맵은 월드에 추가되고 일정시간이 지나면 사라진다. 이 객체를 상속받으면 월드에 두번 추가되기 떄문에 상속받지 않는다.
 * @param maxLifeTime : -1이면 없애지지않는다.
 * 
 * */
public final class DrawBitmap extends GameObject{
	GameWorld world = GameWorld.getInstance();
	float lifeTime = 0;
	float m_maxLifeTime;
	public DrawBitmap(Bitmap bitmap,float x, float y,float maxLifeTime) {
		super("DrawBitmap", x, y);
		SetBitmap(bitmap);
		SetPos(x - bitmap.getWidth()/2, y - bitmap.getHeight()/2);
		m_maxLifeTime = maxLifeTime;
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
	}

	@Override
	public float Update(float timeDelta) {
		if(lifeTime == -1) return timeDelta;
		lifeTime += timeDelta;
		if(lifeTime > m_maxLifeTime){
			world.Remove((IUpdateable)this);
			world.Remove((IDrawable)this);
		}
		return timeDelta;
	}
}
