package pl.parser.nbp;

import pl.parser.nbp.entity.DirEntity;
import pl.parser.nbp.entity.ValidArgs;
import pl.parser.nbp.web.NbpConnector;

import java.time.LocalDate;
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
                                          .filter(dirEntity -> dirEntity.getTableDate().isAfter(validArgs.getStartDate().get()))
                                          .filter(dirEntity -> dirEntity.getTableDate().isBefore(validArgs.getEndDate().get()))
                                         .collect(Collectors.toList());
        System.out.println(nowaLista);
    }

}
