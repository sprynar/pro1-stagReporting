package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BooksList {
    @SerializedName("literatura")  // ← likely key, check the raw JSON if it fails
    public List<Book> items;
}