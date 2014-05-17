package org.janusauskas.avatarview;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by darius on 14.5.17.
 */
public class Utils {

    /**
     * Returns a certain percentage from and integer.
     * @param value the full integer value.
     * @param percent the percentage to get from the value.
     * @return the given percentage from the given value.
     */
    public static int getPercent(int value, float percent){
        return (int)(value*(percent/100.0f));
    }

    /**
     * Converts density pixels to normal pixels.
     * @param dp the value in density pixels
     * @param dm
     * @return density pixel value in pixels.
     */
    public static int getPixels(int dp, DisplayMetrics dm){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
    }
}
