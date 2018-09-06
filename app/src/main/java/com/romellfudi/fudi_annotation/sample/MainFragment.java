package com.romellfudi.fudi_annotation.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.GroupButtonSeleted;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.OnClick;
import com.romellfudi.fudi_annotation.OnFocus;
import com.romellfudi.fudi_annotation.OnLongClick;
import com.romellfudi.fudi_annotation.RegularExpression;
import com.romellfudi.fudi_annotation.SimpleTabHost;
import com.romellfudi.fudi_annotation.SimpleViewPaper;
import com.romellfudi.fudi_annotation.SpinnerSelected;
import com.romellfudi.fudi_annotation.TabHostWidget;
import com.romellfudi.fudi_annotation.ViewPagerWidget;
import com.romellfudi.fudi_annotation.sample.child_pk.DatosPersonalesFragment;
import com.romellfudi.fudi_annotation.sample.child_pk.HijosFragment;
import com.romellfudi.fudi_annotation.sample.tabsHost.ConfigurationFragment;
import com.romellfudi.fudi_annotation.sample.tabsHost.RetrievalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class MainFragment extends Fragment {

    @ItemWidget(identifier = R.id.toolbar)
    public Toolbar toolbar;
    @ItemWidget(className = FloatingActionButton.class)
    public FloatingActionButton fab;
    @ItemWidget
    ListView lv_items;
    @ItemWidget(identifier = R.id.tl_main)
    @ViewPagerWidget(
            idViewPaper = R.id.vp_main,
            colorSelected = R.color.colorCasiNegro100,
            colorUnselected = R.color.colorCasiNegro70,
            values = {
                    @SimpleViewPaper(c = DatosPersonalesFragment.class, t = "DATOS PERSONALES", d = R.drawable.ic_datos_personales_50dp),
                    @SimpleViewPaper(c = HijosFragment.class, t = "HIJOS", d = R.drawable.ic_child_friendly_black_24dp)
            })
    TabLayout tl_main;
    @ItemWidget
    @TabHostWidget(
            tabcontent = R.id.tabcontent,
            value = {
                    @SimpleTabHost(c = RetrievalFragment.class, t = "", d = R.drawable.buscar_cliente_icon),
                    @SimpleTabHost(c = ConfigurationFragment.class, t = "", d = R.drawable.config_icon)
            })
    FragmentTabHost tabHost;
    @ItemWidget
    RadioGroup radio_group;
    @ItemWidget
    Spinner my_spinner;

    ArrayList<ObjectItem> objectItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        Bind.Plug(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        objectItems = new ArrayList<ObjectItem>();
        objectItems.add(new ObjectItem("onLongClick 1"));
//        objectItems.add(new ObjectItem("onLongClick 2"));
        lv_items.setAdapter(new AdaptarItem(getActivity(), R.layout.row_item, objectItems));
        return view;
    }

    @OnClick(identifier = R.id.btn_apretar)
    private void info() {
        Toast.makeText(getActivity(), "Puedo!", Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(identifier = R.id.btn_apretar)
    private void infoMaximo() {
        Toast.makeText(getActivity(), "Puedo Muchísimo!", Toast.LENGTH_SHORT).show();
    }

    @RegularExpression(identifier = R.id.et_ip, maxLeght = 15,
            regularExpression = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            isIPAdress = true)
    private void FormatoIP(List<Object> objects) {
        if (objects.size() > 0) {
            String finalValue = (String) objects.get(0);
            Toast.makeText(getActivity(), "->" + finalValue, Toast.LENGTH_SHORT).show();
        }
//        password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @OnFocus(identifier = R.id.et_world, viewRequestFocus = R.id.et_ip, needParameters = true)
    private void cuandoEsteEnfocado(EditText v, boolean b) {
        if (b)
            Toast.makeText(getActivity(), "Ingrese info", Toast.LENGTH_SHORT).show();
        else
            v.setText("Ha ingresado :" + v.getText().toString());
    }

//    @OnFocus(identifier = R.id.et_world, viewRequestFocus = R.id.et_ip, needParameters = false)
//    private void cuandoEsteEnfocado() {
//            Toast.makeText(getActivity(), "Ingrese info", Toast.LENGTH_SHORT).show();
//    }

    @SpinnerSelected(identifier = R.id.my_spinner, arrayStringId = R.array.lista_pais,
            typeSpinner = android.R.layout.simple_spinner_item)
    private void SeleccionPaises(String paisSelected, int position) {
        Toast.makeText(getActivity(), position + "-" + paisSelected, Toast.LENGTH_SHORT).show();
    }

    @GroupButtonSeleted(identifier = R.id.radio_group)
    private void ChangeYesNo(RadioGroup radioGroup, RadioButton radioButton) {
        Toast.makeText(getActivity(), "valor=" + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
