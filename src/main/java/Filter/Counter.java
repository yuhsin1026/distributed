package Filter;

public class Counter {
    int number=0;
    public Counter(){

    }
    public int inccounter() {
        number++;
        return number;
    }
    public int getcounter(){
        return number;
    }
}
