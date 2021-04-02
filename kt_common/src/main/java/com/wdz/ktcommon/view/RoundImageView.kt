package com.wdz.ktcommon.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.wdz.ktcommon.R

/**
 * 将imageView变换成圆形或矩形，并可选择是否带边框
 * 圆角矩形边框尺寸有点差距
 * @author wdz
 */
class RoundImageView : AppCompatImageView {
    private var mMaskBitmap: Bitmap? = null
    private var mPaint: Paint? = null

    /**
     * 默认圆角为0dp,即直角
     */
    private var topLeftRadius = 0
    private var topRightRadius = 0
    private var bottomLeftRadius = 0
    private var bottomRightRadius = 0
    private var frameWidth = 0
    private var frameColor = 0
    private var type = 0
    private var framePaint: Paint? = null
    private var rectPath: Path? = null


    companion object {
        private val sXfermode: Xfermode =
            PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        private val sXfermode_dst_in: Xfermode =
            PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        const val CIRCLE = 0 //圆形
        const val ROUNDRECT = 1 //圆角
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(
            attrs,
            R.styleable.RoundImageView
        )
        // 默认为Circle
        type = array.getInt(R.styleable.RoundImageView_type, 0)
        frameWidth = array.getDimensionPixelSize(R.styleable.RoundImageView_frameWidth, 0)
        frameColor = array.getColor(R.styleable.RoundImageView_frameColor, 0)
        topLeftRadius = array.getDimensionPixelSize(R.styleable.RoundImageView_topLeftRadius, 0)
        topRightRadius = array.getDimensionPixelSize(R.styleable.RoundImageView_topRightRadius, 0)
        bottomLeftRadius =
            array.getDimensionPixelSize(R.styleable.RoundImageView_bottomLeftRadius, 0)
        bottomRightRadius =
            array.getDimensionPixelSize(R.styleable.RoundImageView_bottomRightRadius, 0)
        array.recycle()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.color = Color.BLACK
        if (frameWidth != 0) {
            framePaint = Paint(Paint.ANTI_ALIAS_FLAG)
            framePaint?.color = frameColor
            framePaint?.style = Paint.Style.STROKE
            framePaint?.strokeWidth = frameWidth.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawable?.run {
            val bitmap = Bitmap.createBitmap(
                width,
                height, Bitmap.Config.ARGB_8888
            )
            val bitmapCanvas = Canvas(bitmap)
            setBounds(0, 0, width, height)
            draw(bitmapCanvas)
            if (mMaskBitmap == null || mMaskBitmap!!.isRecycled) {
                if (type == CIRCLE) {
                    mMaskBitmap = getBitmapCircle(width, height)
                } else if (type == ROUNDRECT) {
                    mMaskBitmap = getBitmapRoundRect(width, height)
                }
            }
            if (type == CIRCLE) {
                mPaint?.xfermode = sXfermode_dst_in
            } else if (type == ROUNDRECT) {
                mPaint?.xfermode = sXfermode
            }
            mMaskBitmap?.let {
                bitmapCanvas.drawBitmap(it, 0.0f, 0.0f, mPaint)
            }

            //这里混合模式，上面设置完后，要再次设置为null
            mPaint?.xfermode = null
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint)


            //绘制外边框
            if (frameWidth != 0) {
                if (type == CIRCLE) {
                    val radius = Math.min(
                        (width - frameWidth) / 2,
                        (height - frameWidth) / 2
                    ).toFloat()
                    canvas.drawCircle(
                        width.toFloat() / 2,
                        height.toFloat() / 2,
                        radius,
                        framePaint!!
                    )
                } else if (type == ROUNDRECT) {
                    canvas.drawPath(rectPath!!, framePaint!!)
                }
            }
        }

    }

    /**
     * 圆角矩形
     * @param width
     * @param height
     * @return
     */
    private fun getBitmapRoundRect(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            width, height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val paint =
            Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLACK
        rectPath = Path()
        drawLeftUp(canvas, paint)
        drawRightUp(canvas, paint)
        drawRightDown(canvas, paint)
        drawLeftDown(canvas, paint)
        rectPath?.close()
        return bitmap
    }

    /**
     * 绘制左上角
     * @param canvas
     * @param paint
     */
    private fun drawLeftUp(
        canvas: Canvas,
        paint: Paint
    ) {
        if (topLeftRadius != 0) {
            val path = Path()
            //从（0，roundHeight）开始绘制
            path.moveTo(0f, topLeftRadius.toFloat())
            path.lineTo(0f, 0f)
            path.lineTo(topLeftRadius.toFloat(), 0f)
            //当 forceMoveTo =false 时，表示连接
            //画出一个直角
            path.arcTo(
                RectF(0F, 0F, (topLeftRadius * 2).toFloat(), (topLeftRadius * 2).toFloat()),
                -90f,
                -90f,
                true
            )
            if (frameWidth != 0) {
                rectPath?.moveTo(
                    frameWidth.toFloat() / 2,
                    topLeftRadius + frameWidth.toFloat() / 2
                )
                rectPath?.arcTo(
                    RectF(
                        frameWidth.toFloat() / 2,
                        frameWidth.toFloat() / 2,
                        frameWidth.toFloat() / 2 + topLeftRadius * 2,
                        frameWidth.toFloat() / 2 + topLeftRadius * 2
                    ), 180f, 90f, true
                )
            }
            path.close()
            canvas.drawPath(path, paint)
        } else {
            rectPath?.moveTo(frameWidth.toFloat() / 2, frameWidth.toFloat() / 2)
        }
    }

    /**
     * 绘制右上角
     * @param canvas
     * @param paint
     */
    private fun drawRightUp(
        canvas: Canvas,
        paint: Paint
    ) {
        if (topRightRadius != 0) {
            val path = Path()
            path.moveTo(width.toFloat(), topRightRadius.toFloat())
            path.lineTo(width.toFloat(), 0f)
            path.lineTo(width - topRightRadius.toFloat(), 0f)
            path.arcTo(
                RectF(
                    (width - topRightRadius * 2).toFloat(),
                    0F,
                    width.toFloat(),
                    (topRightRadius * 2).toFloat()
                ), -90f, 90f
            )
            if (frameWidth != 0) {
                rectPath?.lineTo(
                    width - topRightRadius - frameWidth.toFloat() / 2,
                    frameWidth.toFloat() / 2
                )
                rectPath?.arcTo(
                    RectF(
                        width - frameWidth.toFloat() / 2 - topRightRadius * 2,
                        frameWidth.toFloat() / 2,
                        width - frameWidth.toFloat() / 2,
                        0 + topRightRadius * 2 + frameWidth.toFloat() / 2
                    ), 270f, 90f
                )
            }
            path.close()
            canvas.drawPath(path, paint)
        } else {
            rectPath?.lineTo(
                width - frameWidth.toFloat() / 2,
                frameWidth.toFloat() / 2
            )
        }
    }

    /**
     * 绘制右下角
     * @param canvas
     * @param paint
     */
    private fun drawRightDown(
        canvas: Canvas,
        paint: Paint
    ) {
        if (bottomRightRadius != 0) {
            val path = Path()
            path.moveTo(width.toFloat(), height - bottomRightRadius.toFloat())
            path.lineTo(width.toFloat(), height.toFloat())
            path.lineTo(width - bottomRightRadius.toFloat(), height.toFloat())
            path.arcTo(
                RectF(
                    (width - bottomRightRadius * 2).toFloat(),
                    (height - bottomRightRadius * 2).toFloat(),
                    width.toFloat(),
                    height.toFloat()
                ), 90f, -90f
            )
            if (frameWidth != 0) {
                rectPath?.lineTo(
                    width - frameWidth.toFloat() / 2,
                    height - bottomRightRadius - frameWidth.toFloat() / 2
                )
                rectPath?.arcTo(
                    RectF(
                        width - frameWidth.toFloat() / 2 - bottomRightRadius * 2,
                        height - frameWidth.toFloat() / 2 - bottomRightRadius * 2,
                        width - frameWidth.toFloat() / 2,
                        height - frameWidth.toFloat() / 2
                    ), 0f, 90f
                )
            }
            path.close()
            canvas.drawPath(path, paint)
        } else {
            rectPath?.lineTo(
                width - frameWidth.toFloat() / 2,
                height - frameWidth.toFloat() / 2
            )
        }
    }

    /**
     * 绘制左下角
     * @param canvas
     * @param paint
     */
    private fun drawLeftDown(
        canvas: Canvas,
        paint: Paint
    ) {
        if (bottomLeftRadius != 0) {
            val path = Path()
            path.moveTo(0f, height - bottomLeftRadius.toFloat())
            path.lineTo(0f, height.toFloat())
            path.lineTo(bottomLeftRadius + frameWidth.toFloat() / 2, height.toFloat())
            path.arcTo(
                RectF(
                    0F,
                    (height - bottomLeftRadius * 2).toFloat(),
                    (bottomLeftRadius * 2).toFloat(),
                    height.toFloat()
                ), 90f, 90f
            )
            if (frameWidth != 0) {
                rectPath?.lineTo(
                    bottomLeftRadius - frameWidth.toFloat() / 2,
                    height - frameWidth.toFloat() / 2
                )
                rectPath?.arcTo(
                    RectF(
                        frameWidth.toFloat() / 2,
                        height - frameWidth.toFloat() / 2 - bottomLeftRadius * 2,
                        bottomLeftRadius * 2 + frameWidth.toFloat() / 2,
                        height - frameWidth.toFloat() / 2
                    ), 90f, 90f
                )
            }
            path.close()
            canvas.drawPath(path, paint)
        } else {
            rectPath?.lineTo(
                frameWidth.toFloat() / 2,
                height - frameWidth.toFloat() / 2
            )
        }
    }

    //圆形
    private fun getBitmapCircle(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(
            width, height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val paint =
            Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        var radius = 0F
        radius = Math.min((width - frameWidth) / 2, (height - frameWidth) / 2).toFloat()
        canvas.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, paint)
        return bitmap
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        var width = 0
        var height = 0
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (frameWidth != 0) {
            //控件宽度
            width = measureWidth + frameWidth
            //控件高度
            height = measureHeight + frameWidth
        }
        setMeasuredDimension(
            if (widthMode == MeasureSpec.EXACTLY) measureWidth else width,
            if (heightMode == MeasureSpec.EXACTLY) measureHeight else height
        )
    }

}