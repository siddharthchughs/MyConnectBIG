package tecnest.manage.myconnectbig;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static tecnest.manage.myconnectbig.R.id.clientAddress;

/**
 * A placeholder fragment containing a simple view.
 */
public class AboutUsActivityFragment extends Fragment {

    private int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private LinearLayout llCall;

    public AboutUsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about_us, container, false);
//        llCall = (LinearLayout) v.findViewById(R.id.phonecall);

        TelephonyManager tMgr = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();

        Toast.makeText(getContext(), mPhoneNumber, Toast.LENGTH_SHORT).show();
        DisplayCall();

        // Intent callIntent = new Intent(Intent.ACTION_CALL);
//        clientPhone = in.getStringExtra("phonenumber");
        //      callIntent.setData(Uri.parse("tel:91" + clientAddress));

        //   startActivity(callIntent);
        return v;



}

    public void DisplayCall() {
        if (isReadStorageAllowed()) {
            //If permission is already having then showing the toast

            //Existing the method with return
            return;
        }
        checkAndRequestPermissions();

    }

    private boolean checkAndRequestPermissions() {
//        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_COARSE_LOCATION);
        int locationPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);

//        int locationFinelocation = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
//        if (locationFinelocation != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
//        }


        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    //    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);

//        int resultFineLocation = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
//        if (resultFineLocation == PackageManager.PERMISSION_GRANTED)
//            return true;

        //If permission is not granted returning false
        return false;
    }


}
