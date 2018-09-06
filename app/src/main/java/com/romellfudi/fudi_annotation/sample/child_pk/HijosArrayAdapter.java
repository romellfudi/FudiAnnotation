package com.romellfudi.fudi_annotation.sample.child_pk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.romellfudi.fudi_annotation.sample.R;

import java.util.ArrayList;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class HijosArrayAdapter extends ArrayAdapter<Hijo>{
    private ArrayList<Hijo> arrHijos;
    public HijosArrayAdapter(Context context, ArrayList<Hijo> arrHijos){
        super(context, -1);
        this.arrHijos = arrHijos;
    }
    @Override
    public int getCount(){
        return arrHijos.size() + 1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        int last = this.getCount() - 1;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(position == last){
            View rowView = inflater.inflate(R.layout.row_agregar_hijo, parent, false);
            Button btnAgregarHijo = (Button)rowView.findViewById(R.id.btnAgregarHijo);
            btnAgregarHijo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    arrHijos.add(new Hijo("", 0));
                    notifyDataSetChanged();
                }
            });
            return rowView;
        }else{
            View rowView = inflater.inflate(R.layout.row_nuevo_hijo, parent, false);
            final Hijo hijo = arrHijos.get(position);
            int edad = hijo.getEdad();
            final EditText edtNombre = (EditText)rowView.findViewById(R.id.edtNombre);
            edtNombre.setTag(String.format("%d", position));
            final EditText edtEdad = (EditText)rowView.findViewById(R.id.edtEdad);
            ImageButton imgBtnEliminar = (ImageButton)rowView.findViewById(R.id.imgBtnEliminar);
            imgBtnEliminar.setTag(String.format("%d", position));
            if(this.getCount() == 2){
                imgBtnEliminar.setVisibility(View.INVISIBLE);
            }
            imgBtnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int position = Integer.parseInt(v.getTag().toString());
                    arrHijos.remove(position);
                    HijosArrayAdapter.this.notifyDataSetChanged();
                }
            });
            edtNombre.setText(hijo.getNombre());
            if(edad == 0){
                edtEdad.setText("");
            }else{
                edtEdad.setText(String.format("%d año%s", edad, (edad == 1) ? "" : "s"));
            }
            edtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        int position = Integer.parseInt(v.getTag().toString());
                        Hijo hijoTemp = arrHijos.get(position);
                        hijoTemp.setNombre(edtNombre.getText().toString());
                    }
                }
            });
            edtEdad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        if(hijo.getEdad() > 0){
                            edtEdad.setText(String.format("%d", hijo.getEdad()));
                        }
                    } else {
                        if(edtEdad.length() > 0){
                            int nuevaEdad = Integer.parseInt(edtEdad.getText().toString());
                            if (nuevaEdad > 100) {
                                nuevaEdad = 100;
                            }
                            hijo.setEdad(nuevaEdad);
                            edtEdad.setText(String.format("%d año%s", nuevaEdad, (nuevaEdad == 1) ? "" : "s"));
                        }else{
                            hijo.setEdad(0);
                        }
                    }
                }
            });
            return rowView;
        }
    }
}