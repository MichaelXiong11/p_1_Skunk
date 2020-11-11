package skunk.domain;

public class Roll
{
    private int dieValue1;
    private int dieValue2;
    
    public Roll(int dieValue1, int dieValue2){
        this.dieValue1 = dieValue1;
        this.dieValue2 = dieValue2;
    }
    
    //Getting only one skunk
    public boolean isSkunk1(){
        if((dieValue1 == 1 && dieValue2 != 1 && dieValue2 != 2) || (dieValue1 != 1  && dieValue1 != 2 && dieValue2 == 1)){
            return true;
        }
        return false;
    }
    
    //Getting a skunk and a deuce
    public boolean isSkunk2(){
        if((dieValue1 == 1 && dieValue2 == 2) || (dieValue1 == 2 && dieValue2 == 1)){
            return true;
        }
        return false;
    }
    
    //Getting two skunks
    public boolean isSkunk3(){
        if(dieValue1 == 1 && dieValue2 == 1){
            return true;
        }
        return false;
    }
}