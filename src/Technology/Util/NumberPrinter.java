package Technology.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import Technology.Game.GameWorld;
import Technology.Game.Sprite.Sprite.SPRITE_PIVOT;
import Technology.Game.Sprite.SpriteEx;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;

/**
 * @brief 숫자를 출력해주는 객체이다. 여러개의 객체를 이름을 통해 가져와서 사용할수 있다. 피벗 위치는 TOP_LEFT 이다.
 * */
public class NumberPrinter implements IUpdateable, IDrawable {
	IUpdateable updater = null;
	
	SpriteEx[] sp = new SpriteEx[10];
	float x,y,width,height;
	int num = 0;
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	static Map<String,NumberPrinter> map = new HashMap<String,NumberPrinter>();
	/**
	 * @brief 이름을 통해 해당 넘버프린터를 가져온다.
	 * */
	public static NumberPrinter getInstance(String name){
		NumberPrinter printer = map.get(name);
		if(printer == null){
			map.put(name, new NumberPrinter());
			return map.get(name);
		}else{
			return printer;
		}
	}
	
	/**
	 * @brief 이름을 통해 해당 넘버프린터를 가져온다. 가져올떄 출력위치와 넘버하나의 크기를 가져올수있따.
	 * */
	public static NumberPrinter getInstance(String name,float x,float y,float width,float height){
		NumberPrinter printer = map.get(name);
		if(printer == null){
			map.put(name, new NumberPrinter());
			printer = map.get(name);
			printer.SetPosition(x, y);
			printer.SetSize(width, height);
			return printer; 
		}else{
			printer.SetPosition(x, y);
			printer.SetSize(width, height);
			return printer;
		}
	}
	
	private NumberPrinter(){
		SetPosition(0,0);
		SetSize(10,10);
	}
	
	public void setOnUpdater(IUpdateable u){
		updater = u;
	}
	
	public void AddWorld()
	{
		GameWorld world = GameWorld.getInstance();
		world.Add((IUpdateable)this);
		world.Add((IDrawable)this);
	}
	
	public void RemoveWorld()
	{
		GameWorld world = GameWorld.getInstance();
		world.Remove((IUpdateable)this);
		world.Remove((IDrawable)this);
	}
	
	/**
	 * @brief 반드시 넘버 설정을 하여 출력하기 바란다.
	 * */
	public void SetPrintNumber(int num){
		this.num = num;
	}
	
	/**
	 * @brief 자신의 숫자를 반환한다.
	 * */
	public int GetPrintNumber(){
		return num;
	}
	
	private NumberPrinter(float x,float y,float width,float height){
		SetPosition(x,y);
		SetSize(width,height);
	}
	
	public void SetNumberImage(int num,int animTime,String loaderKey){
		sp[num] = new SpriteEx(animTime);
		sp[num].SetBitmapArray(BitmapLoader.getInstance().get(loaderKey));
		sp[num].SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_LEFTTOP);
	}
	
	public void SetSize(float width,float height)
	{
		this.width = width;
		this.height = height;
	}
	
	/* 출력위치를 정한다. */
	public void SetPosition(float x,float y)
	{
		this.x = x;
		this.y = y;
	}
	
	Stack<Integer>	stack = new Stack<Integer>();//그려질 인덱스 번호가 있는 콜렌션 프레임워크
	
	@Override
	public void Draw(Canvas canvas) {
		// TODO 숫자들을 그린다.
		int stackSize = stack.size();
		for(int i = 0 ; i < stackSize ; i++){
			int stackPopVal = stack.pop();
			//Log.v("Debug","" + sp[stackPopVal]);
			if(sp[stackPopVal] != null) sp[stackPopVal].Draw(canvas,new RectF((x+i*width),y,width+(x+i*width),height+y),paint);
		}
	}
	@Override
	public float Update(float timeDelta) {
		// TODO 숫자들을 업데이트 한다.
		int dividedVal;	//나뉘어진 값
		int tempNum = this.num;//원본값을 저장하기 위해 사용됨.
		
		do{
			dividedVal = tempNum % 10;
			tempNum = tempNum / 10;
			stack.push(dividedVal);
		}while(tempNum != 0);
		
		if(updater != null)	updater.Update(timeDelta);
		
		for(int i = 0 ; i < 10 ; i++){
			if(sp[i] != null)	sp[i].Update(timeDelta);
		}
		return 0;
	}
}
