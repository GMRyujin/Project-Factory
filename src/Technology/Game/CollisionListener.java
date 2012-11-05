package Technology.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Technology.Interface.ICollisionable;
import Technology.Interface.IUpdateable;

/*
 * �浹 �˻縦 �����Ͽ� �浹�� �Ͼ�� onCollide �̺�Ʈ�� �߻��Ų��.
 * �����ʴ� ������Ʈ�� Update�� ȣ������ �ʴ´�. ��� ������Ʈ�� Update�� ���� ����������ϸ�
 * �� �� ��  �� �������� å���� �� �浹�˻�����̴�. ������Ʈ�� ������Ʈ�� �ƴϴ�.
 * */
public class CollisionListener implements IUpdateable{
	public ArrayList<RigidBody> rigidList = new ArrayList<RigidBody>();

	ArrayList<RigidBody> removeList = new ArrayList<RigidBody>();
	
	public void Add(RigidBody object){
		rigidList.add(object);
	}
	
	public boolean Remove(RigidBody object){
		return removeList.add(object);
	}
	
	
	/* 이것은 위험한 함수이다. World애서만 사용한다. */
	public void Remove(int i){
		rigidList.remove(i);
	}
	
	public ICollisionable Get(int i){
		return rigidList.get(i);
	}
	
	public Iterator<RigidBody> itorator()
	{
		return removeList.iterator();
	}
	
	public void Clear()
	{
		rigidList.clear();
	}
	
	public int Size()
	{
		return rigidList.size();
	}

	/* �׻� 0 ���� �浹�˻� �� �̺�Ʈ �߻�*/
	public float Update(float timeDelta) {
		// TODO Auto-generated method stub
		Iterator<RigidBody> itor = rigidList.iterator();
		Iterator<RigidBody> itor1 = rigidList.iterator();
		
		
		RigidBody obj = null;
		RigidBody obj1 = null;
		
		/* ��� ��ü���� �浹�� onCollide �޼ҵ带 ȣ������ �����ɼ� �ְ� �Ѵ�. */
		while(itor.hasNext()){
			obj = itor.next();
			while(itor1.hasNext()){
				obj1 = itor1.next();
				if(obj != obj1){//�ڽŰ�� �˻縦 ���� �ʴ´�.
					if(obj.CheckColliosion(obj1) == true){
						//�浹�ߴٸ� onCollide�� ȣ���Ѵ�.(���⼭ ������ �ֳ�..)
						obj.onCollide(obj1.CreateEventClassInfo());
					}
				}
			}
		}
		
		/* ������ ������Ʈ���� �����Ѵ�. 
		itor = removeList.iterator();
		
		while(itor.hasNext()){
			rigidList.remove(itor.next());
		}
		removeList.clear();*/
		
		
		return 0;
	}
	
	
	
}

