package pl.parser.nbp;

import pl.parser.nbp.entity.Position;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CourseCalculator {

    private List<Position> listCourse;

    CourseCalculator(List<Position> pList) {
        this.listCourse = pList;
    }

    private Integer getNumberOfPositons() {
        return listCourse.size();
    }

    private BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal( Math.sqrt( value.doubleValue()));
        return x;
    }

    BigDecimal getMeanCourses(String type) {
        BigDecimal  sum = new BigDecimal(0);
        if( ! listCourse.isEmpty() ) {
            if (type.equals("buy")) {

                for (Position p : listCourse) {
                    sum = p.getBuy().add(sum);
                }
                return sum.divide(new BigDecimal(getNumberOfPositons()), BigDecimal.ROUND_DOWN);
            } else  {
                for (Position p : listCourse) {
                    sum = p.getSell().add(sum);
                }
                return sum.divide(new BigDecimal(getNumberOfPositons()), BigDecimal.ROUND_DOWN);
            }
        } else
            return new BigDecimal(0);
    }

    BigDecimal getStdDev(String type) {
        BigDecimal val = new BigDecimal(0);
        BigDecimal mean = getMeanCourses(type);
        if (! listCourse.isEmpty() )  {
            if (type.equals("buy")) {
                for (Position p : listCourse) {
                    val.add( (p.getBuy().subtract(mean)).pow(2) );
                }
            } else {
                for (Position p : listCourse) {
                    val = val.add( (p.getSell().subtract(mean)).pow(2) );
                }
            }
            return  sqrt(  val.divide( new BigDecimal(getNumberOfPositons()),BigDecimal.ROUND_DOWN) )
                         .setScale(4, RoundingMode.CEILING);
        }
        else return new BigDecimal(0);
    }


}

