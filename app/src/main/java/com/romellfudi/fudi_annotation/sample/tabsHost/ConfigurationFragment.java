package com.romellfudi.fudi_annotation.sample.tabsHost;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.RegularExpression;
import com.romellfudi.fudi_annotation.sample.R;

import java.util.List;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class ConfigurationFragment extends Fragment {

    public static String TAG = ConfigurationFragment.class.getSimpleName();

    @ItemWidget(identifier = R.id.et_server_ip)
    public EditText etIP;
    @ItemWidget(identifier = R.id.et_time_out)
    public EditText etTimeOUT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ConfigurationFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuration, container, false);
        Bind.Plug(this, view);

//        etIP.setText(ConfigurationController.getInstance().getConfig().getIdServidor());
//        etTimeOUT.setText(String.valueOf(ConfigurationController.getInstance().getConfig().getTimeOut()));

        etTimeOUT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // get backup
                    etTimeOUT.setText(String.valueOf(var));
                } else {
                    int newTime = Integer.parseInt(etTimeOUT.getText().toString().trim());
                    if (etTimeOUT.length() > 0) {
                        if (newTime > 99)
                            newTime = 99;
                        else if (newTime < 1)
                            newTime = 1;
                        // set backup
                        var = newTime;
                        etTimeOUT.setText(String.format("%d segundo%s", newTime, (newTime == 1) ? "" : "s"));
                    } else {
                        // set backup
                        var = newTime;
                        etTimeOUT.setText(String.valueOf(var));
                    }
                }
            }
        });

        return view;
    }

    int var = 1;

    @RegularExpression(identifier = R.id.et_server_ip, maxLeght = 15,
            regularExpression = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            isIPAdress = true)
    private void FormatoIP(List<Object> objects) {
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        etTimeOUT.requestFocus();
        if (objects.size() > 0) {
            String finalValue = (String) objects.get(0);
            Toast.makeText(getActivity(), "->" + finalValue, Toast.LENGTH_SHORT).show();
        }
//        password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @RegularExpression(identifier = R.id.et_time_out, maxLeght = 11,
            regularExpression = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED)
    private void d(List<Object> object) {
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        getActivity().onSearchRequested();
    }

    private boolean CorrrectTimeOut(int newTime) {
        return (newTime > 0);
    }


}
