package KeyboardPang.Motion;

import java.util.Random;

import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;

/**
 * @brief 이 클래스는 랜덤 이미지 출력기이다. 이 객체는 생성되자마자 월드에 자동으로 추가되며, 월드에 자동으로 해체된다. 
 비트맵을 주면 일정 시간내에 사라지는 랜덤 비트맵 출력기이다.
 * */
public final class RandomBitmapObject extends GameObject {
	GameWorld world = GameWorld.getInstance();
	float lifeTime = 0;
	float m_maxLifeTime;
	
	public RandomBitmapObject(Bitmap[] random_bitmap,float x, float y,float maxLifeTime) {
		super("WallBreak", x, y);
		
		Bitmap[] bitmap = random_bitmap;
		int size = bitmap.length;
		int randInt = new Random().nextInt(size);
		SetBitmap(bitmap[randInt]);
		
		SetPos(x - bitmap[0].getWidth()/2, y - bitmap[0].getHeight()/2);
		
		m_maxLifeTime = maxLifeTime;
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
	}

	@Override
	public float Update(float timeDelta) {
		lifeTime += timeDelta;
		if(lifeTime > m_maxLifeTime){
			world.Remove((IUpdateable)this);
			world.Remove((IDrawable)this);
		}
		return timeDelta;
	}

}
