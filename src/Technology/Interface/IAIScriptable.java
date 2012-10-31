package Technology.Interface;

import Technology.Event.EventClassInfo;

/* 
 * �� �������̽��� ��ü�� AI ����� ����Ѵ�. �� AI�޼ҵ�� AI�� ��Ʈ�� �߻��Ǹ� ȣ��ȴ�.
 *  */
public interface IAIScriptable {
	 //�����Ǿ�����
    public void OnCreated();
    //���� ���Ҷ�
    public void OnAttacked(EventClassInfo event);
    //�׾�����
    public void OnDied();
    
    
    //�� �߽߰�
    public void OnDetectEnemy(EventClassInfo event);
    //�� ���Ž�
    public void OnDiedEnemy(EventClassInfo event);
    //���� Ÿ������ �������
    public void OnTargetedEnemy(EventClassInfo event);
    //���� ��������
    public void OnMissedEnemy(EventClassInfo event);
    
    
    //�Ʊ��� �����Ǿ�����
    public void OnCreatedForces(EventClassInfo event);
    //�Ʊ��� ���ݴ��ϰ� ������
    public void OnAttackedForces(EventClassInfo event);
    //�Ʊ��� �׾�����
    public void OnDeidForces(EventClassInfo event);
}

