package Technology.Interface;

//이 인터페이스를 구현한 클래스는 컨트롤 될수 있다. (Touch)
public interface IControllable {
	//터치 이벤트가 UP일때 호출된다.
	void onActionUp(int x,int y);
	//터치 이벤트가 DOWN일때 호출된다.
	void onActionDown(int x,int y);
	//터치 이벤트가 MOVE일때 호출된다.
	void onActionMove(int x,int y);
}

