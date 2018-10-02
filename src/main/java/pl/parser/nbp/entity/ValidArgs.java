package pl.parser.nbp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ValidArgs {

    private String code;
    private Optional<LocalDate> startDate;
    private Optional<LocalDate> endDate;

    public ValidArgs() {
        this.startDate = Optional.empty();
        this.endDate = Optional.empty();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Optional<LocalDate> getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = Optional.of(startDate );
    }

    public Optional<LocalDate> getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = Optional.of(endDate);
    }

    public boolean isValid() {

        if ( startDate.isPresent() & endDate.isPresent() & ! code.isEmpty() ) {
            return getEndDate().get().isBefore(LocalDate.now()) || getEndDate().get().equals(LocalDate.now()) &
                    (getStartDate().get().isBefore(getEndDate().get()));
        } else
            return false;
    }

    public List<String> getDirNameFiles() {
        List<String> result = new ArrayList<>();
        if (startDate.isPresent() & endDate.isPresent()) {
            if (startDate.get().getYear()< endDate.get().getYear() ){
                for (int i = startDate.get().getYear(); i <= endDate.get().getYear(); i++) {
                    if (i != LocalDate.now().getYear()) {
                        result.add("dir" + i + ".txt");
                    } else {
                        result.add("dir.txt");
                    }
                }
            } else {
                if (startDate.get().getYear() == LocalDate.now().getYear())
                result.add( "dir.txt");
            }
        }

        return result;
    }

}
