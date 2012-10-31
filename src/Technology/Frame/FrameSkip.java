package Technology.Frame;

/* 프레임 스키핑과 프레임 동기화를 동시에 지원한 클래스 */
public class FrameSkip 
{ 
	public FrameSkip(){
		Clear(); 
	} 
	
    public void Clear() 
    { 
        SetFramePerSec( 60.0f ); 
        m_Timer = 0.0f; 
    } 
    public void SetFramePerSec( float fps ) 
    { 
        m_SecPerFrame = 1.0f/fps; 
    } 
    
    // 원하는 프레임보다 너무 빠르면, 
    // false를 반환해서 코드를 동작시키지 않도록 한다. 
    // dt는 '초'단위 (밀리초 아님!!!) 
    public boolean Update( float dt ) 
    { 
        m_Timer += dt;       
        if( m_Timer<0 ) return false;       
        // 한프레임에 해당하는 시간을 뺀다. 
        m_Timer -= m_SecPerFrame; 
        return true; 
    } 
    
    // Update후에 호출해서 frame skip을 수행해야 하는지 검사한다. 
    // frame skip이 일어나야 한다면 true를 반환한다. 
    public boolean IsFrameSkip() 
    { 
        return m_Timer >= 0; 
    } 
    
    float m_Timer; 
    float m_SecPerFrame; 
}; 
