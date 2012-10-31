package Technology.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * @brief 비트맵을 로드하고 불러올수있는 기능이 들어있는 클래스이다.
 * 절대로 전역변수에 저장하지 않기를 바란다.
 * */
public class BitmapLoader {
	private static BitmapLoader me = null;
	Resources res = null;
	Map<String,ArrayList<Bitmap>> map = new HashMap<String,ArrayList<Bitmap>>();

	
	public static BitmapLoader getInstance(){
		if(me == null){
			me = new BitmapLoader();
		}
		return me;
	}
	
	/**
	 * @brief 비트맵 로더를 초기화 한다.
	 * */
	public void init(Resources res){
		this.res = res;
	}
	
	/**
	 * @brief 비트맵을 로드한다.
	 * @param tag : 저장할 태그명
	 * @param resource_id : 리소스 번호
	 * */
	public void put(String tag,int resource_id){
		ArrayList<Bitmap> list = null;
		if(map.containsKey(tag) == false){//만약 리스트가 없다면 만들어서 맵에 넣는다.
			list = new ArrayList<Bitmap>();
			map.put(tag, list);
		}else{//만약 있다면 리스트를 구한다.
			list = map.get(tag);
		}
		//그리고 리스티에 데이터를 추가한다.
		list.add(BitmapFactory.decodeResource(res, resource_id));
	}
	
	/**
	 * @brief 저장된 비트맵을 가져온다.
	 * */
	public Bitmap[] get(String tag){
		ArrayList<Bitmap> list = map.get(tag);
		Bitmap[] bitmap = new Bitmap[0];
		bitmap = list.toArray(bitmap);
		return bitmap;
	}
	
	private BitmapLoader(){};
}
	
	
