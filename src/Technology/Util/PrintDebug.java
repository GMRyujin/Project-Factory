package Technology.Util;

import Technology.Game.GameWorld;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @brief 디버그에 필요한 모든 함수들을 모아놓은 클래스이다.
 * */
public class PrintDebug {
	
	static public void PrintWorldObjectNumberInfo(Canvas canvas,GameWorld world,int textColor,float size,float x,float y)
	{
		   /* Print World Info */
		   Paint text = new Paint();
		   text.setColor(textColor);
		   text.setTextSize(size);
		   int offset = 0;
		   canvas.drawText("World Update Number : " + world.GetUpdateListNum(), x, y + size * offset++ , text);
		   canvas.drawText("World Draw Number : " + world.GetDrawListNum(), x, y + size * offset++, text);
		   canvas.drawText("World Controll Number : " + world.GetControllListNum(), x, y + size * offset++, text);
		   canvas.drawText("World Collide Number : " + world.GetCollideListNum(), x, y + y + size * offset++, text);
		   canvas.drawText("World Dummy Update Number : " + world.GetDummyUpdateListNum(), x, y + size * offset++ , text);
		   canvas.drawText("World Dummy Draw Number : " + world.GetDummyDrawListNum(), x, y + size * offset++, text);
		   canvas.drawText("World Dummy Controll Number : " + world.GetDummyControllListNum(), x, y + size * offset++, text);
		   canvas.drawText("World Dummy Collide Number : " + world.GetDummyCollideListNum(), x, y + y + size * offset++, text);
	   }
	
}
