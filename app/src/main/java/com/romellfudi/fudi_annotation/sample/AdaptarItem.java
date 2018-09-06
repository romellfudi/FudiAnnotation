package com.romellfudi.fudi_annotation.sample;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.romellfudi.fudi_annotation.Bind;
import com.romellfudi.fudi_annotation.ItemWidget;
import com.romellfudi.fudi_annotation.OnLongClick;
import com.romellfudi.fudi_annotation.RegularExpression;

import java.util.List;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class AdaptarItem extends ArrayAdapter<ObjectItem> {

    final Context context;
    int res;
    List<ObjectItem> items;

    public AdaptarItem(Context context, int resource, List<ObjectItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.res = resource;
        this.items = objects;
    }

    @Override
    public ObjectItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(res, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ObjectItem item = getItem(position);
        if (item != null) {
            viewHolder.tv_data.setText(item.getData());
            viewHolder.et_ip.setText("192.168.1.1");
            viewHolder.et_pass.setText("12345");
        }
        return convertView;
    }

    class ViewHolder {
        @ItemWidget
        TextView tv_data;
        @ItemWidget
        EditText et_ip;
        @ItemWidget
        EditText et_pass;

        ViewHolder(View view) {
            Bind.Plug(this, view);
        }

        @RegularExpression(identifier = R.id.et_ip, maxLeght = 15,
                regularExpression =
                        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
        private void ValidarIP(List<Object> objects) {
            if (objects.size() > 0) {
                Toast.makeText(context, "Nueva información :" + objects.get(0), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(context, "IP mal ingresada", Toast.LENGTH_SHORT).show();
        }

        @RegularExpression(identifier = R.id.et_pass, maxLeght = 3,
                regularExpression = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
                inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD)
        private void PassValidate(List<Object> objects) {
            if (objects.size() > 0) {
                Toast.makeText(context, "Password acceptable!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Rewrite password", Toast.LENGTH_SHORT).show();
            }
        }

        @OnLongClick(identifier = R.id.tv_data)
        private void mostrarInfo() {
            Toast.makeText(context, "onLongClick :" + tv_data.getText(), Toast.LENGTH_SHORT).show();

        }

//        @OnFocus(identifier = R.id.et_pass, viewRequestFocus = R.id.et_ip)
//        private void cuandoEsteEnfocadoPass() {
//            Toast.makeText(context, "3 digits", Toast.LENGTH_SHORT).show();
//        }
    }
}
