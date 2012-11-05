package Technology.Interface;

//�� �������̽��� ������ Ŭ������ ��Ʈ�� �ɼ� �ִ�. (Touch)
public interface IControllable {
	//��ġ �̺�Ʈ�� UP�϶� ȣ��ȴ�.
	void onActionUp(float x,float y);
	//��ġ �̺�Ʈ�� DOWN�϶� ȣ��ȴ�.
	void onActionDown(float x,float y);
	//��ġ �̺�Ʈ�� MOVE�϶� ȣ��ȴ�.
	void onActionMove(float x,float y);
}

