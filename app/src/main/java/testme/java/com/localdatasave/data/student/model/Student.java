package testme.java.com.localdatasave.data.student.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by achau on 07-03-2018.
 */

public class Student implements Parcelable {
    private String name;
    private int current_class;
    private int roll_no;
    private boolean sex;

    public Student(Parcel in) {
        name = in.readString();
        current_class = in.readByte();
        roll_no = in.readInt();
        sex = in.readByte() != 0;
    }

    public Student(String name, int current_class, int roll_no, boolean sex) {
        this.name = name;
        this.current_class = current_class;
        this.roll_no = roll_no;
        this.sex = sex;
    }

    public Student() {

    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent_class() {
        return current_class;
    }

    public void setCurrent_class(int current_class) {
        this.current_class = current_class;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(current_class);
        dest.writeInt(roll_no);
        dest.writeByte((byte) (sex ? 1 : 0));
    }
}
