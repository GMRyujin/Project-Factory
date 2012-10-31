package RidemPang.Object;

import Technology.Game.GameWorld;
import Technology.Interface.IUpdateable;

/**
 * @brief 타이머이다. 업데이트 인터페이스를 지정하여 사용자가 직접 지정할수있다.
 * */
public class TechTimer implements IUpdateable {
	float currentTime = 0;
	IUpdateable updator = null;
	float maxTime = 0;
	int callNum = 0;
	int maxCallNum = 1;
	
	/**
	 * @brief 타이머를 생성한다.
	 * @param sec : 타이머가 호출할 시간이다.
	 * */
	public TechTimer(float sec){
		maxTime = sec;
		maxCallNum = 1;
	}
	
	/**
	 * @brief 타이머를 생성한다.
	 * @param sec : 타이머가 호춣할 시간이다.
	 * @param callNumber : 타이머가 몇번 호출될것인지를 지정한다. 0은 무한.
	 * */
	public TechTimer(float sec,int callNumber){
		maxTime = sec;
		maxCallNum = callNumber;
	}
	
	public void setOnUpdater(IUpdateable u){
		updator = u;
	}
	
	/**
	 * @brief 타이머를 리셋한다.
	 * */
	public void Reset()
	{
		callNum = 0;
		currentTime = 0;
	}
	/**
	 * @brief 타이머를 시작한다.
	 * */
	public void StartTimer()
	{
		GameWorld.getInstance().Add(this);
	}
	/**
	 * @brief 타이머를 정지한다.
	 * */
	public void StopTimer(){
		GameWorld.getInstance().Remove(this);
	}
	/**
	 * @brief 타이머의 호출시간을 지정한다.
	 * */
	public void SetTimer(float sec){
		maxTime = sec;
	}
	
	
	
	@Override
	public float Update(float timeDelta){
		if(maxCallNum != 0){
			if(callNum >= maxCallNum){
				return timeDelta;
			}
		}
		currentTime += timeDelta;
		
		if(currentTime >= maxTime){
			callNum++;
			currentTime = 0;
			if(updator != null)	updator.Update(timeDelta);
		}
		return timeDelta;
	}
}
