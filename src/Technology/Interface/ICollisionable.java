package Technology.Interface;

import Technology.Event.EventClassInfo;

/*
 *  이 인터페이스를 구현하게 되면 충돌 이벤트가 발생한다. 
 *  이 인터페이스는 같은 인터페이스를 구현한 클래스끼리만 이벤트가 발생한다.
 *  충돌 발생시 onCollide가 발생된다. (ClassInfo는 충돌이 발생한 클래스를 담는 클래스 정보이다.)
 *  */

public interface ICollisionable {
	void onCollide(EventClassInfo ci);
}

