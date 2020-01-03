# FUDI ANNOTATION Library API 

 [![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
 [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
 [![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiAnnotation/blob/master/LICENSE)
 [![Workflow](https://github.com/romellfudi/FudiAnnotation/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/FudiAnnotation/actions)
 [![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-annotation.svg)](https://bintray.com/romllz489/maven/fudi-annotation)
 [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Fudi%20Annotation-green.svg?style=flat)]( https://android-arsenal.com/details/1/7115 )
 [![Jitpack](https://jitpack.io/v/romellfudi/FudiAnnotation.svg)](https://jitpack.io/#romellfudi/FudiAnnotation)
 [![CircleCi]( https://img.shields.io/circleci/project/github/romellfudi/FudiAnnotation.svg)](https://circleci.com/gh/romellfudi/FudiAnnotation/tree/master)
 [![](https://img.shields.io/badge/language-ES-blue.svg)](./README.es)


### by Romell Dominguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` is 1.0.a

Add the following in your app's `build.gradle` file:

```gradle
repositories {
    jcenter()
}
dependencies {
    implementation 'com.romellfudi.fudi_annotation:fudi-annotation:1.0.a'
}
```

Examples:

if you need Inject widget, first setup Fudi Library on youe activity or fragment:
```java
import com.romellfudi.fudi_annotation.Bind;
setContentView(R.layout.activity_main);
Bind.Plug(this);
```
Then you could be able inject whatever android widget without initializer:
```java
@ItemWidget(identifier = R.id.toolbar)
public Toolbar toolbar;
@ItemWidget
RadioGroup radio_group; // private variable
@ItemWidget
private Spinner my_spinner;
```
You can also override mapping claases:
```java
@ItemWidget(className = FloatingActionButton.class)
public View fab;
```
if you use would like Tabhost on your frame, thats it:
```java
@ItemWidget
@TabHostWidget(
        tabcontent = R.id.tabcontent,
        value = {
                @SimpleTabHost(c = RetrievalFragment.class, t = "", d = R.drawable.buscar_cliente_icon),
                @SimpleTabHost(c = ConfigurationFragment.class, t = "", d = R.drawable.config_icon)
        })
FragmentTabHost tabHost;
```
if you use would like Tablayout, with many custom ViewPages:
```java
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
```

This API also have inject methods for android performance:
```java
@OnClick(identifier = R.id.btn_apretar)
private void info() {
        Toast.makeText(this, "Go!", Toast.LENGTH_SHORT).show();
}
```
Other inject methods:
```java
 @OnLongClick(identifier = R.id.btn_apretar)
```

for Spinners or ButtomGroups, you can implement the inject metthods:
```java
@SpinnerSelected(identifier = R.id.my_spinner, arrayStringId = R.array.lista_pais,
            typeSpinner = android.R.layout.simple_spinner_item)
private void SeleccionPaises(String paisSelected, int position) {
    Toast.makeText(this, position + "-" + paisSelected, Toast.LENGTH_SHORT).show();
}

@GroupButtonSeleted(identifier = R.id.radio_group)
private void ChangeYesNo(RadioGroup radioGroup, RadioButton radioButton) {
    Toast.makeText(this, "valor=" + radioButton.getText(), Toast.LENGTH_SHORT).show();
}
```
For EditText widget, like current input:
```java
@OnFocus(identifier = R.id.et_world, viewRequestFocus = R.id.et_ip, needParameters = true)
private void cuandoEsteEnfocado(EditText v, boolean b) {
    if (b)
        Toast.makeText(this, "Ingrese info", Toast.LENGTH_SHORT).show();
    else
        v.setText("Ha ingresado :" + v.getText().toString());
}
```
if you want setup easy regular expression on EditText widgets use:
```java
@RegularExpression(identifier = R.id.et_ip, maxLeght = 15,
            regularExpression = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
            isIPAdress = true)
private void FormatoIP(List<Object> objects) {
    if (objects.size() > 0) {
        String finalValue = (String) objects.get(0);
        Toast.makeText(this, "->" + finalValue, Toast.LENGTH_SHORT).show();
    }
}
```

### License
```
Copyright 2017 Romell D.Z.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

2017, January

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
img[src*='#gif'] { 
    width:200px;
    display: block;
    margin: auto;
}
</style>