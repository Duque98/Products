import java.util.*; 
import java.util.Comparator; 
/**
 * Write a description of class PointsComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 public class PointsComparator implements Comparator<Comments>
{
    /**
     * Constructor for objects of class PointComparator
     */
    public int compare(Comments c1, Comments c2)
    {
        if(c1.getPoints() == c2.getPoints()){
            return 0; 
        }else if(c1.getPoints() < c2.getPoints()){
            
            return 1; 
        }
        else{
            return -1; 
        }
    }

}
