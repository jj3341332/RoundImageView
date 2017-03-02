# Android RoundImageView控件


[![](https://jitpack.io/v/jj3341332/RoundImageView.svg)](https://jitpack.io/#jj3341332/RoundImageView)

---

##### 高仿淘宝的商品图  微信朋友圈个人头像 效果如下

![image](https://raw.githubusercontent.com/jj3341332/RoundImageView/master/images/example.png)
![image](https://raw.githubusercontent.com/jj3341332/RoundImageView/master/images/example2.png)


### 联系方式
![image](https://raw.githubusercontent.com/jj3341332/RoundImageView/master/images/AndroidQQqun.png)

# 如何使用？
## 第一步
在项目的build.gradle中添加如下代码

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
##第二步
在模块的build.gradle中添加如下代码


```
dependencies {
	        compile 'com.github.jj3341332:RoundImageView:1.0'
	}
```


### 实例代码
    <com.mrj.library.RoundImageView
        android:layout_marginTop="40dp"
        android:layout_gravity="center_horizontal"
        android:layout_width="150dp"
        android:layout_height="250dp"
        app:border_color="#ffffff"
        app:radius="10dp"
        app:border_width="10dp"
        app:border_alpha="255"
        app:shadow_color="#000"
        android:src="@mipmap/jieyi2"/>

### Attributes属性说明

Attributes | format | describe
---|---|---
border_color | color |  边框颜色
radius | dimension | 圆角边框
border_width | dimension | 边框长度
border_alpha | integer | 边框颜色透明度
shadow_color | color | 阴影颜色


