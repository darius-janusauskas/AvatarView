package org.janusauskas.avatarview.example;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.janusauskas.avatarview.AvatarView;
import org.janusauskas.avatarview.Painter;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private AvatarView mAvatar_1;
    private AvatarView mAvatar_2;
    private AvatarView mAvatar_3;
    private AvatarView mAvatar_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAvatar_1 = (AvatarView) findViewById(R.id.avatar_1);
        mAvatar_2 = (AvatarView) findViewById(R.id.avatar_2);
        mAvatar_3 = (AvatarView) findViewById(R.id.avatar_3);
        mAvatar_4 = (AvatarView) findViewById(R.id.avatar_4);

        findViewById(R.id.add_image_b).setOnClickListener(this);
        findViewById(R.id.remove_image_b).setOnClickListener(this);


        mAvatar_2.setPainter(new Painter.Builder().setShadowColor(Color.LTGRAY).setOutlineColor(Color.WHITE).build());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_image_b:
                mAvatar_1.setImageResource(R.drawable.ic_launcher);
                mAvatar_2.setImageResource(R.drawable.ic_launcher);
                mAvatar_3.setImageResource(R.drawable.ic_launcher);
                mAvatar_4.setImageResource(R.drawable.ic_launcher);
                break;

            case R.id.remove_image_b:
                mAvatar_1.setImageBitmap(null);
                mAvatar_2.setImageBitmap(null);
                mAvatar_3.setImageBitmap(null);
                mAvatar_4.setImageBitmap(null);
                break;
        }
    }
}
