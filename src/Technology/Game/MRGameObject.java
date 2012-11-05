package Technology.Game;

/* �����ϼ� �ְ�, ȸ��ɼ� �ִ� ���� ������Ʈ (Move Rotate GameObject
 *  */
public class MRGameObject extends RotateableGameObject{

	public MRGameObject(float animTime, String id, float x, float y) {
		super(animTime, id, x, y);
		// TODO Auto-generated constructor stub
	}
	
	 public void onActionUp(float x, float y) {
         // TODO Auto-generated method stub
		 super.onActionUp(x, y);
		 
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
         
         super.onActionDown(x, y);
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
         
         super.onActionMove(x, y);
	 }

	@Override
	public float Update(float timeDelta) {
		// TODO Auto-generated method stub
		float sumTime = super.Update(timeDelta);
		sp.SetTranslate(objectPos.x, objectPos.y);
		return sumTime;
	}
}

