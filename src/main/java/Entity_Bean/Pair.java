package Entity_Bean;

public class Pair<L,R> {
    private L first;
    private R second;
    public Pair(L l, R r){
        this.first = l;
        this.second = r;
    }
    public L first(){ return first; }
    public R second(){ return second; }
    public void setL(L l){ this.first = l; }
    public void setR(R r){ this.second = r; }

    public boolean equals(Object o)
    {
        if ( o instanceof Pair) {
            Pair p = (Pair) o;
            return first.equals(p.first) && second.equals(p.second);
        }else
            return false;
    }

    public int hashCode()
    {
        return 7 * first.hashCode() + 13 * second.hashCode();
    }
}
