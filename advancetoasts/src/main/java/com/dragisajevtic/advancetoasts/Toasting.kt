package com.dragisajevtic.advancetoasts

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class Toasting(private val context : Context) {

    enum class TextPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    enum class ToastPosition {
        TOP,
        CENTER,
        BOTTOM
    }


    private lateinit var descriptionText : TextView
    private lateinit var titleText : TextView
    private lateinit var card : CardView
    private lateinit var view : View

    private var radius = 0f
    private var title = ""
    private var message = ""

    private var toastColor = Color.WHITE
    private var toastTitleColor = Color.BLACK
    private var toastMessageColor = Color.BLACK

    private var titleTextSize = -1
    private var messageTextSize = -1

    private var titleTypeface : Typeface? = null
    private var messageTypeface : Typeface? = null

    private var titleGravity = TextPosition.CENTER
    private var messageGravity = TextPosition.CENTER
    private var toastPosition = ToastPosition.TOP

    private var verticalOffset = 0

    /**
     * Set radius of message you want to show
     * Radius should be set in number of DP you want corners to be rounded
     */
    fun setRadius(radius : Int) : Toasting {
        this.radius = (radius * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toFloat()
        return this
    }

    /**
     * Set Toast title to be shown
     */
    fun setTitle(title : String) : Toasting {
        this.title = title
        return this
    }

    /**
     * Set title to be shown in Toast
     * title text to be shown
     * textSize is size of text you want to have
     * titleTextPosition is horizontal gravity
     */
    fun setTitle(title: String, textSize: Int, titleTextPosition: TextPosition): Toasting {
        this.title = title
        this.titleGravity = titleTextPosition
        this.titleTextSize = textSize
        return this
    }

    /**
     * Set title to be shown in Toast
     * title text to be shown
     * typeFace of custom font to be used
     * textSize is size of text you want to have
     * titleTextPosition is horizontal gravity
     */
    fun setTitle(title: String, typeFace: Typeface, textSize: Int, titleTextPosition: TextPosition): Toasting {
        this.title = title
        this.titleGravity = titleTextPosition
        this.titleTextSize = textSize
        this.titleTypeface = typeFace
        return this
    }

    /**
     * Set Toast title gravity. Can be LEFT, CENTER or RIGHT
     */
    fun setTitleTextGravity(position : TextPosition) : Toasting {
        this.titleGravity = position
        return this
    }

    /**
     * Set toast title text color. Color have to be provided
     * resolved like this resources.getColor(R.color.red, null)
     */
    fun setTitleTextColor(toastTitleColor : Int) : Toasting {
        this.toastTitleColor = toastTitleColor
        return this
    }

    /**
     * Set message to be shown in Toast
     * message text to be shown
     */
    fun setMessage(message : String) : Toasting {
        this.message = message
        return this
    }

    /**
     * Set message to be shown in Toast
     * message text to be shown
     * textSize is size of text you want to have
     * messageTextPosition is horizontal gravity
     */
    fun setMessage(message: String, textSize: Int, messageTextPosition: TextPosition): Toasting {
        this.message = message
        this.messageGravity = messageTextPosition
        this.messageTextSize = textSize
        return this
    }

    /**
     * Set message to be shown in Toast
     * message text to be shown
     * typeFace of custom font to be used
     * textSize is size of text you want to have
     * messageTextPosition is horizontal gravity
     */
    fun setMessage(message: String, typeFace: Typeface, textSize: Int, messageTextPosition: TextPosition): Toasting {
        this.message = message
        this.messageGravity = messageTextPosition
        this.messageTextSize = textSize
        this.messageTypeface = typeFace
        return this
    }

    /**
     * Set Toast message gravity. Can be LEFT, CENTER or RIGHT
     */
    fun setMessageTextGravity(position : TextPosition) : Toasting {
        this.messageGravity = position
        return this
    }

    /**
     * Set tittle text size
     */
    fun setTitleTextSize(textSize : Int) : Toasting {
        this.titleTextSize = textSize
        return this
    }

    /**
     * Set message text size
     */
    fun setMessageTextSize(textSize : Int) : Toasting {
        this.messageTextSize = textSize
        return this
    }

    /**
     * Set tittle text typeface
     */
    fun setTitleTypeFace(typeface : Typeface) : Toasting {
        this.titleTypeface = typeface
        return this
    }

    /**
     * Set message text typeface
     */
    fun setMessageTypeface(typeface : Typeface) : Toasting {
        this.messageTypeface = typeface
        return this
    }

    /**
     * Set toast message text color. Color have to be provided
     * resolved like this resources.getColor(R.color.red, null)
     */
    fun setMessageTextColor(toastMessageTextColor : Int) : Toasting {
        this.toastMessageColor = toastMessageTextColor
        return this
    }

    /**
     * Set background toast color. Color have to be provided
     * resolved like this resources.getColor(R.color.red, null)
     */
    fun setToastColor(toastColor : Int) : Toasting {
        this.toastColor = toastColor
        return this
    }

    /**
     * Setting vertical position. Can be set to TOP, CENTER or BOTTOM
     */
    fun setToastVerticalPosition(toastPosition: ToastPosition) : Toasting {
        this.toastPosition = toastPosition
        return this
    }

    /**
     * Setting vertical Toast offset. When Toast is in TOP position it creates
     * padding in between top and toast. When Toast in Bottom position create
     * padding in between bottom and toast
     */
    fun setToastVerticalOffset(verticalOffset: Int) : Toasting {
        this.verticalOffset = verticalOffset
        return this
    }

    /**
     * Prepare toast to be ready for showing. After this call .show to show your prepared Toast
     */
    fun build() : Toasting {
        inflateViews()
        card.setCardBackgroundColor(this.toastColor)
        card.radius = this.radius
        prepareText()
        return this
    }

    /**
     * Setting and checking all necessary things to show text properly
     */
    private fun prepareText() {
        if(this.title.isNotEmpty() && this.message.isNotEmpty()) {
            if(::titleText.isInitialized) {
                titleText.text = this.title
                titleText.setTextColor(toastTitleColor)
                titleText.gravity = getGravity(titleGravity)
                if(titleTextSize > 0) {
                    titleText.textSize = titleTextSize.toFloat()
                }
                if(titleTypeface != null) {
                    titleText.typeface = titleTypeface
                }
            }
            descriptionText.text = this.message
        } else {
            if(this.title.isEmpty()) {
                descriptionText.text = this.message
                descriptionText.gravity = getGravity(messageGravity)
            } else {
                descriptionText.text = this.title
                descriptionText.gravity = getGravity(titleGravity)
            }
        }
        if(messageTextSize > 0) {
            descriptionText.textSize = messageTextSize.toFloat()
        }
        descriptionText.setTextColor(toastMessageColor)
        if(messageTypeface != null) {
            descriptionText.typeface = messageTypeface
        }
    }

    /**
     * Call after .build for your Toast to show it on screen
     */
    fun show() {
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view
        toast.setGravity(getToastGravity(toastPosition), 0, verticalOffset)
        toast.show()
    }

    /**
     * Get proper gravity data fot Toast vertical position
     */
    private fun getToastGravity(toastPosition: ToastPosition): Int {
        return when (toastPosition) {
            ToastPosition.TOP -> {
                Gravity.TOP
            }
            ToastPosition.CENTER -> {
                Gravity.CENTER
            }
            else -> {
                Gravity.BOTTOM
            }
        }
    }

    /**
     * Get proper gravity data for text fields
     */
    private fun getGravity(gravity: TextPosition): Int {
        return when (gravity) {
            TextPosition.LEFT -> {
                Gravity.START
            }
            TextPosition.CENTER -> {
                Gravity.CENTER
            }
            else -> {
                Gravity.END
            }
        }

    }

    /**
     * Just inflating views based on number of lines
     */
    private fun inflateViews() {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if(this.title.isNotEmpty() && this.message.isNotEmpty()) {
            view = inflater.inflate(R.layout.double_line_layout, null, false)
            titleText = view.findViewById<View>(R.id.info_title) as TextView
        } else {
            view = inflater.inflate(R.layout.single_line_layout, null, false)
        }
        descriptionText = view.findViewById<View>(R.id.info_message) as TextView
        card = view.findViewById<View>(R.id.card_view) as CardView
    }
}