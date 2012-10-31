package Technology.Particle;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;
import Technology.Interface.IDrawable;
import Technology.Interface.IParticle;
import Technology.Interface.IUpdateable;


/**
 * @brief 파티클 생성기이다. 이 파티클 발생기는 거의 변하지 않는다.
 * */
public class BaseEmitter implements IDrawable, IUpdateable {
	
	ArrayList<IParticle> list = new ArrayList<IParticle>();
	float rx,ry;//������ ��ġ
	float MAX_LIFE_TIME = 1;
	float EMIT_SPPED;
	int MAX_EMIT;
	IParticle PARTICLE;
	
	
	int particleCount = 0;
	float localEmitTime = 0;
	
	/**
	 * @method
	 * @param responX : 파티클이 리스폰될  X위치
	 * @param responY : 파티클이 리스폰될  Y위치
	 * @param emitMax : 파티클의 최대 출력
	 * @param emitSpeed : 초당 파티클의 생성 갯수
	 * @param maxLifeTime : 파티클이 이미터에서 나온후 존재할 시간
	 * */
	public BaseEmitter(float responX,float responY,int emitMax,float emitSpeed,float maxLifetime) 
	{
		SetPos(responX,responY);
		SetLifeTime(maxLifetime);
		SetEmitSpeed(emitSpeed);
		SetEmitMax(emitMax);
	}
	
	//�ݵ�� ��ƼŬ�� ������ش�.
	public void SetParticle(IParticle p)
	{
		PARTICLE = p;
	}
	
	public void SetEmitMax(int max)
	{
		MAX_EMIT = max;
	}
	
	public void SetEmitSpeed(float speed)
	{
		EMIT_SPPED = speed;
	}
	
	public void SetLifeTime(float maxLife)
	{
		MAX_LIFE_TIME = maxLife;
	}
	public float GetLifeTime()
	{
		return MAX_LIFE_TIME;
	}
	
	public void SetPos(float x,float y)
	{
		rx = x;
		ry = y;
	}
	public float GetX()
	{
		return rx;
	}
	public float GetY()
	{
		return ry;
	}
	
	//��ƼŬ�� ��� Ŭ�����մϴ�.
	public void Clear()
	{
		list.clear();
	}
	
	//��ƼŬ�� ����������Ŭ�� ��ƼŬ�� ��ġ�� �̹��Ͱ� ����Ѵ�.
	@Override
	public float Update(float timeDelta) {
		localEmitTime += timeDelta;
		if(localEmitTime > EMIT_SPPED){
			if(list.size() < MAX_EMIT)//����� �ƽ� �̹Ժ��� ������ ������ �ʴ´�.
			{
				list.add(PARTICLE.clone());
				localEmitTime = 0;
			}
		}
		
		Iterator<IParticle> itor =  list.iterator();
		while(itor.hasNext()){
			IParticle p = itor.next();
			//������Ʈ��  ������Ÿ���� �����ٸ� ��ƼŬ�� �����Ѵ�.
			p.Update(timeDelta);
			if(p.GetLifeTime() > this.GetLifeTime())
			{
				itor.remove();
			}else{
				p.SetLifeTime(p.GetLifeTime() + timeDelta);
			}
		}
		return timeDelta;
	}

	//��ƼŬ�� �׸��� �۾��� ��� ��ƼŬ���� ����Ѵ�.
	@Override
	public void Draw(Canvas canvas) {
		Iterator<IParticle> itor =  list.iterator();
		while(itor.hasNext()){
			itor.next().Draw(canvas);
		}
	}
	
}
