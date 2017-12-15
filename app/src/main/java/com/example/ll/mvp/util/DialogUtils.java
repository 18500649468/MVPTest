package com.example.ll.mvp.util;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ll.mvp.ContextApplication;
import com.example.ll.mvp.HomeActivity;
import com.example.ll.mvp.MainActivity;
import com.example.ll.mvp.R;
import com.example.ll.mvp.view.CustomDialog;


import java.util.Timer;
import java.util.TimerTask;

public class DialogUtils {

	//返回键退出应用--------------------------
	public static void showDialog(final Context context, final ContextApplication myApplication) {
		final String simpleName = context.getClass().getSimpleName();
		CustomDialog.Builder dialog = new CustomDialog.Builder(context);
		dialog.setTitle("温馨提示");
		dialog.setMessage("亲,您确定要退出应用吗？");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				 if ("LoginActivity".equals(simpleName)) {
//					LoginActivity consumeActy = (LoginActivity) context;
					dialog.dismiss();
//					consumeActy.finish();
//					 System.exit(0);
					 android.os.Process.killProcess(android.os.Process.myPid());
				}  else if ("HomeActivity".equals(simpleName)) {
					HomeActivity usercActy = (HomeActivity) context;
					dialog.dismiss(); //
					 usercActy.finish();
				}else if ("MainActivity".equals(simpleName)) {
					 MainActivity shareActy = (MainActivity) context;
					 dialog.dismiss(); //
					 shareActy.finish();
				 }
//				}else if ("UpGradeActy".equals(simpleName)) {
//					UpGradeActy upActy = (UpGradeActy) context;
//					dialog.dismiss(); //
//					upActy.finish();
//				}else if ("FinancialServiceActy".equals(simpleName)) {
//					FinancialServiceActy financialActy = (FinancialServiceActy) context;
//					dialog.dismiss(); //
//					financialActy.finish();
//				}else if ("ReceivablesPageActy".equals(simpleName)) {
//					ReceivablesPageActy receivablesActy = (ReceivablesPageActy) context;
//					dialog.dismiss(); //
//					receivablesActy.finish();
//				}else {
////					MainActivity acitivty = (MainActivity) DataStore.getMap().get("homeActivity");
////					acitivty.finish();
////					 System.exit(0);
//					 android.os.Process.killProcess(android.os.Process.myPid());
//				 }
				if(myApplication!=null){
//					myApplication.setOpeningName("");
//					myApplication.setBranchesId("");
//					myApplication.setReceiverMoneyResVO(null);
					myApplication.setUserVoinfo(null);
				}
			}
		});
		dialog.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		dialog.create(false).show();
	}
		public static ProgressDialog showProDialog(Context context){
						ProgressDialog proDialog = new ProgressDialog(context);
			proDialog.setMessage("加载数据中，请稍候...");
			proDialog.setCanceledOnTouchOutside(false);
			proDialog.setCancelable(false);
			return proDialog;
		}
	    //显示进度条
		public static Dialog showDialog(final Context context) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
			LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
			// main.xml中的ImageView
			ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
			TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
			// 加载动画
			Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
					context, R.anim.loading_animation);
			// 使用ImageView显示动画
			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
			tipTextView.setText("玩命加载中，请稍后...");// 设置加载信息
			final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
			loadingDialog.setCancelable(false);// 不可以用“返回键”取消
			loadingDialog.setCanceledOnTouchOutside(false);
			loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					if (loadingDialog!=null&&loadingDialog.isShowing()){
						loadingDialog.dismiss();
					}
				}
			},8000,8000);
			return loadingDialog;
		}
//
//		public static Dialog showLog(Context context){
//			LayoutInflater inflater = LayoutInflater.from(context);
//			View v = inflater.inflate(R.layout.load_dialog, null);// 得到加载view
//			LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
//			// main.xml中的ImageView
//			ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
////			TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
//			// 加载动画
//			Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//					context, R.anim.loading_animation);
//			// 使用ImageView显示动画
//			spaceshipImage.startAnimation(hyperspaceJumpAnimation);
////			tipTextView.setText("玩命加载数据中...");// 设置加载信息
//			Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
//			loadingDialog.setCancelable(false);// 不可以用“返回键”取消
//			loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
//					LinearLayout.LayoutParams.MATCH_PARENT,
//					LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//			return loadingDialog;
//		}
//
//
//
//
//		public static void showMsgDialog(Context context, String msgInfo){
//			CustomDialog.Builder builder = new CustomDialog.Builder(context);
//				builder.setTitle("友情提示");
//				builder.setMessage(msgInfo);
//				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								dialog.cancel();
//							}
//						});
//
//				builder.create(true).show();
//
//
//			}

}
