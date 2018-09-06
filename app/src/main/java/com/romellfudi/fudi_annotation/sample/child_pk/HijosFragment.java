package com.romellfudi.fudi_annotation.sample.child_pk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.sample.R;

import java.util.ArrayList;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class HijosFragment extends Fragment {
    @ItemWidget
    private ListView lstHijos;

    public HijosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hijos, container, false);
        Bind.Plug(this, view);
        lstHijos.setDividerHeight(0);
        ArrayList<Hijo> arrHijos = new ArrayList<>();
        arrHijos.add(new Hijo("Andrea", 12));
        HijosArrayAdapter adapter = new HijosArrayAdapter(getActivity(), arrHijos);
        lstHijos.setAdapter(adapter);
        return view;
    }
}