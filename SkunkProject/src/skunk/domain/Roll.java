package skunk.domain;

public class Roll{
    private int dieValue1;
    private int dieValue2;
    
    public Roll(){
        this.dieValue1 = 0;
        this.dieValue2 = 0;
    }
    
    /**
     * Constructor for objects of class Roll
     */
    public Roll(int dieValue1, int dieValue2){
        this.dieValue1 = dieValue1;
        this.dieValue2 = dieValue2;
    }
    
    //Getter (Accessor) methods
    public int getDieValue1(){
    	return dieValue1;
    }
    public int getDieValue2(){
    	return dieValue2;
    }
    
    //Setter (Mutator) methods
    public void setDieValue1(int dieValue1){
    	this.dieValue1 = dieValue1;
    }
    public void setDieValue2(int dieValue2){
    	this.dieValue2 = dieValue2;
    }
    
    public void setDieValues(int dieValue1, int dieValue2){
        this.dieValue1 = dieValue1;
        this.dieValue2 = dieValue2;
    }
    
    /**
     * Method that checks if exactly 1 skunk was rolled
     * @return boolean true if exactly 1 skunk was rolled
     */
    public boolean isSkunk1(){
        if((dieValue1 == 1 && dieValue2 != 1 && dieValue2 != 2) || (dieValue1 != 1 && dieValue1 != 2 && dieValue2 == 1)){
            return true;
        }
        return false;
    }
    
    /**
     * Method that checks if one skunk and a deuce was rolled
     * @return boolean True if a skunk AND a deuce was rolled
     */
    public boolean isSkunk2(){
        if((dieValue1 == 1 && dieValue2 == 2) || (dieValue1 == 2 && dieValue2 == 1)){
            return true;
        }
        return false;
    }
    
    /**
     * Method that checks if two skunks were rolled
     * @return boolean True if two skunks were rolled
     */
    public boolean isSkunk3(){
        if(dieValue1 == 1 && dieValue2 == 1){
            return true;
        }
        return false;
    }
}