package org.janusauskas.avatarview;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by darius on 14.5.17.
 */
public class Painter {

    public Paint mShadowPaint;
    public Paint mImagePaint;
    public Paint mOutlinePaint;

    private static Painter mInstance = null;

    public static synchronized Painter getInstance(){
        if(mInstance == null)
        {
            mInstance = new Painter();
        }
        return mInstance;
    }

    public Painter(){
        mShadowPaint = getDefaultShadowPaint();
        mImagePaint = getDefaultImagePaint();
        mOutlinePaint = getDefaultOutlinePaint();
    }

    private Painter(Builder builder){
        mShadowPaint = builder.mShadowPaint != null ? builder.mShadowPaint : getDefaultShadowPaint();
        mImagePaint = builder.mImagePaint != null ? builder.mImagePaint : getDefaultImagePaint();
        mOutlinePaint = builder.mOutlinePaint != null ? builder.mOutlinePaint : getDefaultOutlinePaint();
    }

    private static Paint getDefaultShadowPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.DKGRAY);
        paint.setAlpha(50);
        paint.setStyle(Paint.Style.FILL);

        return paint;
    }

    private static Paint getDefaultImagePaint(){
        return new Paint();
    }

    private static Paint getDefaultOutlinePaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        return paint;
    }

    public static class Builder{

        private Paint mShadowPaint;
        private Paint mImagePaint;
        private Paint mOutlinePaint;

        public Builder setShadowPaint(Paint paint){
            mShadowPaint = paint;
            return this;
        }

        public Builder setImagePaint(Paint paint){
            mImagePaint = paint;
            return this;
        }

        public Builder setOutlinePaint(Paint paint){
            mOutlinePaint = paint;
            return this;
        }

        /**
         * Sets the shadow color.
         * @param color
         * @return
         */
        public Builder setShadowColor(int color){
            mShadowPaint = getDefaultShadowPaint();
            mShadowPaint.setColor(color);
            return this;
        }

        /**
         * Sets the outline color.
         * @param color
         * @return
         */
        public Builder setOutlineColor(int color){
            mOutlinePaint = getDefaultOutlinePaint();
            mOutlinePaint.setColor(color);
            return this;
        }

        public Painter build() {
            return new Painter(this);
        }
    }
}
