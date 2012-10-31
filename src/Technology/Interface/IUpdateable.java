package Technology.Interface;


public interface IUpdateable {
        /* 
         * Update 함수
         * @pre : timeDelta는 초단위 시간이다.
         * @post : 자신의 상태를 업데이트하고, 현재 자신의 누적시간을 반환한다.  
         * */
        float Update(float timeDelta);
}
