package Technology.Interface;

import Technology.Event.EventClassInfo;

/*
 *  �� �������̽��� �����ϰ� �Ǹ� �浹 �̺�Ʈ�� �߻��Ѵ�. 
 *  �� �������̽��� ���� �������̽��� ������ Ŭ���������� �̺�Ʈ�� �߻��Ѵ�.
 *  �浹 �߻��� onCollide�� �߻��ȴ�. (ClassInfo�� �浹�� �߻��� Ŭ������ ��� Ŭ���� �����̴�.)
 *  */

public interface ICollisionable {
	void onCollide(EventClassInfo ci);
}

