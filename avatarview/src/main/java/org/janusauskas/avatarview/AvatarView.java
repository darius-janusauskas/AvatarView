package org.janusauskas.avatarview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by darius on 14.5.17.
 */
public class AvatarView extends View {

    private Painter mPainter;
    private Bitmap mBitmap;

    private int mWidth;
    private int mHeight;

    public AvatarView(Context context) {
        super(context);
        init();
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPainter = Painter.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = Utils.getPixels(100, getResources().getDisplayMetrics());
        int desiredHeight = Utils.getPixels(100, getResources().getDisplayMetrics());

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = Math.min(desiredWidth, widthSize);
        } else {
            mWidth = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = Math.min(desiredHeight, heightSize);
        } else {
            mHeight = desiredHeight;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    /**
     * Calculates the shadow rectangle with 5% padding.
     * @return the rectangle of the shadow.
     */
    protected RectF getShadowRect(){
        int xStart = 0;
        int yStart = 0;
        int xEnd = 0;
        int yEnd = 0;
        int padding = 0;
        if(mWidth <= mHeight){
            padding = Utils.getPercent(mWidth, 5);
            xStart = padding;
            xEnd = mWidth - padding;
            yStart = (mHeight - mWidth) / 2 + padding;
            yEnd = yStart + xEnd - xStart;
        } else {
            padding = Utils.getPercent(mHeight, 5);
            yStart = padding;
            yEnd = mHeight - padding;
            xStart = (mWidth - mHeight) / 2 + padding;
            xEnd = xStart + yEnd - yStart;
        }

        return new RectF(xStart, yStart, xEnd, yEnd);
    }


    protected RectF getImageRect(Float scale){
        int max = Math.min(mWidth, mHeight);
        RectF rect = getShadowRect();
        return new RectF((rect.left + Utils.getPercent(max, 2))/scale, rect.top/scale, (rect.right - Utils.getPercent(max, 2))/scale, (rect.bottom - Utils.getPercent(max, 3))/scale);
    }

    /**
     * Calculates the rectangle of the outline.
     * @return
     */
    protected RectF getOutlineRect(){
        int min = Math.min(mWidth, mHeight);
        RectF rect = getShadowRect();
        mPainter.mOutlinePaint.setStrokeWidth(Utils.getPercent(min, 3));
        float strokeWidth = mPainter.mOutlinePaint.getStrokeWidth();
        return new RectF(rect.left + Utils.getPercent((int)strokeWidth, 60), rect.top, rect.right - Utils.getPercent((int)strokeWidth, 60), rect.bottom - strokeWidth);
    }

    protected void drawShadow(Canvas canvas){
        canvas.drawOval(getShadowRect(), mPainter.mShadowPaint);
    }

    protected void drawImage(Canvas canvas, Bitmap bitmap){
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPainter.mImagePaint.setShader(bitmapShader);
        int max = Math.min(mWidth, mHeight);
        float scale = getScaleFactor(mWidth, mHeight, bitmap);

        canvas.scale(scale, scale);
        canvas.drawRoundRect(getImageRect(scale), max*2, max*2, mPainter.mImagePaint);
        canvas.scale(1/scale, 1/scale);
    }

    protected void drawOutline(Canvas canvas){
        canvas.drawOval(getOutlineRect(), mPainter.mOutlinePaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawShadow(canvas);
        if(mBitmap != null) drawImage(canvas, mBitmap);
        drawOutline(canvas);
    }

    private float getScaleFactor(int viewWidth, int viewHeight, Bitmap bitmap){
        float scale = 1f;
        if(viewWidth <= viewHeight){
            scale = (float)viewWidth/(float)bitmap.getWidth();
        } else {
            scale = (float)viewHeight/(float)bitmap.getWidth();
        }

        return scale;
    }

    /**
     * Loads an image bitmap to the view.
     * @param bitmap {@link android.graphics.Bitmap} to load.
     */
    public void setImageBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        requestLayout();
        invalidate();
    }

    /**
     * Loads an image from a resource to the view.
     * @param resource image resource to load.
     */
    public void setImageResource(int resource){
        mBitmap = BitmapFactory.decodeResource(getResources(), resource);
        requestLayout();
        invalidate();
    }

    /**
     * Set the painter for this {@link org.janusauskas.avatarview.AvatarView}
     * @param painter
     */
    public void setPainter(Painter painter){
        mPainter = painter;
    }


}
