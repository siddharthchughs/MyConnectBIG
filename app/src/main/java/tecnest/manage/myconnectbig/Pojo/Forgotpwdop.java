package tecnest.manage.myconnectbig.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 18-09-2017.
 */

public class Forgotpwdop  implements Parcelable{

    private String fullname;
    private String email;
    private String phoneno;

    public Forgotpwdop(String fullname, String email, String phoneno) {
        this.fullname = fullname;
        this.email = email;
        this.phoneno = phoneno;
    }

    protected Forgotpwdop(Parcel in) {
        fullname = in.readString();
        email = in.readString();
        phoneno = in.readString();
    }

    public static final Creator<Forgotpwdop> CREATOR = new Creator<Forgotpwdop>() {
        @Override
        public Forgotpwdop createFromParcel(Parcel in) {
            return new Forgotpwdop(in);
        }

        @Override
        public Forgotpwdop[] newArray(int size) {
            return new Forgotpwdop[size];
        }
    };

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullname);
        parcel.writeString(email);
        parcel.writeString(phoneno);
    }
}
