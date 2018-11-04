# Librería FUDI ANNOTATION para Android Widgets 

 [![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
 [![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17)
 [![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiAnnotation/blob/master/LICENSE)
 [![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-annotation.svg)](https://bintray.com/romllz489/maven/fudi-annotation)
 [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Fudi%20Annotation-green.svg?style=flat)]( https://android-arsenal.com/details/1/7115 )
 [![Jitpack](https://jitpack.io/v/romellfudi/FudiAnnotation.svg)](https://jitpack.io/#romellfudi/FudiAnnotation)
 [![CircleCi]( https://img.shields.io/circleci/project/github/romellfudi/FudiAnnotation.svg)](https://circleci.com/gh/romellfudi/FudiAnnotation/tree/master)
 [![](https://img.shields.io/badge/language-EN-blue.svg)](./)

### by Romell Dominguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` es 1.0.a

Agregar en tu archivo `build.gradle` del proyecto Android:

```gradle
repositories {
    jcenter()
}
dependencies {
    compile 'com.romellfudi.fudi_annotation:fudi-annotation:1.0.a'
}
```

Cómo usar:

En caso requieras usar injecciones en tu proyecto de Android, primero requieres configurar la librería dentro del activity o fragment:
```java
import com.romellfudi.fudi_annotation.Bind;
setContentView(R.layout.activity_main);
Bind.Plug(this);
```
Luego podemos direccionar cualquier objecto widget de los recursos siempre que esten dentro del mapeo xml:
```java
@ItemWidget(identifier = R.id.toolbar)
public Toolbar toolbar;
@ItemWidget
RadioGroup radio_group; // private variable
@ItemWidget
private Spinner my_spinner;
```
Puedes mapear las clases sin tener que inicializar,manteniendo protegida la información del objeto:
```java
@ItemWidget(className = FloatingActionButton.class)
public View fab;
```
Para uso de Tabhost:
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
Para uso de Tablayout, podrás customizarlo los ViewPages en custion de segundo:
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

Esta API soporta injecciones de métodos comunes como @OnClick & @OnLongClick:
```java
@OnClick(identifier = R.id.btn_apretar)
private void info() {
        Toast.makeText(this, "Go!", Toast.LENGTH_SHORT).show();
}
```
```java
 @OnLongClick(identifier = R.id.btn_apretar)
```

Para los widgets de agrupaciones como Spinners o ButtomGroups, puedes incorporar métodos de selección:
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
Para campos de texto que requieras cambiar si estrucctura visual, podrás incorporar con @OnFocus:
```java
@OnFocus(identifier = R.id.et_world, viewRequestFocus = R.id.et_ip, needParameters = true)
private void cuandoEsteEnfocado(EditText v, boolean b) {
    if (b)
        Toast.makeText(this, "Ingrese info", Toast.LENGTH_SHORT).show();
    else
        v.setText("Ha ingresado :" + v.getText().toString());
}
```
Si quieres implementar una  expresión regular dentro de un campo de texto, facimente se puede implementar con @RegularExpression:
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

2017, Enero

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