package com.zy.drapanddrop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class DragableDot extends View {
    private String mDescription;
    private int mColor = Color.RED; // TODO: use a default from R.color...
    private int mRadius = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private Paint mPaint;

    public DragableDot(Context context) {
        super(context);
        init(null, 0);
    }

    public DragableDot(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DragableDot(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DragableDot, defStyle, 0);

        final int N = a.getIndexCount();
        for (int i=0; i<N; i++){
           int attr = a.getIndex(i);
           switch (attr){
               case R.styleable.DragableDot_radius :
                   mRadius = a.getDimensionPixelSize(attr, 0);
                   break;
               case R.styleable.DragableDot_color:
                    mColor = a.getColor(attr, Color.RED);
                   break;
               case R.styleable.DragableDot_description:
                   mDescription = a.getString(attr);
                   break;

           }

        }

        if (a.hasValue(R.styleable.DragableDot_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.DragableDot_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xFFF0F0FF);

        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int totalDiameter = 2*mRadius + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(totalDiameter, totalDiameter);
    }

    /*private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        float cx = getWidth()/2;
        float cy = getHeight()/2;

        canvas.drawCircle(cx, cy, mRadius, mPaint);
        canvas.drawText(mDescription, cx, cy + mTextPaint.getFontSpacing()/2, mTextPaint);
    }



    /**
     * Gets the example drawable attribute value.
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
