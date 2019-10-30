package com.example.download;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appsend.R;

public class SuccinctProgress {

	private static ProgressDialog pd;
	private static TextView mProgressMessage;
	public static void showSuccinctProgress(Context context, String message,boolean isCanceledOnTouchOutside, boolean isCancelable) {

		pd = new ProgressDialog(context, R.style.succinctProgressDialog);
		pd.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
		pd.setCancelable(isCancelable);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		View view = LayoutInflater.from(context).inflate(
				R.layout.succinct_progress_content, null);
		ImageView mProgressIcon = (ImageView) view
				.findViewById(R.id.progress_icon);
		mProgressIcon.setImageResource(R.drawable.icon_progress_style1);
		mProgressMessage = (TextView) view
				.findViewById(R.id.progress_message);
		mProgressMessage.setText(message);
		new AnimationUtils();
		Animation jumpAnimation = AnimationUtils.loadAnimation(context,
				R.anim.succinct_animation);
		mProgressIcon.startAnimation(jumpAnimation);
		pd.show();
		pd.setContentView(view, params);
	}

	
	public static boolean isShowing() {

		if (pd != null && pd.isShowing()) {

			return true;
		}
		return false;

	}

	
	public static void dismiss() {

		if (isShowing()) {

			pd.dismiss();
		}

	}
	public static void setMessage(String message) {

		if (isShowing()) {

			mProgressMessage.setText(message);
		}

	}
}
