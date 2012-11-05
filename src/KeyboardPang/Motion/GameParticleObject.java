package KeyboardPang.Motion;

import Technology.Game.GameWorld;
import Technology.Game.Sprite.Sprite;
import Technology.Interface.IControllable;
import Technology.Interface.IDrawable;
import Technology.Interface.IUpdateable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * @brief 움직이는 설정을 사용자가 정의할수 잇는 파이클 오브젝트이다. 
 * 애니메이션 프레임과 이 오브젝트는 월드에 자동으로 등록된다. 
 * 다른 컨트롤들과 마찬가지로 이 오브젝트 또한 컨트롤 될수 있다. 
 * 하지만 반드시 SetOnActionContolls 메소드를 사용하여 컨트롤을 정의해줘야 한다. 
 * 이 오브젝트는 자동으로 월드에 추가되고 애니메이션이 다되면 자동으로 삭제한다. 따라서 안전성을 위해 상속은 불가능하며 오직 컨트롤만 지정해줄수 있다.
 * 이 오브젝트의 기본 피벗은 중앙이다.
 * 비트맵 애니메이션 파티클 오브젝트이다. 
 * */
public final class GameParticleObject extends Sprite implements IControllable{
	GameWorld world = null;
	IControllable controls = null;
	
	Matrix matrix = new Matrix();
	
	float dx,dy,dr,ds;//속도값
	float nx,ny,nr,ns;//현재 값
	
	
	/**
	 * @method 여기에서 world에 자동 추가된다.
	 * @param lifeTime : 비트맵이 배열이 아닐경우 이 인자는 보여질 시간이다.
	 * @param nx, ny : 이 오브젝트의 초기 위치
	 * @param ns : 이 오프젝트의 초기 사이즈
	 * @param dx, dy : 이 오브젝트의 속도
	 * @param rx, ry : 이 오브젝트의 회전속도
	 * @param rs : 이 오브젝트의 크기 증가 속도 
	 * */
	public GameParticleObject(float lifeTime,Bitmap bitmap,float nx,float ny,float ns,float dx,float dy,float dr,float ds) {
		super(lifeTime);
		world = GameWorld.getInstance();
		Bitmap[] bitmapArr = new Bitmap[1];
		bitmapArr[0] = bitmap;
		SetBitmapArray(bitmapArr);
		SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_CENTER);
		world.Add((IDrawable)this);
		world.Add((IUpdateable)this);
		world.Add((IControllable)this);
		
		/* 자신의 위치와 설정된 값들을 멤버 변수로 저장 */
		this.nx = nx;//위치 설정
		this.ny = ny;
		
		this.ns = ns;
		
		this.dx = dx;//속도값
		this.dy = dy;
		
		this.dr = dr;//회전값
		this.ds = ds;//크기값
		
		//자신의 위치 설정
		SetTranslate(nx, ny);
	}
	
	/**
	 * @method 여기에서 world에 자동 추가된다.
	 * @param animTime : 애니메이션될 속도.
	 * @param nx, ny : 이 오브젝트의 초기 위치
	 * @param ns : 이 오프젝트의 초기 사이즈
	 * @param dx, dy : 이 오브젝트의 속도
	 * @param rx, ry : 이 오브젝트의 회전속도
	 * @param rs : 이 오브젝트의 크기 증가 속도 
	 * */
	public GameParticleObject(float animTime,Bitmap[] bitmap,float nx,float ny,float ns,float dx,float dy,float dr,float ds) {
		super(animTime);
		world = GameWorld.getInstance();
		SetBitmapArray(bitmap);
		SetPivot(SPRITE_PIVOT.SPRITE_PIVOT_CENTER);
		world.Add((IDrawable)this);
		world.Add((IUpdateable)this);
		world.Add((IControllable)this);
		
		/* 자신의 위치와 설정된 값들을 멤버 변수로 저장 */
		this.nx = nx;//위치 설정
		this.ny = ny;
		
		this.ns = ns;
		
		this.dx = dx;//속도값
		this.dy = dy;
		
		this.dr = dr;//회전값
		this.ds = ds;//크기값
		
		//자신의 위치 설정
		SetTranslate(nx, ny);
	}

	/**
	 * @method 이 이 오브젝트의 컨트롤을 변경하고 싶다면 이 메소드를 사용해서 메소드를 변경하기 바란다.
	 * @param controls : 사용자 정의 컨트롤 인터페이스*/
	public void setOnActionControll(IControllable controls){
		this.controls = controls;
	}

	/**
	 * @method 루프를 다돌면 그리지않는다.
	 * */
	@Override
	public void Draw(Canvas canvas) {
		if(IsEnd() == false){
			super.Draw(canvas);
		}
	}
	/**
	 * @method 여기서 루프를 다돌면 월드에서 삭제한다.
	 * */
	@Override
	public float Update(float timeDelta) {
		//자신의 위치값들을 계속 더해준다.
		nx += dx * timeDelta;
		ny += dy * timeDelta;
		
		//자신의 회전값
		nr += dr * timeDelta;
		
		//자신의 크기값
		ns += ds * timeDelta;
		
		if(IsEnd() == false){
			SetScale(ns, ns);
			SetRotate(nr);
			SetTranslate(nx, ny);
			return super.Update(timeDelta);
		}else{
			world.Remove((IDrawable)this);
			world.Remove((IUpdateable)this);
			world.Remove((IControllable)this);
			return 0;
		}
	}

	@Override
	public void onActionUp(float x, float y) {
		if(controls != null) controls.onActionUp(x, y);
	}

	@Override
	public void onActionDown(float x, float y) {
		if(controls != null) controls.onActionDown(x, y);
	}

	@Override
	public void onActionMove(float x, float y) {
		if(controls != null) controls.onActionMove(x, y);
	}
}
