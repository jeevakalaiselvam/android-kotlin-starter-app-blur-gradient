package com.gamerguide.android.starterapp.helpers

import android.animation.Animator
import android.app.Activity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import com.squareup.picasso.MemoryPolicy
import android.util.TypedValue
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

class BlurHelper {


    //Setup Blur on Image, Check if already stored image id is present in local storage, If not grab a new random one
    fun setupImageBlurBackground(activity: Activity, background: ImageView , refresh: Boolean = false) {

      if (refresh) SimpleStorage.intoStorage(activity,"COLOR_THEME",ThemeHelper.getRandomTheme())

        val theme: Int = SimpleStorage.fromStorage(activity,"COLOR_THEME",520720)

            Picasso.get()
            .load(getGameImageURL(theme.toString()))
            .transform(BlurTransformation(activity))
            .into(background)
        setAlphaAnimation(background)
    }

    //Load the image using Picasso Image Library
    fun picassoLoad(imageID: Int, H: Int, V: Int, activity: Activity, image: ImageView?) {
        Picasso.get().load(imageID)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .resize(
                getPixelfromDP(activity, H),
                getPixelfromDP(activity, V)
            ).into(image)
    }

    //Calcualate the DP multiplier for current screen
    fun getPixelfromDP(activity: Activity, dp: Int): Int {
        val r = activity.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).toInt()
    }

    //Create a transition animation on the image to generate a cool visual effect
    @SuppressLint("Recycle")
    private fun setAlphaAnimation(v: View) {
        val fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f)
        fadeOut.duration = 2000
        val fadeIn = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f)
        fadeIn.duration = 2000
        val mAnimationSet = AnimatorSet()
        mAnimationSet.play(fadeIn)
        mAnimationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }
        })
        mAnimationSet.start()
    }

    //URL endpoints for the image
    companion object {
        fun getGameImageURL(gameID: String): String {
            return "https://steamcdn-a.akamaihd.net/steam/apps/$gameID/header.jpg"
        }
    }
}