package Technology.Game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import Technology.Event.EventClassInfo;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.INameable;
import Technology.Interface.IUpdateable;


/* GameObject class
 * GameObject�� �ڽ��� ���¸� ������Ʈ�ϰ�, �׷����������, ��Ʈ�� �ɼ� �ִ�. 
 * GameObject�� ���ӿ� ������ ������Ʈ �� ��ü�̴�.
 * �� GameObject�� �⺻ ����� ��ġ ��ǥ�� �ε巴�� �����̴� ���̴�.
 * �� GameObject�� ������ ��Ʈ�ʰ� ��ġ(Down)�� �Ͼ�� �� ������ �̵��ϴ� ����� ������ �ִ�.
 * */
public class GameObject extends BaseObject implements IControllable, IDrawable, IUpdateable, INameable{
		private Bitmap bitmap = null;
		
        public GameObject(String id,float x,float y){
        		super(id,x,y);
                objectPos = new PointF(x,y);
                touchPos = new PointF();
                SetId(id);
        }
        public GameObject(String id,PointF p){
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
        
        public void SetBitmap(Bitmap bitmap){
                this.bitmap = bitmap;
        }
        
        
        /**
         * @method 자신의 비트맵을 구한다.*/
        public Bitmap GetBitmap()
        {
        	return this.bitmap;
        }
        
        /**
         * @method 현재 들어온 좌표가 현재 객체와 충돌하는가 ? <반드시 비트맵이 있어야한다. 비트맵이 없다면 항상 false를 호출한다.> 
         * */
        public boolean IsMe(float x,float y)
        {
        	if(GetBitmap() == null)	return false;
        	
        	int mx,my;
        	mx = (int)GetX();
        	my = (int)GetY();
        	int width = GetBitmap().getWidth();
        	int height = GetBitmap().getHeight();
        	
        	if(mx <= x && x <= mx + width){
        		if(my <= y && y <= my + height){
        			return true;
        		}
        	}
        	return false;
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
                if(bitmap != null){
                        super.set((int)objectPos.x, (int)objectPos.y, (int)objectPos.x + (int)bitmap.getWidth(), (int)objectPos.y + (int)bitmap.getHeight());
                        
                }else{
                        super.set((int)objectPos.x, (int)objectPos.y, (int)objectPos.x + 10, (int)objectPos.y + 10);
                }
                
                return currentTime;
        }


        @Override
        public String GetClassName() {
                // TODO Auto-generated method stub
                return "GameObject";
        }


        @Override
        public void onCollide(EventClassInfo ci) {
                // TODO Auto-generated method stub
                Log.v("CollideClass","Collide Class by : " + ci.GetClassName());
                Log.v("CollideName","Collide Id by : " + ci.GetId());
                
        /*        if(ci.GetClassName().equals("GameObject")){
                        GameObject object = (GameObject)ci.GetClass();
                        Log.v("CollideName","�ε��� ������Ʈ ���̵� : " + ci.GetId());
                }*/
        
        }


        public void Draw(Canvas canvas) {
                // TODO Auto-generated method stub
                if(bitmap == null){
                        canvas.drawRect(new Rect((int)objectPos.x,(int)objectPos.y,10,10), null);
                }else{
                        canvas.drawBitmap(bitmap, (int)objectPos.x,(int)objectPos.y, null);
                }
        }


        public EventClassInfo CreateEventClassInfo() {
                return new EventClassInfo("GameObject", this);
        }
}
