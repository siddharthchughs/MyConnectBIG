package tecnest.manage.myconnectbig.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richie on 05-09-2017.
 */

public class Categoryop implements Parcelable{

    String category_id;
    String Category_name;

    protected Categoryop(Parcel in) {
        category_id = in.readString();
        Category_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category_id);
        dest.writeString(Category_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Categoryop> CREATOR = new Creator<Categoryop>() {
        @Override
        public Categoryop createFromParcel(Parcel in) {
            return new Categoryop(in);
        }

        @Override
        public Categoryop[] newArray(int size) {
            return new Categoryop[size];
        }
    };

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public Categoryop(String category_id, String category_name) {
        this.category_id = category_id;
        Category_name = category_name;
    }


}
