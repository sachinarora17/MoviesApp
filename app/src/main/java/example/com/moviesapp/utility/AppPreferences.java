package example.com.moviesapp.utility;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class AppPreferences {

    private static AppPreferences appPreferences;

    /**
     * Factory method
     *
     * @return the instance of {@link AppPreferences}
     */
    public static AppPreferences getInstance() {
        if (appPreferences == null) {
            appPreferences = new AppPreferences();
        }
        return appPreferences;
    }

    /**
     * @param
     * @Module Name/Class		:	setImageInImageView
     * @Author Name            :	Sachin Arora
     * @Date :	Sept 17, 2018
     * @Purpose :	Method to set image in image view using glide
     */

    public static void setImageInImageView(String imgUrl, ImageView imgProfilePic, AppCompatActivity appCompatActivity, ProgressBar progressBar) {

        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        Glide.with(appCompatActivity).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                if (progressBar != null)
                    progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (progressBar != null)
                    progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imgProfilePic);

    }
}
