package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action {
    @SerializedName("roakIdno")
    public long id;

    @SerializedName("ucitIdno")
    public long teacherId;

    @SerializedName("obsazeni")
    public long studentsCount;
}