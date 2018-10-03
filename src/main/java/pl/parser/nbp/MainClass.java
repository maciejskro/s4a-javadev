package pl.parser.nbp;

import pl.parser.nbp.entity.CourseTable;
import pl.parser.nbp.entity.DirEntity;
import pl.parser.nbp.entity.Position;
import pl.parser.nbp.entity.ValidArgs;
import pl.parser.nbp.web.NbpConnector;

import javax.xml.bind.JAXBException;
import java.math.MathContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainClass {

    private static NbpConnector nbpConnector;

    private static final String nbpDir = "http://www.nbp.pl/kursy/xml/";

    public static void main(String[] args) {

        ValidArgs validArgs = (new ArgsValidator(args)).getValidArgs();
        if ( ! validArgs.isValid()) {
            System.out.println("Wrong number of atributes or wrong atributes");
            System.out.println("pl.parser.npb.MainClass <currency code> <start date> <end date>");
            System.exit(1);
        }

        // parse dir*.txt files
        List<String> dirlist = new ArrayList<>();
        for (String i : validArgs.getDirNameFiles()) {
            nbpConnector =new NbpConnector(nbpDir+i );
            dirlist.addAll(Arrays.asList(nbpConnector.getResponse("text/plain").split("\r\n")));
        }

        List<DirEntity> nowaLista = dirlist.stream()
                                         .map(new Function<String, DirEntity>() {
                                                    public DirEntity apply(String s) {
                                                            return new DirEntity(s);
                                                    }
                                            })
                                          .filter(dirEntity -> dirEntity.getTableName().equals('c'))
                                          .filter(dirEntity -> dirEntity.getTableDate().isAfter(validArgs.getStartDate().get().minusDays(1L) ))
                                          .filter(dirEntity -> dirEntity.getTableDate().isBefore(validArgs.getEndDate().get().plusDays(1L) ))
                                         .collect(Collectors.toList());

        // get list course table from xml
        XmlValidator xmlValidator = new XmlValidator();
        List<Position> pList = new ArrayList<>();
        // this might be used lambda, but I decided  for loop because I thint there are less line of code :)
        try {
            for(DirEntity de : nowaLista) {
                pList.add(xmlValidator.getSinglePositonFromTable( new URL(nbpDir+de.getLine()+".xml"), validArgs.getCode()).get() );
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        CourseCalculator courseCalculator = new CourseCalculator(pList);
        System.out.println(courseCalculator.getMeanCourses("buy"));
        System.out.println(courseCalculator.getStdDev("sell"));
    }



}
