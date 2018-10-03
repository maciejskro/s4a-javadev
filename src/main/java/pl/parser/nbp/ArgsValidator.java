package pl.parser.nbp;

import pl.parser.nbp.entity.ValidArgs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class ArgsValidator {

    private String[] args;

    ArgsValidator(String[] args) {
        this.args = args;
    }

    ValidArgs getValidArgs() {
        ValidArgs result = new ValidArgs();
        if (args.length != 3 ) {
            return result;
        }

        if (args[0].matches("\\w{3}")) {
            result.setCode(args[0].toUpperCase());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
        if ( pattern.matcher(args[1]).matches()) {
            try {
                LocalDate date = LocalDate.parse(args[1],formatter);
                result.setStartDate(date);
            } catch (Exception e) {
                result.setStartDate(LocalDate.now());
            }
        }
        if ( pattern.matcher(args[2]).matches()) {
            try {
                LocalDate date = LocalDate.parse(args[2],formatter);
                result.setEndDate(date);
            } catch (Exception e) {
                result.setEndDate(LocalDate.now());
            }
        }

        return result;
    }
}
