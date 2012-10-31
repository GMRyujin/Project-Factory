package Technology.Interface;

//파티클을 구현하기 위해서는 이 인터페이스를 반드시 구현합니다. 파티클은 단순히 자신이 어떤것을 그릴지만 결정한다. 또한 어떻게 업데이트 할것인지를 결정한다.
//파티클 책임 : 위치와 라이플 타임을을 제외한 모든 책임. 그리기와 어떻게 업데이트 할것인지.
public interface IParticle extends IDrawable, IUpdateable{
	void SetLifeTime(float time);
	float GetLifeTime();
	float GetX();
	float GetY();
	void SetPos(float x,float y);
	IParticle clone();
}
