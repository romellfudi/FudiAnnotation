package com.romellfudi.fudi_annotation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bind class for transfer preProgram task for each annotation
 *
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class Bind {

//    public static void Plug(final Object context){
//        if (context instanceof Activity){
//           Plug((Activity)context);
//        } else if (context instanceof Dialog){
//
//        }
//
//    }

    public static void Plug(final Activity context) {
        Plug(context, context.getWindow().getDecorView().getRootView());
    }

    @SuppressLint("ResourceType")
    public static void Plug(final Object objectViewHolder, final View view) {

        for (Field field : (objectViewHolder).getClass().getDeclaredFields()) {
            field.setAccessible(true); // if false, is possible throw IlleResBindgalAccessException
            int myId = View.NO_ID;
            if (field.isAnnotationPresent(ItemWidget.class)) {
                ItemWidget ItemWidget = field.getAnnotation(ItemWidget.class);
                myId = (ItemWidget.identifier() != View.NO_ID) ? ItemWidget.identifier() :
                        view.getResources().getIdentifier(field.getName(), "id", view.getContext().getPackageName());
                Class myClass = (ItemWidget.className() != void.class) ? ItemWidget.className() :
                        field.getType();
                try {
                    if (myId != View.NO_ID)
                        field.set(objectViewHolder, myClass.cast((view.findViewById(myId))));
                    else
                        throw new NullPointerException();
                } catch (IllegalAccessException e) { // depreciated because setAccessible(true)
                    Log.e(e.getClass().getSimpleName(), "The value of the field " + field.toString() + " can't accessed.");
                } catch (ClassCastException e) {
                    Log.e(e.getClass().getSimpleName(), "The class of the field " + field.getName() + " can't cast.");
                    if (ItemWidget.className() != void.class)
                        Log.e(e.getClass().getSimpleName(), "Can't convert " +
                                myClass + " to " + view.findViewById(myId).getClass().getName());
                    else
                        Log.e(e.getClass().getSimpleName(), "Can't convert " +
                                view.findViewById(myId).getClass().getName() + " to " + myClass);
                } catch (NullPointerException e) {
                    Log.e(e.getClass().getSimpleName(), "The value of the field " + field.getName() + " can't be NULL.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (field.isAnnotationPresent(Res.class)) {
                Class<?> clazz = field.getType();
                Res Res = field.getAnnotation(Res.class);
                try {
                    if (int.class.equals(clazz) && Color.class.equals(Res.className())) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "color", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getColor(myId));
                    } else if (int.class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "integer", view.getContext().getPackageName());
                        field.setInt(objectViewHolder, view.getResources().getInteger(myId));
                    } else if (boolean.class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "bool", view.getContext().getPackageName());
                        field.setBoolean(objectViewHolder, view.getResources().getBoolean(myId));
                    } else if (String.class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "string", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getString(myId));
                    } else if (Drawable.class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "drawable", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getDrawable(myId));
                    } else if (String[].class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "array", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getStringArray(myId));
                    } else if (int[].class.equals(clazz) && Color[].class.equals(Res.className())) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "array", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getIntArray(myId));
                    } else if (int[].class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "array", view.getContext().getPackageName());
                        field.set(objectViewHolder, view.getResources().getIntArray(myId));
                    } else if (Drawable[].class.equals(clazz)) {
                        myId = (Res.identifier() != View.NO_ID) ? Res.identifier() :
                                view.getResources().getIdentifier(field.getName(), "array", view.getContext().getPackageName());
                        TypedArray objectArray = view.getResources().obtainTypedArray(myId);
                        Drawable[] drawables = new Drawable[objectArray.length()];
                        for (int i = 0; i < objectArray.length(); i++)
                            drawables[i] = objectArray.getDrawable(i);
                        field.set(objectViewHolder, drawables);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(NavigationWidget.class) && myId != View.NO_ID) {
                NavigationWidget NavigationWidget = field.getAnnotation(NavigationWidget.class);
                NavigationView navigationView = (NavigationView) view.findViewById(myId);
                navigationView.getMenu().clear(); //clear old inflated items.
                navigationView.inflateMenu(NavigationWidget.idMenu());
                DrawerLayout drawer = (DrawerLayout) view.findViewById(NavigationWidget.idDrawer());
                Toolbar toolbar = (Toolbar) view.findViewById(NavigationWidget.idToolBar());
                AppCompatActivity act = null;
                if (objectViewHolder instanceof AppCompatActivity)
                    act = (AppCompatActivity) objectViewHolder;
                else if (objectViewHolder instanceof Fragment)
                    act = (AppCompatActivity) ((Fragment) objectViewHolder).getActivity();
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        act, drawer, toolbar, NavigationWidget.openString(), NavigationWidget.closeString());
                drawer.setDrawerListener(toggle);
                toggle.syncState();
            } else if (field.isAnnotationPresent(TabHostWidget.class) && myId != View.NO_ID) {
                TabHostWidget TabHostWidget = field.getAnnotation(TabHostWidget.class);
                FragmentTabHost tabHost = (FragmentTabHost) view.findViewById(myId);
                AppCompatActivity act = null;
                if (objectViewHolder instanceof AppCompatActivity)
                    act = (AppCompatActivity) objectViewHolder;
                else if (objectViewHolder instanceof Fragment)
                    act = (AppCompatActivity) ((Fragment) objectViewHolder).getActivity();
                tabHost.setup(act, act.getSupportFragmentManager(), TabHostWidget.tabcontent());
                tabHost.clearAllTabs();
                for (int i = 0; i < TabHostWidget.value().length; i++) {
                    SimpleTabHost SimpleTabHost = TabHostWidget.value()[i];
                    tabHost.addTab(tabHost
                                    .newTabSpec(SimpleTabHost.c().getSimpleName())
                                    .setIndicator(SimpleTabHost.t(),
                                            act.getResources().getDrawable(SimpleTabHost.d())),
                            SimpleTabHost.c(), null);
                }

            } else if (field.isAnnotationPresent(ViewPagerWidget.class) && myId != View.NO_ID) {
                final ViewPagerWidget ViewPagerWidget = field.getAnnotation(ViewPagerWidget.class);
                AppCompatActivity act = null;
                if (objectViewHolder instanceof AppCompatActivity)
                    act = (AppCompatActivity) objectViewHolder;
                else if (objectViewHolder instanceof Fragment)
                    act = (AppCompatActivity) ((Fragment) objectViewHolder).getActivity();
                TabLayout tabLayout = (TabLayout) view.findViewById(myId);
                final ViewPager viewPager = (ViewPager) view.findViewById(ViewPagerWidget.idViewPaper());
                WidgetPagerAdapter adapter = new WidgetPagerAdapter(act.getSupportFragmentManager());
                for (int i = 0; i < ViewPagerWidget.values().length; i++) {
                    SimpleViewPaper SimpleViewPaper = ViewPagerWidget.values()[i];
                    try {
                        adapter.addFrag((Fragment) SimpleViewPaper.c().getDeclaredConstructor().newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                viewPager.setOffscreenPageLimit(adapter.getCount());
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    SimpleViewPaper SimpleViewPaper = ViewPagerWidget.values()[i];
                    View viewTittle = LayoutInflater.from(act).inflate(R.layout.custom_tab, null);
                    TextView txt = (TextView) viewTittle.findViewById(R.id.text_view);
                    txt.setText(SimpleViewPaper.t());
                    ImageView imgView = (ImageView) viewTittle.findViewById(R.id.image_view);
                    imgView.setImageResource(SimpleViewPaper.d());
                    if (i == 0) {
                        txt.setTextColor(view.getResources().getColor(ViewPagerWidget.colorSelected()));
                        imgView.setColorFilter(Color.rgb(0, 0, 0));
                    } else {
                        txt.setTextColor(view.getResources().getColor(ViewPagerWidget.colorUnselected()));
                        imgView.setColorFilter(Color.rgb(170, 170, 170));
                    }
                    tabLayout.getTabAt(i).setCustomView(viewTittle);
                }
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView().findViewById(R.id.text_view))
                                .setTextColor(view.getResources().getColor(ViewPagerWidget.colorSelected()));
                        ((ImageView) tab.getCustomView().findViewById(R.id.image_view))
                                .setColorFilter(Color.rgb(0, 0, 0));
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        ((TextView) tab.getCustomView().findViewById(R.id.text_view))
                                .setTextColor(view.getResources().getColor(ViewPagerWidget.colorUnselected()));
                        ((ImageView) tab.getCustomView().findViewById(R.id.image_view))
                                .setColorFilter(Color.rgb(170, 170, 170));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
        }

        for (final Method method : (objectViewHolder).getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            final Class<?> params[] = method.getParameterTypes();
            if (method.isAnnotationPresent(OnClick.class)) {
                OnClick Onclick = method.getAnnotation(OnClick.class);
                int idWidget = (Onclick.identifier() != View.NO_ID) ? Onclick.identifier() :
                        view.getResources().getIdentifier(method.getName(), "id", view.getContext().getPackageName());
                if (idWidget != View.NO_ID) {
                    view.findViewById(idWidget).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                method.invoke(objectViewHolder, new Object[]{params});
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else if (method.isAnnotationPresent(OnLongClick.class)) {
                OnLongClick OnLongClick = method.getAnnotation(OnLongClick.class);
                int idWidget = (OnLongClick.identifier() != 0) ? OnLongClick.identifier() :
                        view.getResources().getIdentifier(method.getName(), "id", view.getContext().getPackageName());
                if (idWidget != View.NO_ID) {
                    view.findViewById(idWidget).setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            try {
                                method.invoke(objectViewHolder, new Object[]{params});
                                return true;
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                }
            } else if (method.isAnnotationPresent(OnFocus.class)) {
                final OnFocus OnFocus = method.getAnnotation(OnFocus.class);
                int idWidget = OnFocus.identifier();
                if (idWidget != View.NO_ID) {
                    view.findViewById(idWidget).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean b) {
                            try {
                                if (b)
                                    if (OnFocus.needParameters())
                                        method.invoke(objectViewHolder, v, b);
                                    else
                                        method.invoke(objectViewHolder);
                                else {
                                    if (OnFocus.needParameters())
                                        method.invoke(objectViewHolder, v, b);
                                    view.findViewById(OnFocus.viewRequestFocus()).requestFocus();
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else if (method.isAnnotationPresent(RegularExpression.class)) {
                final RegularExpression RegularExpression = method.getAnnotation(RegularExpression.class);
                int idWidget = (RegularExpression.identifier() != View.NO_ID) ? RegularExpression.identifier() :
                        view.getResources().getIdentifier(method.getName(), "id", view.getContext().getPackageName());
                if (idWidget != View.NO_ID) {
                    final EditText et = ((EditText) view.findViewById(idWidget));
                    if (RegularExpression.inputType() != 0) {
                        et.setInputType(RegularExpression.inputType());
                    } else
                        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                    if (RegularExpression.maxLeght() != 0) {
                        InputFilter[] fArray = new InputFilter[1];
                        fArray[0] = new InputFilter.LengthFilter(RegularExpression.maxLeght());
                        et.setFilters(fArray);
                    }
                    et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (actionId == EditorInfo.IME_ACTION_SEARCH
                                    || actionId == EditorInfo.IME_ACTION_DONE
                                    || actionId == EditorInfo.IME_ACTION_NEXT
                                    || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                                if (v.getText().toString().trim().isEmpty()) {
//                                    v.setText("192.168.1.1");
                                } else {
                                    List<Object> objects = new ArrayList<Object>();
                                    String newIP = v.getText().toString().trim();
                                    if (newIP.matches(RegularExpression.regularExpression())) {
                                        objects.add(newIP);
                                    }
                                    try {
                                        method.invoke(objectViewHolder, objects);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                                return true; // consume.
                            } else {
                                // pulsa otros botones
                            }
                            return false;
                        }
                    });
                    if (!RegularExpression.isIPAdress())
                        et.addTextChangedListener(new TextWatcher() {
                            private String currentString;
                            private int pos = 0;

                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                currentString = charSequence.toString();
                                pos = (i != 0) ? i : pos;
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                String tempIP = editable.toString().trim();
                                if (tempIP.equals(currentString))
                                    return;
                                boolean minMax = (currentString.length() <
                                        tempIP.length()) ? true : false;
                                if (!tempIP.isEmpty()) {
                                    String nuevaIP = "";
                                    if (nuevaIP.matches(RegularExpression.regularExpression())) {
                                        currentString = nuevaIP;
                                        et.setText(nuevaIP);
                                        if (pos >= currentString.length())
                                            et.setSelection(pos);
                                        else {
                                            if (minMax)
                                                et.setSelection(pos + 1);
                                            else
                                                et.setSelection(pos);
                                        }
                                    }
                                } else {
                                    et.setText(currentString);
                                }

                            }
                        });
                    else {

                        et.addTextChangedListener(new TextWatcher() {
                            private String currentString;
                            private int pos = 0;

                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                currentString = charSequence.toString();
                                pos = (i != 0) ? i : pos;
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                String tempIP = editable.toString().trim();
                                try {
                                    boolean minMax = (currentString.length() <
                                            tempIP.length()) ? true : false;
                                    if (!tempIP.isEmpty()) {
                                        if (tempIP.equals(currentString))
                                            return;
                                        //
                                        List<String> numbers = Arrays.asList(tempIP.split("\\."));
                                        if (numbers.size() != 4) {
                                            et.setText(currentString);
                                            return;
                                        }
                                        String nuevaIP = "";
                                        for (String number : numbers) {
                                            if (number.isEmpty()) {
                                                number = "0";
                                                minMax = true;
                                            }
                                            if (Integer.valueOf(number) > 255 ||
                                                    Integer.valueOf(number) < 0) {
                                                et.setText(currentString);
                                                return;
                                            }
                                            nuevaIP = nuevaIP.concat(Integer.valueOf(number) + "").concat(".");
                                        }
                                        nuevaIP = nuevaIP.substring(0, nuevaIP.length() - 1);
                                        //
                                        if (nuevaIP.matches(RegularExpression.regularExpression())) {
                                            currentString = nuevaIP;
                                            et.setText(nuevaIP);
                                            if (pos >= currentString.length())
                                                et.setSelection(pos);
                                            else {
                                                if (minMax)
                                                    et.setSelection(pos + 1);
                                                else
                                                    et.setSelection(pos);
                                            }
                                        }
                                    } else {
                                        et.setText(currentString);
                                    }
                                } catch (Exception e) {
                                    et.setText(currentString);
                                }
                            }
                        });
                    }
                }
            } else if (method.isAnnotationPresent(SpinnerSelected.class)) {
                final SpinnerSelected SpinnerSelected = method.getAnnotation(SpinnerSelected.class);
                int idWidget = SpinnerSelected.identifier();
                if (idWidget != View.NO_ID) {
                    final Spinner sp = ((Spinner) view.findViewById(idWidget));
                    Context context = null;
                    if (objectViewHolder instanceof Activity) {
                        context = (Context) objectViewHolder;
                    } else if (objectViewHolder instanceof Fragment) {
                        context = (Context) ((Fragment) objectViewHolder).getActivity();
                    }
                    final ArrayList<String> listString
                            = new ArrayList<String>(Arrays.asList((context)
                            .getResources().getStringArray(SpinnerSelected.arrayStringId())));
                    ArrayAdapter<String> arrayAdapter
                            = new ArrayAdapter<String>((context)
                            , SpinnerSelected.typeSpinner(), listString);
                    sp.setAdapter(arrayAdapter);
                    sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String current = listString.get(i);
                            try {
                                method.invoke(objectViewHolder, current, i);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                }
            } else if (method.isAnnotationPresent(GroupButtonSeleted.class)) {
                final GroupButtonSeleted GroupButtonSeleted = method.getAnnotation(GroupButtonSeleted.class);
                int idWidget = GroupButtonSeleted.identifier();
                if (idWidget != View.NO_ID) {
                    RadioGroup radioGroup = (RadioGroup) view.findViewById(idWidget);
                    radioGroup.setOrientation(GroupButtonSeleted.orientation());
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            try {
                                RadioButton radioButton = (RadioButton) view.findViewById(i);
                                method.invoke(objectViewHolder, radioGroup, radioButton);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }

    }

}
