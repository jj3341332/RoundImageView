package com.mrj.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/2.
 */


public class RoundImageView extends ImageView {
    protected int borderColor = Color.WHITE;
    protected int borderWidth = 0;
    protected int radius=0;
    protected final Paint borderPaint = new Paint();

    protected final  Paint imagePaint = new Paint();
    protected int viewWidth=0;
    protected int viewHeight=0;
    private final RectF borderRect = new RectF();

    private final RectF imageRect = new RectF();
    private int borderAlpha=255;
    private int shadowColor= Color.BLACK;;


    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(viewWidth == w && viewHeight == h) return;
        viewWidth = w;
        viewHeight = h;
        borderRect.set(borderWidth,borderWidth,viewWidth-borderWidth,viewHeight-borderWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable()!=null){
            Bitmap bitmap=((BitmapDrawable) getDrawable()).getBitmap();
            imagePaint.setAntiAlias(true);
            imagePaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            if(bitmapWidth > 0 && bitmapHeight > 0) {
                float width = Math.round(viewWidth - 2f * borderWidth);
                float height = Math.round(viewHeight - 2f * borderWidth);
                float scale;
                float translateX = 0;
                float translateY = 0;
                if (bitmapWidth * height > width * bitmapHeight) {
                    scale = height / bitmapHeight;
                    translateX = Math.round((width/scale - bitmapWidth) / 2f);
                } else {
                    scale = width / (float) bitmapWidth;
                    translateY = Math.round((height/scale - bitmapHeight) / 2f);
                }
                Matrix matrix = new Matrix();
                matrix.setScale(scale, scale);
                matrix.preTranslate(translateX, translateY);
                matrix.postTranslate(borderWidth, borderWidth);
                imageRect.set(-translateX, -translateY, bitmapWidth + translateX, bitmapHeight + translateY);
                //   int bitmapRadius = Math.round(radius / scale);


                setLayerType(LAYER_TYPE_SOFTWARE, borderPaint);
                canvas.drawRoundRect(borderRect,radius,radius,borderPaint);

                canvas.save();
                canvas.concat(matrix);
                canvas.drawRoundRect(imageRect, radius/scale, radius/scale, imagePaint);
                canvas.restore();


            }

        }else {
            super.onDraw(canvas);
        }


    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);

        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyle, 0);
            borderColor = typedArray.getColor(R.styleable.RoundImageView_border_color, borderColor);
            borderWidth = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_border_width, borderWidth);
            radius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_radius,radius);
            borderAlpha = typedArray.getInteger(R.styleable.RoundImageView_border_alpha,borderAlpha);
            shadowColor = typedArray.getColor(R.styleable.RoundImageView_shadow_color, shadowColor);
            typedArray.recycle();
        }
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setAlpha(borderAlpha);
        borderPaint.setShadowLayer(1, 0, 0  , shadowColor);


    }
}