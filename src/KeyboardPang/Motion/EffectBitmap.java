package KeyboardPang.Motion;

import Technology.Game.GameObject;
import Technology.Game.GameWorld;
import Technology.Game.Sprite.Sprite.SPRITE_PIVOT;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * @brief 화면 이펙트이다. 잠깐 보여주고 다시 사라진다. 이것은 월드에 자동적으로 추가되고, 해제된다.
 * */
public final class EffectBitmap extends GameObject {
	GameWorld world = GameWorld.getInstance();
	float lifeTime = 0;
	
	public EffectBitmap(Bitmap bitmap) {
		super("EffectBitmap", 0, 0);
		SetBitmap(bitmap);
		SetPos(0,0);
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
	}

	@Override
	public float Update(float timeDelta) {
		lifeTime += timeDelta;
		if(lifeTime > 0.01f){
			world.Remove((IUpdateable)this);
			world.Remove((IDrawable)this);
		}
		return timeDelta;
	}
}
