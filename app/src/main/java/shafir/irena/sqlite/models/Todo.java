package shafir.irena.sqlite.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irena on 21/07/2017.
 */

// model class

public class Todo implements Parcelable {
    private int id;
    private String mission;
    private String importance;


    // constructor for INSERT (new)
    public Todo(String mission, String importance) {
        this.mission = mission;
        this.importance = importance;
    }

    // constructor for SELECT (to take from DB)
    public Todo(int id, String mission, String importance) {
        this.id = id;
        this.mission = mission;
        this.importance = importance;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "mission='" + mission + '\'' +
                ", importance='" + importance + '\'' +
                ", id=" + id +
                '}';
    }
    public String getMission() {
        return mission;
    }
    public void setMission(String mission) {
        this.mission = mission;
    }
    public String getImportance() {
        return importance;
    }
    public void setImportance(String importance) {
        this.importance = importance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.mission);
        dest.writeString(this.importance);
    }

    protected Todo(Parcel in) {
        this.id = in.readInt();
        this.mission = in.readString();
        this.importance = in.readString();

    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
}
