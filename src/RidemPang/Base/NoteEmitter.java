package RidemPang.Base;

import Technology.Interface.IUpdateable;

public class NoteEmitter implements IUpdateable{
	float currentTime = 0;
	IUpdateable updator = null;
	float maxTime = 0;
	
	public NoteEmitter(float rythem){
		maxTime = rythem;
	}
	
	public void setOnUpdater(IUpdateable u){
		updator = u;
	}
	
	@Override
	public float Update(float timeDelta) {
		currentTime += timeDelta;
		if(currentTime >= maxTime){
			currentTime = 0;
			if(updator != null)	updator.Update(timeDelta);
		}
		return timeDelta;
	}
}

