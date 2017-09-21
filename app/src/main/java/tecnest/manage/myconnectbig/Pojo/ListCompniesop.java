package tecnest.manage.myconnectbig.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 05-09-2017.
 */

public class ListCompniesop implements Parcelable{

    String id;
    String client_name;
    String client_company;
    String client_mobilenumber;


    public ListCompniesop(String client_name, String client_company, String client_mobilenumber) {
//        this.id = id;
        this.client_name = client_name;
        this.client_company = client_company;
        this.client_mobilenumber = client_mobilenumber;
    }

    protected ListCompniesop(Parcel in) {
     //   id = in.readString();
        client_name = in.readString();
        client_company = in.readString();
        client_mobilenumber = in.readString();
    }

    public static final Creator<ListCompniesop> CREATOR = new Creator<ListCompniesop>() {
        @Override
        public ListCompniesop createFromParcel(Parcel in) {
            return new ListCompniesop(in);
        }

        @Override
        public ListCompniesop[] newArray(int size) {
            return new ListCompniesop[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_company() {
        return client_company;
    }

    public void setClient_company(String client_company) {
        this.client_company = client_company;
    }

    public String getClient_mobilenumber() {
        return client_mobilenumber;
    }

    public void setClient_mobilenumber(String client_mobilenumber) {
        this.client_mobilenumber = client_mobilenumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    //    parcel.writeString(id);
        parcel.writeString(client_name);
        parcel.writeString(client_company);
        parcel.writeString(client_mobilenumber);
    }
}
