package Technology.Interface;

//�� �������̽��� ������ Ŭ������ ��Ʈ�� �ɼ� �ִ�. (Touch)
public interface IControllable {
	//��ġ �̺�Ʈ�� UP�϶� ȣ��ȴ�.
	void onActionUp(int x,int y);
	//��ġ �̺�Ʈ�� DOWN�϶� ȣ��ȴ�.
	void onActionDown(int x,int y);
	//��ġ �̺�Ʈ�� MOVE�϶� ȣ��ȴ�.
	void onActionMove(int x,int y);
}

