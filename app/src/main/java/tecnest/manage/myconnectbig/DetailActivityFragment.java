package tecnest.manage.myconnectbig;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private TextView tdCompnayDeatil;
    private TextView tdCompnayName;
    private TextView tdCompnayMobileNumber;


    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        SharedPreferences shareAccountInformation = getContext().getSharedPreferences("Detail", Context.MODE_PRIVATE);
        String companyName = shareAccountInformation.getString("companyname", "No value found").toString();
        String clientName = shareAccountInformation.getString("clientname", "No value found").toString();
        String clientNumber = shareAccountInformation.getString("clientnumber", "No value found").toString();

        tdCompnayDeatil = v.findViewById(R.id.textDeatil);
        tdCompnayName = v.findViewById(R.id.textName);
        tdCompnayMobileNumber = v.findViewById(R.id.textNumber);



        tdCompnayDeatil.setText(companyName);
        tdCompnayName.setText(clientName);
        tdCompnayMobileNumber.setText(clientNumber);


        return v;
    }
}
