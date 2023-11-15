
package datastructproject3;

/**
 *
 * @author Mason
 */
public class Printjob implements Comparable<Printjob> {
    String name;
    int numpages;
    int user_priority;
    int job_priority = user_priority * numpages;
    String flag;
    
    public Printjob (String n, int np, int up, String fl){
    name = n;
    numpages = np;
    user_priority = up;
    flag = fl;
    }
    
    @Override public int compareTo(Printjob x){
        int thisPriority = this.user_priority * this.numpages;
        int otherPriority = x.user_priority * x.numpages;
        return otherPriority - thisPriority;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPages(){
        return numpages;
    }
    
    public int getUserPriority(){
        return user_priority;
    }
    
    public int getJobPriority(){
        return job_priority;
    }
    
    public String getFlag(){
        return flag;
    }
}


