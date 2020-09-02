# DiscreteSlider [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DiscreteSlider-green.svg?style=true)](https://android-arsenal.com/details/1/3871)

## Overview

A slider that allows a user to select a value at one of the specified tickmarks

## Screenshots

![DiscreteSlider](https://raw.githubusercontent.com/lawloretienne/DiscreteSlider/master/images/DiscreteSlider_Screenshot4.png)

## Setup

#### Gradle

`compile 'com.github.lawloretienne:discreteslider:0.0.9'`

#### Maven
```
<dependency>
    <groupId>com.github.lawloretienne</groupId>
    <artifactId>discreteslider</artifactId>
    <version>0.0.9</version>
</dependency>
```

## Sample Usage

```xml
<com.etiennelawlor.discreteslider.library.ui.DiscreteSlider
        android:layout_width="match_parent"
        android:layout_height="64dp"
        android:paddingLeft="8dp"
        android:paddingRight="8dp"
        android:layout_gravity="bottom"
        android:background="@color/grey_100"
        app:backdropFillColor="@color/grey_200"
        app:backdropStrokeColor="@color/grey_300"
        app:backdropStrokeWidth="1dp"
        app:horizontalBarThickness="4dp"
        app:tickMarkCount="6"
        app:tickMarkRadius="8dp"
        app:position="1"
        app:thumb="@drawable/thumb"
        app:progressDrawable="@drawable/transparent_progress_drawable"/>
```

## Notes

### XML Attributes

* **backdropFillColor** - the fill color of the slider backdrop
* **backdropStrokeColor** - the stroke color of the slider backdrop
* **backdropStrokeWidth** - the width of the stroke on the slider backdrop
* **horizontalBarThickness** - the thickness of the horizontal bar that makes up the slider backdrop
* **tickMarkCount** - the number of tickmarks
* **tickMarkRadius** - the radius of each tickmark
* **position** - the position of the thumb in the slider (set to the position of the appropriate tickmark)
* **thumb** - the drawable that is used as the thumb of the slider
* **progressDrawable** - the drawable that is used as the progress indicator of the slider

## Developed By

* Etienne Lawlor 
 
&nbsp;&nbsp;&nbsp;**Email** - lawloretienne@gmail.com

&nbsp;&nbsp;&nbsp;**Website** - https://medium.com/@etiennelawlor

## Projects/Apps using DiscreteSlider

Feel free to contact me to add yours to this list.

## License

```
Copyright 2016 Etienne Lawlor

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
