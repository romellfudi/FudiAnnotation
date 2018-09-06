package com.romellfudi.fudi_annotation.sample.child_pk;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.sample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class DatosPersonalesFragment extends Fragment {
    final private Calendar calMax = Calendar.getInstance();
    final private Calendar calMin = Calendar.getInstance();
    private Calendar calFechaNacimiento;
    @ItemWidget
    private Spinner spiTipoDocIdentidad;
    @ItemWidget
    private EditText edtFechaNacimiento, edtEdad;

    public DatosPersonalesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_personales, container, false);
        Bind.Plug(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        View view = this.getView();
        super.onActivityCreated(savedInstanceState);
        calMax.add(Calendar.YEAR, -18);
        calMin.add(Calendar.YEAR, -100);
        calFechaNacimiento = (Calendar) calMax.clone();
        String[] arrTiposDocumentoIdentidad = {"DNI", "Pasaporte"};
        String[] arrCodigoTelefonoFijos = {"01", "02", "03"};
        String[] arrEstadoCivil = {"Casado", "Soltero", "Divorciado"};
        setAdapterToSpinnerWithArray(spiTipoDocIdentidad, arrTiposDocumentoIdentidad);
        edtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calFechaNacimiento.set(Calendar.YEAR, year);
                        calFechaNacimiento.set(Calendar.MONTH, monthOfYear);
                        calFechaNacimiento.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateEdtFechaNacimiento();
                    }
                }, 2015, 10, 1);
                datePickerDialog.getDatePicker().updateDate(calFechaNacimiento.get(Calendar.YEAR), calFechaNacimiento.get(Calendar.MONTH), calFechaNacimiento.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(calMax.getTimeInMillis());
                datePickerDialog.getDatePicker().setMinDate(calMin.getTimeInMillis());
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.setTitle("Seleccione una fecha");
                datePickerDialog.show();
            }
        });
        updateEdtFechaNacimiento();
    }

    private void updateEdtFechaNacimiento() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd 'de' MMMM, yyyy");
        edtFechaNacimiento.setText(simpleDateFormat.format(calFechaNacimiento.getTime()));
        Calendar calHoy = Calendar.getInstance();
        int diaHoy = calHoy.get(Calendar.DAY_OF_MONTH);
        int mesHoy = calHoy.get(Calendar.MONTH);
        int anioHoy = calHoy.get(Calendar.YEAR);
        int diaFecNac = calFechaNacimiento.get(Calendar.DAY_OF_MONTH);
        int mesFecNac = calFechaNacimiento.get(Calendar.MONTH);
        int anioFecNac = calFechaNacimiento.get(Calendar.YEAR);
        int edad = anioHoy - anioFecNac;
        if (mesHoy < mesFecNac) {
            edad -= 1;
        } else if (mesHoy == mesFecNac) {
            if (diaHoy < diaFecNac) {
                edad -= 1;
            }
        }
        edtEdad.setText(String.format("%d años", edad));
    }

    private void setAdapterToSpinnerWithArray(Spinner spin, String[] arrPosiblesValores) {
        ArrayAdapter<String> arrAdapCodTelFijo = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, arrPosiblesValores);
        arrAdapCodTelFijo.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(arrAdapCodTelFijo);
    }
}