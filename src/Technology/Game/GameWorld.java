package Technology.Game;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import RidemPang.Object.RythemNote;
import Technology.Interface.ICollisionable;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.INameable;
import Technology.Interface.IUpdateable;
import android.graphics.Canvas;
import android.util.Log;

/**
 * @brief 싱글톤 패턴이 적용되었으며, 이 객체를 구할 떄는 GetInstance 명령어를 사용하도록 한다.
 * 주의해야할점 : 이 객체를 전역변수에 저장하여 사용하지 말기 바란다.
 * 지역변수로 만들어서 쓰거나, GetInstnace명령어를 사용하기 바란다.
 * */
public final class GameWorld implements IControllable, IDrawable, IUpdateable {
        float currentTime = 0;
        static GameWorld world;
        
        //�� Ŭ������ ����Ϸ��� �� �Լ��� ����Ͻÿ�.
        public static GameWorld getInstance(){
	        if(world == null){
	                world = new GameWorld();
	        }
	        return world;
        }
        
        private GameWorld(){/* Nothing */}
        
        ArrayList<IControllable>         controllList        = new ArrayList<IControllable>();        //��Ʈ��
        ArrayList<IDrawable>                drawList                = new ArrayList<IDrawable>();                //�׸���
        ArrayList<IUpdateable>                updateList                = new ArrayList<IUpdateable>();                //������Ʈ
        CollisionListener                         collLis                 = new CollisionListener();                        //�浹

        ArrayList<IControllable>         dummyControllList        = new ArrayList<IControllable>();        //��Ʈ��
        ArrayList<IDrawable>                dummyDrawList                = new ArrayList<IDrawable>();                //�׸���
        ArrayList<IUpdateable>                dummyUpdateList                = new ArrayList<IUpdateable>();                //������Ʈ
        CollisionListener                         dummyCollLis                 = new CollisionListener();                        //�浹
        
        ArrayList<IControllable>         controllListAdder       = new ArrayList<IControllable>();        //��Ʈ��
        ArrayList<IDrawable>                drawListAdder                = new ArrayList<IDrawable>();                //�׸���
        ArrayList<IUpdateable>                updateListAdder                = new ArrayList<IUpdateable>();                //������Ʈ
        CollisionListener                         collLisAdder                 = new CollisionListener();                        //�浹
        
        

        /* 12.11.05 중복 추가,삭제를 패치하였다. 오브젝트가 두번 삭제되면 팅기고, 두번 추가되면 이상현상이 생기므로 이를 패치하였다.
         * 이 문제의 해결방법은 오브젝트가 이미 추가가 되었는지 혹은 두번 삭제되고 있는지를 확인해보면 된다.
         * 이 문제의 원인은 멀티터치를 적용하면서 오브젝트가 연속으로 두번 터치당하고 이를 통해 오브젝트는 삭제 리스트에 두개가 추가된다.
         * 따라서 이를 리스트에서 확인후 추가가 되어있으면 리스트에 추가하지 않는 것으로 해결하였다. */
        
        /* 리스트의 contain 메소드는 객체의 주소를 비교하는 것이 아닌 다른 기준으로 비교하는 것 같다. 따라서 이 메소드는 하드 == 연산을 할때 쓰지 않기 바란다.
         * 대신에 이터레이터를 사용하여 직접 주소를 비교하기 바란닫.  */
        public void Add(IControllable object){
        	Iterator<IControllable> itor = controllListAdder.iterator();
        	while(itor.hasNext()){
        		if(itor.next() == object) return;
        	}
        	controllListAdder.add(object);
        }
        public void Add(IDrawable object){
        	Iterator<IDrawable> itor = drawListAdder.iterator();
        	while(itor.hasNext()){
        		if(itor.next() == object) return;
        	}
        	drawListAdder.add(object);
        }
        public void Add(IUpdateable object){
        	Iterator<IUpdateable> itor = updateListAdder.iterator();
        	while(itor.hasNext()){
        		if(itor.next() == object) return;
        	}
        	updateListAdder.add(object);
        }
        public void Add(RigidBody object){
        	Iterator<RigidBody> itor = collLisAdder.itorator();
        	while(itor.hasNext()){
        		if(itor.next() == object) return;
        	}
        	collLisAdder.Add(object);
        }
        
        
        
        /**
         * @method 리스트의 오브젝트 갯수를 구합니다.
         * */
        public int GetDrawListNum()
        {
        	//synchronized(this) {
        		return drawList.size();
        	//}
        }
        /**
         * @method 리스트의 오브젝트 갯수를 구합니다.
         * */
        public int GetUpdateListNum()
        {
        	//synchronized(this) {
        		return updateList.size();
        	//}
        }
        /**
         * @method 리스트의 오브젝트 갯수를 구합니다.
         * */
        public int GetCollideListNum()
        {
        	//synchronized(this) {
        		return collLis.Size();
        	//}
        }
        /**
         * @method 리스트의 오브젝트 갯수를 구합니다.
         * */
        public int GetControllListNum()
        {
        	//synchronized(this) {
        		return controllList.size();
        	//}
        }
        
        /**
         * @method 더미리스트의 오브젝트 갯수를 구합니다. 반드시 0이 나와야합니다.
         * */
        public int GetDummyDrawListNum()
        {
        	//synchronized(this) {
        		return dummyDrawList.size();
        	//}
        }
        /**
         * @method 더미리스트의 오브젝트 갯수를 구합니다. 반드시 0이 나와야합니다.
         * */
        public int GetDummyUpdateListNum()
        {
        	//synchronized(this) {
        		return dummyUpdateList.size();
        	//}
        }
        /**
         * @method 더미리스트의 오브젝트 갯수를 구합니다. 반드시 0이 나와야합니다.
         * */
        public int GetDummyCollideListNum()
        {
        	//synchronized(this) {
        		return dummyCollLis.Size();
        	//}
        }
        /**
         * @method 더미리스트의 오브젝트 갯수를 구합니다. 반드시 0이 나와야합니다.
         * */
        public int GetDummyControllListNum()
        {
        	//synchronized(this) {
        		return dummyControllList.size();
        	//}
        }
        
        /* ������Ʈ ã�� Test�ʿ�
         * ������ false ����, �����Ѵٸ� out �� obj ����*/
        public INameable Find(String id){
        	//synchronized(this) {
	        	/* IUpdateable���� ã�´�. */
	        	Iterator<IUpdateable> itor = updateList.iterator();
	        	
	        	while(itor.hasNext()){
	        		IUpdateable obj = itor.next();
	        		if(obj instanceof INameable){
	        			if(((INameable)obj).GetId().equals(id)){
	        				return (INameable)obj;
	        				
	        			}
	        		}
	        	}
	        	
	        	/* IDrawable���� ã�´�. */
	        	Iterator<IDrawable> itor1 = drawList.iterator();
	        	
	        	while(itor.hasNext()){
	        		IUpdateable obj = itor.next();
	        		if(obj instanceof INameable){
	        			if(((INameable)obj).GetId().equals(id)){
	        				return (INameable)obj;
	        			}
	        		}
	        	}
	        	
	        	/* IControllable ã�´�. */
	        	Iterator<IControllable> itor2 = controllList.iterator();
	        	
	        	while(itor.hasNext()){
	        		IUpdateable obj = itor.next();
	        		if(obj instanceof INameable){
	        			if(((INameable)obj).GetId().equals(id)){
	        				return (INameable)obj;
	        			}
	        		}
	        	}
        	//}
        	
        	return null;
        }
     
    //만일 터치시에 오브젝트의 삭제 혹은 추가를 할때 팅길경우 GameWorld의 Remove 혹은 Add 부분에 문제가 생긴것기므로 한번 살펴보기 바란다.
	/* 더미 버퍼에 추가하고, 모든 업데이트와 Draw를 끝마친후 제거한다. */
    //이미 포함되어있는지 확인작업을 한다. 중복 삭제를 피한다.
    /* 리스트의 contain 메소드는 객체의 주소를 비교하는 것이 아닌 다른 기준으로 비교하는 것 같다. 따라서 이 메소드는 하드 == 연산을 할때 쓰지 않기 바란다.
     * 대신에 이터레이터를 사용하여 직접 주소를 비교하기 바란닫.  */
	public boolean Remove(IDrawable object) {
		Iterator<IDrawable> itor = dummyDrawList.iterator();
		if(itor.hasNext()){
			if(itor.next() == object) return false;
		}
		return dummyDrawList.add(object);
	}

	public boolean Remove(IUpdateable object) {
		Iterator<IUpdateable> itor = dummyUpdateList.iterator();
		if(itor.hasNext()){
			if(itor.next() == object) return false;
		}
		return dummyUpdateList.add(object);
	}

	public boolean Remove(IControllable object) {
		Iterator<IControllable> itor = dummyControllList.iterator();
		if(itor.hasNext()){
			if(itor.next() == object) return false;
		}
		return dummyControllList.add(object);
	}

	public void Remove(RigidBody object) {
		Iterator<RigidBody> itor = dummyCollLis.itorator();
		if(itor.hasNext()){
			if(itor.next() == object) return;
		}
		dummyCollLis.Add(object);
	}

        /**
         * @brief World내의 객체를 모두 삭제한다.
         * */
        public void Clear()
        {
        	int size = 0;
        	
        	size = updateList.size();
        	Iterator<IUpdateable> uitor = updateList.iterator();
        	while(uitor.hasNext()){
        		Remove(uitor.next());
        	}
        	
        	size = drawList.size();
        	Iterator<IDrawable> ditor = drawList.iterator();
        	while(ditor.hasNext()){
        		Remove(ditor.next());
        	}
        	
        	size = controllList.size();
        	Iterator<IControllable> citor = controllList.iterator();
        	while(citor.hasNext()){
        		Remove(citor.next());
        	}
        	
        	size = collLis.Size();
        	Iterator<RigidBody> ritor = collLis.itorator();
        	while(ritor.hasNext()){
        		Remove(ritor.next());
        	}
        	
        	updateListAdder.clear();
        	controllListAdder.clear();
        	drawListAdder.clear();
        	collLisAdder.Clear();
       
        	currentTime = 0;
        }
        
        /* interface implements */
        /* @pre : �� ���� ��
         * @post : �׻� 0�� �����Ѵ�.*/
        public float Update(float timeDelta) {
        	//synchronized (this) {
	    		int size = 0;
	    		
	    		//TODO 터치 명령어는 여기서 삭제, 추가한다. 여기에 추가하니까 터치를 한후 팅기는 버그가 사라졌다.
	    		SafeControllRemove();
	    		SafeControlAdd();
	    		
	    		//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
	    		size = updateList.size();
	    		Iterator<IUpdateable> itor = dummyUpdateList.iterator();
	    		while(itor.hasNext()){
	    			IUpdateable u = itor.next();
		    		for(int i = 0 ; i < size ; i++){
		    			if(updateList.get(i) == u){
		    				updateList.remove(i);
		    				break;
		    			}
		    		}
	    		}
	    			
	    		//더미 콜리전 리스트를 지운다. 이 구문은 에러가있다. ArrayList의 Remove 오동작.
	    		/*Iterator<RigidBody> ritor = dummyCollLis.itorator();
	    		while(itor.hasNext()){
	    			collLis.Remove(ritor.next());
	    		}*/
	    		
	    		size = collLis.Size();
	    		Iterator<RigidBody> ritor = dummyCollLis.itorator();

	    		while(itor.hasNext()){
	    			ICollisionable u = ritor.next();
		    		for(int i = 0 ; i < size ; i++){
		    			if(collLis.Get(i) == u){
		    				collLis.Remove(i);
		    				break;
		    			}
		    		}
	    		}
	    		//1회성 이므로 반드시 지워준다.
	    		dummyUpdateList.clear();
	    		dummyCollLis.Clear();
	    		
	            //���� ������Ʈ�� ���¸� ������Ʈ ���ְ�..
	            itor = updateList.iterator();
	            while(itor.hasNext()){
	                    itor.next().Update(timeDelta);
	            }
	            
	            //�� �������� �浹 �̺�Ʈ�� �����ش�.
	            collLis.Update(timeDelta);
	            
	            /* 오브젝트들을 추가한다. */
	    		itor = updateListAdder.iterator();
	    		while(itor.hasNext()){
	    			updateList.add(itor.next());
	    		}
	    		ritor = collLisAdder.itorator();
	    		while(ritor.hasNext()){
	    			collLis.Add(ritor.next());
	    		}
	    		updateListAdder.clear();
	    		collLisAdder.Clear();
        	//}
            return 0;
        }

        public void Draw(Canvas canvas) {
        	//synchronized (this) {
                Iterator<IDrawable> itor;
                
        		//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서  이 구문은 에러가있다. ****ArrayList의 Remove 오동작.
        		/*itor = dummyDrawList.iterator();
        		while(itor.hasNext()){
        			drawList.remove(itor.next());
        		}*/
                
                int size = drawList.size();
	    		itor = dummyDrawList.iterator();

	    		while(itor.hasNext()){
	    			IDrawable u = itor.next();
		    		for(int i = 0 ; i < size ; i++){
		    			if(drawList.get(i) == u){
		    				drawList.remove(i);
		    				break;
		    			}
		    		}
	    		}
        		dummyDrawList.clear();
        		
                itor = drawList.iterator();
                while(itor.hasNext()){
                        itor.next().Draw(canvas);
                }
                
              //오브젝트들을 추가한다.
        		itor = drawListAdder.iterator();
        		while(itor.hasNext()){
        			drawList.add(itor.next());
        		}
        		drawListAdder.clear();
        	//}
        }

        public void SafeControllRemove()
        {
        	//synchronized(this) {
	    		Iterator<IControllable> itor;
	    		//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서 ****ArrayList의 rmove 오동작.
	    		/*itor = dummyControllList.iterator();
	    		while(itor.hasNext()){
	    			controllList.remove(itor.next());
	    			//if(controllList.equals(itor.next())
	    		}*/
	    		int size = controllList.size();
	    		itor = dummyControllList.iterator();
	    		while(itor.hasNext()){
	    			IControllable u = itor.next();
		    		for(int i = 0 ; i < size ; i++){
		    			if(controllList.get(i) == u){
		    				controllList.remove(i);
		    				break;
		    			}
		    		}
	    		}
	    		dummyControllList.clear();//1회성이므로 반드시 모두 지워준다.
        	//}
        }
        
        public void SafeControlAdd()
        {
        	Iterator<IControllable> itor;
    	
    		itor = controllListAdder.iterator();
    		while(itor.hasNext()){
    			controllList.add(itor.next());
    		}
    		controllListAdder.clear();
        }

        /* 갑작스런 이벤트 발생에 오류가 생길수있기때문에 동기화 작업을 해준다. */
        public void onActionUp(float x, float y) {
        	//TODO 노트가 일정이상 아래로 내려가도록 하면 삭제하도록 하였다. 그러나 한참을 그렇게 기다린후 갑작스럽게 터치를 하면 튕기는 문제가 사라졌다. 
        	//이것의 해결 방법은 World의 update 문으로 이벤트의 추가 삭제 로직을 옮겼다. 아마도 예상되는 문제는 역시 SafeControlAdd문제로써 쓰레드간 동기화의 문제인것 같다.
			//SafeControllRemove();// 여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
			//SafeControlAdd(); //TODO 전체 터치 이벤트를 추가하는 로직을 Add가 앞쪽으로 오도록 수정하였따. 
			Iterator<IControllable> itor = controllList.iterator();	
			while (itor.hasNext()) {
				itor.next().onActionUp(x, y);
			}
			
        }


        public void onActionDown(float x, float y) {
			//SafeControllRemove();// 여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서 여기에
			//SafeControlAdd();
			Iterator<IControllable> itor = controllList.iterator();
			while (itor.hasNext()) {
				itor.next().onActionDown(x, y);
			}
			
        }

        public void onActionMove(float x, float y) {
			//SafeControllRemove();// 여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
			//SafeControlAdd();
			Iterator<IControllable> itor = controllList.iterator();
			while (itor.hasNext()) {
				itor.next().onActionMove(x, y);
			}
			
        }
}
