package pl.parser.nbp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class DirEntity {

    Character tableName;
    Integer tableNumber;
    LocalDate tableDate;
    String line;

    public DirEntity(String  table) {
        line = table.replaceAll("\\p{C}","");
        if (table.length()==11) {
            parseDirtable(table);
        } else {
            parseDirtable(table.substring(table.length()-11,table.length()));
        }
    }

    private void parseDirtable(String table) {
        tableName = table.charAt(0);
        tableNumber = Integer.parseInt(table.substring(1, 4));
        tableDate = LocalDate.of(Integer.parseInt(table.substring(5,7) ) + 2000 ,
                Integer.parseInt(table.substring(7,9)),
                Integer.parseInt(table.substring(table.length()-2, table.length())));
    }

}
