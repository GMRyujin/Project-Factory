package Technology.Game.AI;


import Technology.Event.EventClassInfo;
import Technology.Interface.IAIScriptable;
import Technology.Interface.IUpdateable;

/*
 * �� �̽�ũ��Ʈ�� AI�� ���̽��̴�. AI Ŭ������ �� Ŭ������ ��ӹ޴´�. 
 * ������Ʈ �Ǹ鼭 �� �޼ҵ���� �������ش�.
 * */
public abstract class AIBase implements IUpdateable, IAIScriptable{
        float sumTime = 0;
        @Override
		public void OnCreated() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void OnAttacked(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void OnDied() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void OnDetectEnemy(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnDiedEnemy(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnTargetedEnemy(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnMissedEnemy(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnCreatedForces(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnAttackedForces(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void OnDeidForces(EventClassInfo event) {
			// TODO Auto-generated method stub
			
		}



		@Override
        public final float Update(float timeDelta) {
                // TODO Auto-generated method stub
                sumTime += timeDelta;
                
                return sumTime;
        }
        
}
