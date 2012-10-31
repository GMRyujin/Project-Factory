package Technology.Interface;

import Technology.Event.EventClassInfo;

/* 
 * 이 인터페이스는 객체의 AI 계산을 담당한다. 각 AI메소드는 AI이 벤트가 발생되면 호출된다.
 *  */
public interface IAIScriptable {
	 //생성되었을때
    public void OnCreated();
    //공격 당할때
    public void OnAttacked(EventClassInfo event);
    //죽었을때
    public void OnDied();
    
    
    //적 발견시
    public void OnDetectEnemy(EventClassInfo event);
    //적 제거시
    public void OnDiedEnemy(EventClassInfo event);
    //적을 타겟으로 잡았을때
    public void OnTargetedEnemy(EventClassInfo event);
    //적을 놓쳤을때
    public void OnMissedEnemy(EventClassInfo event);
    
    
    //아군이 생성되었을때
    public void OnCreatedForces(EventClassInfo event);
    //아군이 공격당하고 있을때
    public void OnAttackedForces(EventClassInfo event);
    //아군이 죽었을때
    public void OnDeidForces(EventClassInfo event);
}

