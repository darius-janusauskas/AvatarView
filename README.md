AvatarView
============

AvatarView is a library that implements an view used for displaying user avatar. It implements the the Google Play avatars described in [this][1] article.

### Usage

To include this library just add the custom view to your layout:

```xml
<org.janusauskas.avatarview.AvatarView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
```

You can customize the shadow and outline, by setting a diferent [`Painter`][2] fot the [`AvatarView`][3].

```java
avatarView.setPainter(new Painter.Builder().setShadowColor(Color.LTGRAY).setOutlineColor(Color.WHITE).build());
```

The example project is avalable [here][4].

[1]: http://www.pushing-pixels.org/2014/04/10/avatars-avatars-everywhere.html
[2]: https://github.com/darius-janusauskas/AvatarView/blob/master/avatarview/src/main/java/org/janusauskas/avatarview/Painter.java
[3]: https://github.com/darius-janusauskas/AvatarView/blob/master/avatarview/src/main/java/org/janusauskas/avatarview/AvatarView.java
[4]: https://github.com/darius-janusauskas/AvatarView/blob/master/avatarview-example/