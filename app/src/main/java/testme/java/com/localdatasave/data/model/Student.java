package testme.java.com.localdatasave.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by achau on 07-03-2018.
 */

public class Student implements Parcelable {
    private String name;
    private  byte current_class ;
    private int roll_no ;
    private boolean sex ;

    protected Student(Parcel in) {
        name = in.readString();
        current_class = in.readByte();
        roll_no = in.readInt();
        sex = in.readByte() != 0;
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

    public byte getCurrent_class() {
        return current_class;
    }

    public void setCurrent_class(byte current_class) {
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
        dest.writeByte(current_class);
        dest.writeInt(roll_no);
        dest.writeByte((byte) (sex ? 1 : 0));
    }
}
