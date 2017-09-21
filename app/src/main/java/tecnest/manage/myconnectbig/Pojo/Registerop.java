package tecnest.manage.myconnectbig.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 15-09-2017.
 */

public class Registerop implements Parcelable{


    String firstname;
    String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    String username;
    String mobileno;
    String password;

    public Registerop(String firstname,String username, String mobileno, String password) {
        this.firstname = firstname;
//        this.lastname = lastname;
        this.username = username;
        this.mobileno = mobileno;
        this.password = password;
    }

    protected Registerop(Parcel in) {
        firstname = in.readString();
  //      lastname = in.readString();
        username = in.readString();
        mobileno = in.readString();
        password = in.readString();
    }

    public static final Creator<Registerop> CREATOR = new Creator<Registerop>() {
        @Override
        public Registerop createFromParcel(Parcel in) {
            return new Registerop(in);
        }

        @Override
        public Registerop[] newArray(int size) {
            return new Registerop[size];
        }
    };


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstname);
parcel.writeString(lastname);
        parcel.writeString(username);
        parcel.writeString(mobileno);
        parcel.writeString(password);
    }
}
