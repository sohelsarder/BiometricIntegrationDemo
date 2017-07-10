package demo.biometricintegration.mpower.com.biometricintegrationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.simprints.libsimprints.Constants;
import com.simprints.libsimprints.Registration;
import com.simprints.libsimprints.SimHelper;

public class MainActivity extends AppCompatActivity {
    EditText etEnrollmentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEnrollmentStatus = (EditText) findViewById(R.id.etEnrollmentStatus);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Here we check the request + result code
        // We can pull the unique ID from LibSimprints by creating a registration
        // object from the returned Intent data, and retrieving the GUID.
        Registration registration =
                data.getParcelableExtra(Constants.SIMPRINTS_REGISTRATION);
        String uniqueId = registration.getGuid();

    }

    public void enrollClient (View view){
        SimHelper simHelper = new SimHelper("4b725213-d5e8-4d70-a4cc-98edbd8bdcb1", "mPower");
        Intent intent = simHelper.register("sohel");
        startActivityForResult(intent, 1); // 1 is your requestCode for the callback
        Log.d("MainActivity:","Enrolled Client Successfully!!");
        etEnrollmentStatus.setText("Enrollment Successful");
    }

    public void verifyClient (View view){

        Log.d("MainActivity:","Client Verification Successfully!!");
        etEnrollmentStatus.setText("Verification Successful");
    }

}
