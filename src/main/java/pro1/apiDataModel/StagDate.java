package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StagDate {
    @SerializedName("value")
    public String value;

    public boolean isValid() {
        if (value == null || value.isBlank()) return false;
        try {
            toLocalDate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        return LocalDate.parse(value, formatter);
    }
}