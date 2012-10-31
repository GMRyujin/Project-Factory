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
        
        

        /* ������Ʈ�� �߰��Ѵ�. */
        public void Add(IControllable object){
        	//synchronized (this) {
                controllList.add(object);
        	//}
        }
        public void Add(IDrawable object){
        	//synchronized(this) {
               drawList.add(object);
        	//}
        }
        public void Add(IUpdateable object){
        	//synchronized(this) {
                updateList.add(object);
        	//}
        }
        public void Add(RigidBody object){
        	//synchronized(this) {
                collLis.Add(object);
        	//}
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
        
        /* 더미 버퍼에 추가하고, 모든 업데이트와 Draw를 끝마친후 제거한다. */
        public boolean Remove(IDrawable object){
        	//synchronized(this) {
                return dummyDrawList.add(object);
        	//}
        }
        
        public boolean Remove(IUpdateable object){
        	//synchronized(this) {
                return dummyUpdateList.add(object);
        	//}
        }
        
        public boolean Remove(IControllable object){
        	//synchronized(this) {
                return dummyControllList.add(object);
        	//}
        }
        public void Remove(RigidBody object){
        	//synchronized(this) {
                dummyCollLis.Add(object);
        	//}
        }
        
        /* interface implements */
        /* @pre : �� ���� ��
         * @post : �׻� 0�� �����Ѵ�.*/
        public float Update(float timeDelta) {
        	//synchronized (this) {
	    		int size = 0;
	    		
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

        /* 갑작스런 이벤트 발생에 오류가 생길수있기때문에 동기화 작업을 해준다. */
        public void onActionUp(int x, int y) {
        	//synchronized (this) {
        	SafeControllRemove();//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
                Iterator<IControllable> itor = controllList.iterator();
                while(itor.hasNext()){
                        itor.next().onActionUp(x, y);
                }
        	//}
        }


        public void onActionDown(int x, int y) {
        	//synchronized (this) {
        	SafeControllRemove();//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
                Iterator<IControllable> itor = controllList.iterator();
                while(itor.hasNext()){
                        itor.next().onActionDown(x, y);
                }
        	//}
        }

        public void onActionMove(int x, int y) {
        	//synchronized (this) {
        	SafeControllRemove();//여기에서 목록에있는 업데이트들을 제거한다. 갑작스런 이벤트의 에러를 막기위해서
                Iterator<IControllable> itor = controllList.iterator();
                while(itor.hasNext()){
                        itor.next().onActionMove(x, y);
                }
        	//}
        }
}
