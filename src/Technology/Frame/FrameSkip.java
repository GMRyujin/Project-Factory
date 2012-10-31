package Technology.Frame;

/* ������ ��Ű�ΰ� ������ ����ȭ�� ���ÿ� ������ Ŭ���� */
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
    
    // ���ϴ� �����Ӻ��� �ʹ� ������, 
    // false�� ��ȯ�ؼ� �ڵ带 ���۽�Ű�� �ʵ��� �Ѵ�. 
    // dt�� '��'���� (�и��� �ƴ�!!!) 
    public boolean Update( float dt ) 
    { 
        m_Timer += dt;       
        if( m_Timer<0 ) return false;       
        // �������ӿ� �ش��ϴ� �ð��� ����. 
        m_Timer -= m_SecPerFrame; 
        return true; 
    } 
    
    // Update�Ŀ� ȣ���ؼ� frame skip�� �����ؾ� �ϴ��� �˻��Ѵ�. 
    // frame skip�� �Ͼ�� �Ѵٸ� true�� ��ȯ�Ѵ�. 
    public boolean IsFrameSkip() 
    { 
        return m_Timer >= 0; 
    } 
    
    float m_Timer; 
    float m_SecPerFrame; 
}; 
