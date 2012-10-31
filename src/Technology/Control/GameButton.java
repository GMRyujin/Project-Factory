package Technology.Control;

import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @brief 이 클래스는 게임의 기본적인 버튼 기능을 담는다. 버튼을 터치하면 색깔이 변하는 기능을 가지고 있다. 이벤트 처리기를 설정하여 이벤트 처리가 가능하다. 
 * @see setOnActionControl
 * @version Tested Safe 1.0
 * */
public class GameButton implements IDrawable, IUpdateable, IControllable {
	Rect rect;
	int nowColor,upColor,downColor;
	Paint paint;
	
	IControllable controls = null;
	
	public GameButton(int left,int top,int right,int bottom){
		SetDownColor(230, 230, 230);
		SetUpColor(200, 200, 200);
		nowColor = upColor;
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		rect = new Rect();
		rect.left = left;
		rect.top = top;
		rect.right = right;
		rect.bottom = bottom;
	}
	
	public void SetDownColor(int r,int g,int b){
		downColor = Color.rgb(r,g,b);
	}
	public void SetUpColor(int r,int g,int b){
		upColor = Color.rgb(r, g, b);
	}
	
	public Rect GetRect()
	{
		return rect;
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
	
	public void SetCurrentColor(int color){
		nowColor = color;
	}
	
	/**
	 * @brief 사용자 정의 터치 이벤트를 설정한다. 버튼의 이벤트 처리에 변화를 주고 싶다면 이 메소드를 사용하도록 한다.
	 * */
	public void setOnActionControl(IControllable control)
	{
		this.controls = control;
	}

	protected IControllable GetUserController()
	{
		return controls;
	}
	
	 /**
     * @method 현재 들어온 좌표가 현재 객체와 충돌하는가 ? 
     * */
    public boolean IsMe(int x,int y)
    {
    	int mx,my;
    	mx = (int)GetLeft();
    	my = (int)GetTop();
    	if(mx <= x && x <= GetRight()){
    		if(my <= y && y <= GetBottom()){
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean IsMeDown(int x,int y)
    {
    	return IsMe(x, y);
    }
    public boolean IsMeUp(int x,int y)
    {
    	return IsMe(x, y);
    }
    
    /**
     * @brief 현재 플레이어가 터치를 하지않으면 색깔은 다시 터치하지 않은 상태로 바꾸되, 버튼위에서  DOWN이 되어있었고, Up 이벤트가 발생하면 눌려진걸로 간주한다.
     * */
	@Override
	public void onActionUp(int x, int y) {
		if(IsMeUp(x, y)){
			//여기가 버튼이 눌려진 곳.
			if(GetUserController() != null) GetUserController().onActionUp(x, y);
		}
		SetCurrentColor(upColor);
	}

	/**
	 * @brief 현재 플레이어가 이 버튼에 Down을 했을때 호출되는 이벤트이다.
	 * */
	@Override
	public void onActionDown(int x, int y) {
		if(IsMeDown(x,y)){
			if(GetUserController() != null) GetUserController().onActionDown(x, y);
			SetCurrentColor(downColor);
		}
	}

	/**
	 * @brief 이 메소드는 버튼에서 눌려지고 움직였을때의 이벤트를 담당한다.*/
	@Override
	public void onActionMove(int x, int y) {
		if(IsMeDown(x, y)){
			if(GetUserController() != null) GetUserController().onActionMove(x, y);
		}
	}
	@Override
	public float Update(float timeDelta) {
		return 0;
	}

	@Override
	public void Draw(Canvas canvas) {
		paint.setColor(nowColor);
		canvas.drawRect(rect,paint);
	}
}
