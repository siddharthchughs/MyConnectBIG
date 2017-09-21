package tecnest.manage.myconnectbig.Pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Richie on 10-04-2017.
 */

public class Dashboard implements Parcelable {


    public Dashboard(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //    public int img;
    public String id;
    public String name;

    public Dashboard(){
        super();
    }

    protected Dashboard(Parcel in) {
    this();

        readFromParcel(in);

    }

    private void readFromParcel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }



    public static final Creator<Dashboard> CREATOR = new Creator<Dashboard>() {
        @Override
        public Dashboard createFromParcel(Parcel in) {

            Log.v("create from parcel","dashboard");
            return new Dashboard(in);
        }

        @Override
        public Dashboard[] newArray(int size) {
            return new Dashboard[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
