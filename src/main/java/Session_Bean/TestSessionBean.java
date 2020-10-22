package Session_Bean;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "TestSessionEJB")
public class TestSessionBean {
    int values = 0;
    public TestSessionBean() {
    }
    public void add(int e){
        values+=e;
    }
    public void minus(int e){
        values-=e;
    }
    public int getValues()
    {
        return values;
    }

}
