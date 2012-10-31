package Technology.Control;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import Technology.Game.Sprite.SpriteEx;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

/**
 * @brief 게임 내에서 사용될수 있는 Prograss bar이다.
 * 이 클래스는 기본 최대 퍼센테이지가 100이며, 사용자가 지정할수 있다. 출력모양또한 Mode를 통해서 지정할수가 있다.
 *  * */
public class GameProgessBar implements IDrawable, IUpdateable {
	public enum DrawMode{
		Persent,Current,CurrentAndMax
	}
	
	
	Rect rect;
	Rect margin;
	
	float myProgress = 0;
	float maxProgress = 0;
	
	DrawMode mode = DrawMode.Persent;
	SpriteEx bar = null;		//틀
	SpriteEx progress = null;	//진행바
	
	Paint paint = null;
	
	/**
	 * @brief 애니메이션 되는 프로그래스바를 생성한다.
	 * @param maxProgressSize : 이 프로그래스바의 최대값(보통 100)
	 * @param currentSize : 이 프로그래스 바의 현재 값(보통 0)
	 * @param barBitmap : 바의 틀 비트맵이다. 바는 무조건 progress보다 크므로 크기에 신경쓰지 않아도된다.
	 * @param progressBar : 체력 또는 마나등의 프로그래스바이다.
	 * @param barAnimTime : 바의 애니메이션 프레임이다.
	 * @param progressAnimTime : 프로그래스바의 애니메이션 프렝미이다.
	 * @param left,top,right,bottom : 실제 바의 위치이다.
	 * @param leftMargin,topMargin,rightMargin,bottomMargin : 프로그래스바와 바의 여백이다. 이 값이 크면클수록 프로그래스의 크기는 줄어든다.
	 * */
	public GameProgessBar(float maxProgressSize,float currentSize,Bitmap[] barBitmap,Bitmap[] progressBar,float barAnimTime,float progressAnimTime,int left,int top,int right,int bottom,
			int leftMargin,int topMargin,int rightMargin,int bottomMargin){
		maxProgress = maxProgressSize;
		myProgress = currentSize;
		
		bar = new SpriteEx(barAnimTime);
		progress = new SpriteEx(progressAnimTime);
		
		bar.SetBitmapArray(barBitmap);
		progress.SetBitmapArray(progressBar);
		
		bar.SetTranslate(0, 0);
		progress.SetTranslate(0, 0);
		
		SetFont(Style.STROKE,15,Color.BLACK);
		
		rect = new Rect(left,top,right,bottom);
		margin = new Rect(leftMargin,topMargin,rightMargin,bottomMargin);
	}
	
	/**
	 * 프로그래스 바의 숫자값을 더한다.
	 * */
	public void AddProgress(float deltaProgress)
	{
		myProgress += deltaProgress;
		if(myProgress > maxProgress){
			myProgress = maxProgress;
		}
	}
	
	/**
	 * 프로그래스바에 값을 설정한다.
	 * */
	public void SetProgress(float progress){
		if(progress > maxProgress){
			progress = maxProgress;
		}
		myProgress = progress;
	}
	
	/**
	 * 폰트를 지정한다.
	*/
	public void SetFont(Style style,int textSize,int color){
		paint = new Paint();
		paint.setStyle(style);
		paint.setColor(color);
		paint.setTextSize(textSize);
	}
	
	/**
	 * @brief 프로그래스바 안에 있는 글씨를 그리는 mode를 설정한다.
	 * persent : 퍼센테이지
	 * Current : 현재 값
	 * CurrentAndMax : 현재값과 맥스값 
	 * */
	public void SetMode(DrawMode mode)
	{
		this.mode = mode;
	}
	
	/**
	 * @brief 퍼센트를 숫자로 바꾼다.
	 * */
	public float PersentToCurrent(float per,float max)
	{
		return per/100 * max;
	}
	
	/**
	 * @brief 숫자를 퍼센트로 바꾼다.
	 * */
	public float CurrentToPersent(float x,float max)
	{
		return x/max * 100;
	}
	
	/**
	 * @brief x1,max1의 퍼센트를 구해서 max2의 값을 얻는다.
	 * */
	public float CurrentToCurrent(float x1,float max1,float max2){
		float curPer = CurrentToPersent(x1, max1);
		return PersentToCurrent(curPer, max2);
	}

	
	@Override
	public float Update(float timeDelta) {
		bar.Update(timeDelta);
		progress.Update(timeDelta);
		
		/* Test Code */
		//myProgress += timeDelta * 50;//Test
		//if(myProgress > maxProgress) myProgress = maxProgress;
		return 0;
	}
	
	@Override
	public void Draw(Canvas canvas) {
		//draw bar title
		bar.Draw(canvas,GetRect(),paint);
		/**
		 * @brief 이  코드는 프로그래스바의 너비값을 찾아서 이것을 Max값으로 설정한후, 현재 프로그래스바의 값을 프로그래스바 이미지의 너비로 설정하는 작업이다. 그리고 프로그래스바의 이미지를 그린다.
		 * 
		 * @code
			//프로그래스바의 Width를 구한다. maring.right를 두번 빼는 이유는 그릴떄 margin.right를 또 뺴주기 때문이다.
			int progressBitmapBarMax = GetRight() - margin.right*2 - GetLeft() - margin.left
			int progressWidth = (int)CurrentToCurrent(myProgress, maxProgress,progressBitmapBarMax);
			
			progress.Draw(canvas,
					new Rect(GetLeft() + margin.left,GetTop() + margin.top,progressWidth + margin.right + GetLeft() + margin.left ,GetBottom() - margin.bottom),
					paint);
		 * @endcode 
		 * */
		
		//프로그래스바의 Width를 구한다. 핵심코드.
		int progressBitmapBarMax = GetRight() - margin.right - GetLeft() - margin.left;
		int progressWidth = (int)CurrentToCurrent(myProgress, maxProgress,progressBitmapBarMax);
		progress.Draw(canvas,
				new Rect(GetLeft() + margin.left,GetTop() + margin.top,progressWidth + GetLeft() + margin.left ,GetBottom() - margin.bottom),
				paint);
		
		
		//draw progress mode
		switch(mode){
			case Persent:
				canvas.drawText(String.valueOf((int)CurrentToPersent(myProgress,maxProgress)) + "%"
						,(GetLeft() + GetRight())/2, (GetTop() + GetBottom())/2, 
						paint);
				break;
			case Current:
				canvas.drawText(String.valueOf((int)myProgress)
						,(GetLeft() + GetRight())/2, (GetTop() + GetBottom())/2, 
						paint);
				break;
			case CurrentAndMax:
				canvas.drawText(String.valueOf((int)myProgress) + "/" + String.valueOf((int)maxProgress)
						,(GetLeft() + GetRight())/2, (GetTop() + GetBottom())/2, 
						paint);
				break;
		}
	}
	
	public void SetLeft(int x)
	{
		rect.left = x;
	}
	public void SetRight(int x)
	{
		rect.right = x;
	}
	public void SetTop(int x)
	{
		rect.top = x;
	}
	public void SetBottom(int x)
	{
		rect.bottom = x;
	}
	public Rect GetRect()
	{
		return rect;
	}
	public int GetLeft()
	{
		return rect.left;
	}
	public int GetRight()
	{
		return rect.right;
	}
	public int GetTop()
	{
		return rect.top;
	}
	public int GetBottom()
	{
		return rect.bottom;
	}
}
