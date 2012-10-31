package Technology.Interface;

//��ƼŬ�� �����ϱ� ���ؼ��� �� �������̽��� �ݵ�� �����մϴ�. ��ƼŬ�� �ܼ��� �ڽ��� ����� �׸����� �����Ѵ�. ���� ��� ������Ʈ �Ұ������� �����Ѵ�.
//��ƼŬ å�� : ��ġ�� ������ Ÿ������ ������ ��� å��. �׸���� ��� ������Ʈ �Ұ�����.
public interface IParticle extends IDrawable, IUpdateable{
	void SetLifeTime(float time);
	float GetLifeTime();
	float GetX();
	float GetY();
	void SetPos(float x,float y);
	IParticle clone();
}
