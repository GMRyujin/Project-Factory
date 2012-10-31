package Technology.Particle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import Technology.Interface.IParticle;

public class BaseParticle implements IParticle {
	protected float x,y;
	protected double dx,dy;
	protected float life = 0;
	BaseEmitter base ;

	public BaseParticle(BaseEmitter base)
	{
		this.base = base;
		x = base.GetX();
		y = base.GetY();
		dx = Math.random() > 0.5 ? -Math.random()*3 : Math.random()*3;
		dy = Math.random() > 0.5 ? -Math.random()*3 : Math.random()*3;
	}

	/**
	 * 파생클래스는 반드시 이 메소드를 오버라이딩 해야한다.
	 * */
	public IParticle clone() {
		return new BaseParticle(base);
	}	

	
	@Override
	public float GetLifeTime() {
		// TODO Auto-generated method stub
		return life;
	}

	@Override
	public float GetX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float GetY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public float GetLife(){
		return life;
	}

	
	@Override
	public void SetPos(float x,float y)
	{
		this.x = (int)x;
		this.y = (int)y;
	}

	@Override
	public void SetLifeTime(float time) {
		life = time;
	}
	
	@Override
	public void Draw(Canvas canvas) {
		canvas.drawRect(new Rect((int)x,(int)y,(int)x+10,(int)y+10),new Paint(Paint.ANTI_ALIAS_FLAG));
	}

	@Override
	public float Update(float timeDelta) {
		x += dx * timeDelta * 10;
		y += dy * timeDelta * 10;

		return timeDelta;
	}
}
