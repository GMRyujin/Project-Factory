package Technology.Frame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import Technology.Interface.IUpdateable;

/*
 * �� Ŭ������ ������ ��Ʈ���� �����ϰ�, �� ��Ʈ�ʵ��� �ð��� ��� ���ϴ� ������ �Ѵ�.*/
public class AnimationFrame implements IUpdateable{
	/*
	 * ������Ʈ ���
	 * if(currentTime > updateTime) then Update
	 * */
	float updateTime = 0;	//�ڽ��� ������Ʈ �ð� (���� ���ڷ� ����)
	
	float currentTime = 0;	//���� �ڽ��� ���� �ð�
	
	int currentFrameNumber = 0;	//���� �ڽ��� ��Ʈ�� ������ �ѹ�(�� ���� �����ϸ� �ִϸ��̼� �ȴ�.)
	
	Bitmap bitmapFrame = null;	//��Ʈ�� ������
	int width,height;	//��Ʈ���Ѱ��� �ʺ�� ����
	int frameLength = 0;	//�ڽ��� ������ ����
	
	/* 
	 * updateTime not null*/
	public AnimationFrame(float updateTime){
		assert(updateTime != 0);
		this.updateTime = updateTime;
	}
	
	/* ��Ʈ�� �¾� (���θ� ����)
	 * @pre : bitmapFrame not null, width not zero, height not zero, frameNumber not zero
	 * ���� width�� ������ �������� ��ü ���̿��� �Ѵ�.
	 * @post : ���������� ��Ʈ���� �����ߴٸ� true
	 * */
	public boolean SetBitmapFrame(Bitmap bitmapFrame,int width,int height,int frameLength){
		assert(bitmapFrame != null && width != 0 && height != 0 && frameLength != 0);
		
		this.bitmapFrame = bitmapFrame;
		this.width = width;
		this.height = height;
		this.frameLength = frameLength;
		
		return true;
	}
	
	/* ��Ʈ�� �¾�
	 * @pre : bitmap not null
	 * @post : ���������� ��Ʈ���� �����ߴٸ� true, �����ߴٸ� false ��ȯ.*/
	public boolean SetBitmapFrame(Bitmap[] bitmap){
		assert(bitmap != null);
		
		//���� ����
		int len = bitmap.length;
		frameLength = len;
		
		//width, height ����
		width = bitmap[0].getWidth();
		height = bitmap[0].getHeight();
		
		//���� ��Ʈ��(�̾���� ���� ����� ��) �ϳ��� ������
		Bitmap created = null;
		//ARGB_8888 �� ���?�� �����Ҽ� �ִ� �ɼ��̴�.
		created = Bitmap.createBitmap(width*len, height, android.graphics.Bitmap.Config.ARGB_8888);
		
		

		//Paint ����
		Paint p = new Paint();
		p.setDither(true);
		p.setFlags(Paint.ANTI_ALIAS_FLAG);
		
		//��Ʈ���� �̾� �׸���.
		Canvas c = new Canvas(created);
	
		for(int i = 0 ; i < len ; i++){
			//��Ʈ���� �̾���δ�.
			c.drawBitmap(bitmap[i], width*i, 0, p);
		}
		
		//���� ��Ʈ���� ��� ������ ����
		bitmapFrame = created; 
		
		return true;
	}
	
	/* ���� �ڽ��� ��Ʈ�� ������ �ѹ��� �����Ѵ�. */
	public int GetCurrentFrame(){
		return currentFrameNumber;
	}
	
	/* ���� ��Ʈ�� ������ ������ �����Ѵ�. */
	public Rect GetCurrentBitmapRect(){
		return new Rect(GetCurrentFrame()*width,0,(GetCurrentFrame()+1)*width,height);
	}
	
	/* �ڽ��� ��Ʈ���� �����Ѵ�. */
	public Bitmap GetCurrentBitmap(){
		return bitmapFrame;
	}
	
	/* �ڽ��� ��Ʈ���� width, height ������ �Ѱ��ش� */
	public Point GetSize(){
		return new Point(width,height);
	}
	
	/* �ڽ��� ��Ʈ�� �������� ���Ѵ�. */
	public Bitmap GetBitmapFrame(){
		return bitmapFrame;
	}
	
	/* �ڽ��� �������� ������Ʈ �Ѵ�. 
	 * @pre : �ʴ��� ��
	 * @post : �ڽ��� ���� �ð�*/
	public float Update(float timeDelta){
		currentTime += timeDelta;
		
		if(currentTime >= updateTime){
			//������ ����
			currentFrameNumber++;
			if(currentFrameNumber >= frameLength){//currentFramenumber�� frameLength ���� ������ �۾ƾ��Ѵ�. len�� 1���� �����ϱ� ����
				currentFrameNumber = 0;
			}
			currentTime -= updateTime;
		}
		return currentTime;
	}
}

