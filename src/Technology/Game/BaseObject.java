package Technology.Game;


import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import Technology.Event.EventClassInfo;
import Technology.Interface.INameable;
import Technology.Interface.IUpdateable;


/** BaseObject class
 * BaseObject�� �ڽ��� ���¸� ������Ʈ�ɼ� �ְ�. id�� ������ �ִ�.
 * BaseObject�� ���ӿ� ������ ������Ʈ �� ��ü�̴�.
 * �� BaseObject�� �⺻ ����� ��ġ ��ǥ�� �ε巴�� �����̴� ���̴�.
 * �� BaseObject�� ������ ��Ʈ�ʰ� ��ġ(Down)�� �Ͼ�� �� ������ �̵��ϴ� ����� ������ �ִ�.
 * ���� ���̽� Ŭ�����̴�.
 * */
public class BaseObject extends RigidBody implements IUpdateable, INameable{
		protected float currentTime = 0;
        protected PointF objectPos = null;
        protected PointF touchPos = null;
        protected PointF dir = new PointF();
        protected String id = null;
        
        public BaseObject(String id,float x,float y){
                objectPos = new PointF(x,y);
                touchPos = new PointF();
                SetId(id);
        }
        public BaseObject(String id,PointF p){
                this(id,p.x,p.y);
        }
                
        public void SetId(String id) {
                // TODO Auto-generated method stub
                this.id = id;
        }
        
        public String GetId() {
                // TODO Auto-generated method stub
                return id;
        }
        
        public void SetPos(float x,float y){
                objectPos = new PointF(x,y);
        }
        public void SetPos(PointF p){
                objectPos = new PointF(p.x,p.y);
        }
        
        public float GetX()
        {
        	return objectPos.x;
        }
        
        public float GetY()
        {
        	return objectPos.y;
        }
        
        public float GetCurrentTime()
        {
        	return currentTime;
        }
        
        public void SetCurrentTime(float time)
        {
        	currentTime = time;
        }
        
        public void onActionUp(float x, float y) {
                // TODO Auto-generated method stub
                touchPos.x = x;
                touchPos.y = y;
        }

        public void onActionDown(float x, float y) {
                // TODO Auto-generated method stub
                touchPos.x = x;
                touchPos.y = y;
                
                dir.x = objectPos.x - touchPos.x;
                dir.y = objectPos.y - touchPos.y;
                
                float len = dir.length();
                dir.x = dir.x / len;
                dir.y = dir.y / len;
        }

        public void onActionMove(float x, float y) {
                // TODO Auto-generated method stub
                
                touchPos.x = x;
                touchPos.y = y;
                
                dir.x = objectPos.x - touchPos.x;
                dir.y = objectPos.y - touchPos.y;
                
                float len = dir.length();
                dir.x = dir.x / len;
                dir.y = dir.y / len;
                
        }


        public float Update(float timeDelta) {
                // TODO Auto-generated method stub
                currentTime += timeDelta;
                
                
                
                //���� �������� 15 ������ �Ѵ�.
                if(Math.abs(objectPos.x - touchPos.x) < 15 && Math.abs(objectPos.y - touchPos.y) < 15){
                        objectPos.x = touchPos.x;
                        objectPos.y = touchPos.y;
                }else{
                        objectPos.x -= dir.x * timeDelta * 200;
                        objectPos.y -= dir.y * timeDelta * 200;
                }
                
                /* ������Ʈ�� ������ ���� ������Ʈ ���ش�. */
                 super.set((int)objectPos.x, (int)objectPos.y, (int)objectPos.x + 10, (int)objectPos.y + 10);
                
                return currentTime;
        }


        @Override
        public String GetClassName() {
                // TODO Auto-generated method stub
                return "BaseObject";
        }


        @Override
        public void onCollide(EventClassInfo ci) {
                // TODO Auto-generated method stub
                Log.v("CollideClass","Collide Class by : " + ci.GetClassName());
                Log.v("CollideName","Collide Id by : " + ci.GetId());
        }

        public EventClassInfo CreateEventClassInfo() {
                return new EventClassInfo("BaseObject", this);
        }
}
