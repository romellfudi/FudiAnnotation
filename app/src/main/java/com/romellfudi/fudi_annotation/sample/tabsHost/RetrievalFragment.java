package com.romellfudi.fudi_annotation.sample.tabsHost;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.sample.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2018
 * @since 1.0
 */
public class RetrievalFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final String TAG = RetrievalFragment.class.getSimpleName();

    Activity context;
    @ItemWidget(identifier = R.id.sp_deals_range)
    public Spinner spDealsRange;
    @ItemWidget(identifier = R.id.sp_days_range)
    public Spinner spDaysRange;
    @ItemWidget(identifier = R.id.btn_retrieval_search)
    public Button simulateButton;

    private ArrayAdapter<String> adapterDealsRange, adapterDaysRange;
    private ArrayList<String> listDealsRange, listDaysRange;
    private String randiaSelected, ransalSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("RetrievalFragment");
        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        PersonaController.getInstance().resetSearch();
        View view = inflater.inflate(R.layout.fragment_retrieval, container, false);
        ;
        Bind.Plug(this, view);

        listDaysRange = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.listDaysRange)));
        listDealsRange = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.listDealsRange)));

        adapterDaysRange = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listDaysRange);
        adapterDaysRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spDaysRange = (Spinner) view.findViewById(R.id.sp_days_range);
        spDaysRange.setAdapter(adapterDaysRange);
        spDaysRange.setOnItemSelectedListener(this);

        adapterDealsRange = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listDealsRange);
        adapterDealsRange.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spDealsRange = (Spinner) view.findViewById(R.id.sp_deals_range);
        spDealsRange.setAdapter(adapterDealsRange);
        spDealsRange.setOnItemSelectedListener(this);

//        simulateButton = (Button) view.findViewById(R.id.btn_retrieval_search);
        simulateButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_retrieval_search) {
//            CobranzaController.getInstance().executeSearch(context, randiaSelected, ransalSelected);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.equals(spDaysRange)) {
            int pos = spDaysRange.getSelectedItemPosition();
            switch (pos) {
                case 0:
                    randiaSelected = "01";
                    break;
                case 1:
                    randiaSelected = "02";
                    break;
                case 2:
                    randiaSelected = "03";
                    break;
                case 3:
                    randiaSelected = "04";
                    break;
                case 4:
                    randiaSelected = "05";
                    break;
            }
        } else if (parent.equals(spDealsRange)) {
            int pos = spDealsRange.getSelectedItemPosition();
            switch (pos) {
                case 0:
                    ransalSelected = "10";
                    break;
                case 1:
                    ransalSelected = "11";
                    break;
                case 2:
                    ransalSelected = "12";
                    break;
                case 3:
                    ransalSelected = "13";
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
