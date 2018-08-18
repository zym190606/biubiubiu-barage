package team.redrock.barrage.been;

import lombok.Data;

@Data
public class ResponseEntity<T> {
    private int status;
    private String success;
    private T data;

    public ResponseEntity(int status,String success,T data){
        this.status=status;
        this.success=success;
        this.data=data;
    }
}
