package Technology.Game.Sprite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.R.bool;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

//�׽�Ʈ �ʿ�

/* @Sprite
 * å�� : �� Ŭ������ ��Ʈ���� �����Ͽ� ȸ��, ũ��, �ǹ� ����, �̵��� ����ϴ� Ŭ�����̴�. 
 * �ִϸ��̼� ���� ����ϰ��ִ�.
 * 
 * ����
 * 1. Sprite ��
 * 2. SetBitmapFrame
 * 3. SetPivot
 * */
public class Sprite implements IUpdateable, IDrawable{
	
	//��������Ʈ �ǹ��� ���� ����
	public enum SPRITE_PIVOT{
		SPRITE_PIVOT_CENTER,
		SPRITE_PIVOT_LEFTTOP,
		SPRITE_PIVOT_RIGHTBOTTOM
	}
	//��Ʈ�� ����
	Collection<Bitmap> bitmapList = new ArrayList<Bitmap>();
	Iterator<Bitmap> bitmapItor = null;
	int width,height;
	
	float sumTime = 0;
	float animTime = 1;	//1�ʴ� ��ȯ��. [�⺻��]
	
	boolean isEnd = false;
	
	
	/* ��ȯ�� ���Ǵ� ��� */
	Matrix trans = new Matrix();
	Matrix scale = new Matrix();
	Matrix res = new Matrix();
	
	
	Canvas cvs = new Canvas();
	float mPivotX,mPivotY;
	float mAngle = 0;
	float tx = 0,ty = 0;
	
	//�׷��� ��Ʈ��
	Bitmap currentBitmap = null;
	Bitmap alphaBitmap;			//�� ���� ��Ʈ��, �����ؼ� ����Ѵ�.
	Bitmap tempBitmap;			//�� ���� ��Ʈ�ʿ��� ����Ǽ� ���Ǵ� ��Ʈ��.
	
	public Sprite(float animTime){
		this.animTime = animTime;
		
	};
	
	/*
	 * ��Ʈ���� �迭�� ���´�.
	 * ��Ʈ���� �迭�� �����Ǹ� ��Ʈ���� �ڵ������� ���� List�� ����Ǿ� ����ȴ�.
	 * @pre : bitmap not null
	 * */
	public void SetBitmapArray(Bitmap[] bitmap){
		assert(bitmap == null);
		
		for(int i = 0 ; i < bitmap.length ; i++){
			bitmapList.add(bitmap[i]);
		}
		width = bitmap[0].getWidth();
		height = bitmap[0].getHeight();
		
		alphaBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		currentBitmap = bitmap[0];
		bitmapItor = bitmapList.iterator();
	}
	
	/**
	 * @date 2012년 10월 24일 버그리포트. 중요함
	 * @brief 이 함수는 반드시 필요하다. 이 함수에서는 생성한 비트맵의 메모리를 해제해준다. (recycling)
	 * 만일 이것을 하지않으면 프로그램은 반드시 죽으며, OutOfMemory Exception Error가 발생할것이다.
	 * 따라서 반드시 이 메소드를 구현해주어야 한다. 하지만 무슨이유에서인지  recycle() 메소드를 호출하면 갑자기 팅겨버린다.
	 * */
	protected void finalize() throws Throwable
	{
		//Log.v("Error","Final");
		//super.finalize();
		//alphaBitmap.recycle();
	}
	
	/*
	 * ��������Ʈ �� �̹����� �ǹ��� �����Ѵ�.
	 * */
	public void SetPivot(float x,float y){
		mPivotX = x;
		mPivotY = y;
	}
	
	public void SetPivot(SPRITE_PIVOT sp){
		switch(sp){
		case SPRITE_PIVOT_CENTER:
			SetPivot(width/2,height/2);
			break;
		case SPRITE_PIVOT_LEFTTOP:
			SetPivot(0,0);
			break;
		case SPRITE_PIVOT_RIGHTBOTTOM:
			SetPivot(width,height);
			break;
		}
	}
	
	public void SetRotate(float angle){
		mAngle = angle;
	}
	
	public void SetScale(float x,float y){
		scale.setScale(x, y);
	}
	
	public void SetTranslate(float x,float y){
		trans.setTranslate(x, y);
	}
	
	
	/**
	 * @method 애니메이션이 1번이상 루프를 완료했다면 true return
	 * */
	public boolean IsEnd()
	{
		return isEnd;
	}
	
	/*
	 * ��ȯ ��Ʈ������ ����Ѵ�.
	 * */
	public void SetTransMatrix(Matrix matrix){
		assert(matrix == null);
		trans.set(matrix);
	}
	
	/*
	 * ��Ʈ�� �������� �޴´�.
	 * @pre : bitmap not null, length : ��Ʈ�� ������ �ȿ� �ִ� �������� ����
	 * @post : ��Ʈ�� �������� �����Ͽ� �߶��� ��Ʈ�� �������� ���´�.
	 *  
	public void SetBitmapFrame(Bitmap bitmap,int length){
		assert(bitmap == null);
		
		
	}*/
	
	/* ��Ʈ�� �������� ������Ʈ�ϴ� �ڵ��̴�. (���⿡ �ִ� �ִϸ��̼� �ڵ�� ����ڰ� ���� �ð����� ������Ʈ �ȴ�.) */
	protected void AnimationUpdate(){
		//�ִϸ��̼� ������Ʈ �ڵ�
		if(sumTime >= animTime){
			sumTime -= animTime;
			
			if(bitmapItor.hasNext()){
				currentBitmap = bitmapItor.next();
			}else{
				bitmapItor = bitmapList.iterator();
				currentBitmap = bitmapItor.next();
				isEnd = true;//다음 비트맵이 없다면 false리턴
			}
		}
	}
	
	/* ��Ʈ�� �������� ���� ��Ʈ���� ȸ��, �̵�, ũ�⺯ȯ�� �Ѵ�. */
	protected void TranslateBitmap(){
		/* �� : ȸ�� -> ũ������ -> �̵� */
		//���ο� ��Ʈ���� ����� �� ��Ʈ���� ȸ���Ų��.
		tempBitmap = alphaBitmap.copy(Config.ARGB_8888, true);
		this.cvs = new Canvas();
		this.cvs.setBitmap(tempBitmap);
		this.cvs.rotate(mAngle,mPivotX,mPivotY);	//�ǹ��� ��ǥ�� ��� ȸ��
		this.cvs.drawBitmap(currentBitmap,0 ,0 , null);
		
		//ȸ���Ų ��Ʈ���� �׸���. (��ȯ ��� =translate * scale)
		res.setConcat(trans,scale);		
	}
	
	public void Draw(Canvas canvas) {
		assert(currentBitmap == null);
		
		if(tempBitmap != null){
			canvas.drawBitmap(tempBitmap, res ,null);
		}
	}

	public float Update(float timeDelta) {
		sumTime += timeDelta;
		
		//��Ʈ�� ������ ������Ʈ
		AnimationUpdate();
		
		//��Ʈ���� ȸ��, �̵�, ũ�⺯ȯ�� ����Ѵ�.
		TranslateBitmap();
		return sumTime;
	}

}

