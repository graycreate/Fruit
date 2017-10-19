package me.ghui.example_android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import me.ghui.example_android.R;
import me.ghui.example_android.util.ScaleUtils;


/**
 * Created by ghui on 01/04/2017.
 */

public class TagView extends AppCompatTextView {

    private int mColor = 0xFFF5F5F5;
    private float mCorner = 0;
    private Paint mPaint;

    public TagView(Context context) {
        super(context);
        init(context, null);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        int padding = ScaleUtils.dp(5, context);
        setPadding(padding * 2, padding, padding * 2, padding);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagView, 0, 0);
        try {
            mColor = a.getColor(R.styleable.TagView_tagColor, mColor);
            mCorner = a.getDimension(R.styleable.TagView_tagCorner, mColor);
        } finally {
            a.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCorner > 0) {
            canvas.drawRoundRect(0, 0, getWidth(), getHeight(), mCorner, mCorner, mPaint);
        } else {
            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        }
        super.onDraw(canvas);
    }

}
