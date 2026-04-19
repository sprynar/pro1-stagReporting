package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("ucitIdno")
    public Long teacherId;

    @SerializedName("obsazeni")
    public String studentsCount; // String in the JSON, e.g. "0", "17"
}