package Technology.Event;

import Technology.Interface.INameable;


/** @brief ClassInfo 클래스
 * 자신의 정보륾 보낸다.
 * 보내는 클래스는 반드시 INamedObject를 상속받아야한다.
 * */
public final class EventClassInfo {
        String clsName;
        INameable cls;
        
        public EventClassInfo(String name,INameable cls){
                this.clsName = name;
                this.cls = cls;
        }
        
        public String GetClassName(){
                return clsName;
        }
        
        public Object GetClass(){
                return cls;
        }
        
        public String GetId() {
                // TODO Auto-generated method stub
                return cls.GetId();
        }
}
