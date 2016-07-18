package com.heysoo.cordova.baidu.push;
import java.util.ArrayList;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushNotificationBuilder;
import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import android.app.Activity;
import android.content.Intent;

public class PushMan extends  CordovaPlugin{

	 private static final String BAIDU_PUSH_API_KEY = "";//输入你的百度推送api_key
	 private static final String INIT_WORK="initWork";//初始化推送服务
	 private static final String STOP_WORK="stopWork";//停止推送服务
	 private static final String IS_PUSH_ENABLED="isPushEnabled";//测试推送服务是否可用
	 private static final String RESUME_WORK="resumeWork";//重启推送服务
	 private static final String SET_TAGS="setTags";//设置标签
	 private static final String DEL_TAGS="delTags";//删除标签
	 private static final String LIST_TAGS="listTags";//显示已有标签列表
	 private static final String TAGS_INFO="getTagInfo";//获取标签信息
	 private static final String SET_NO_DISTURB_MODE = "setNoDisturbMode";//设置免打扰时段
	 private static final String SET_NOTIFICATION_BUILDER = "setNotificationBuilder";//设置通知的Builder
	 private static final String SET_DEFAULT_NOTIFICATION_BUILDER = "setDefaultNotificationBuilder";//设置默认的通知Builder
	 private static final String SET_MEDIA_NOTIFICATION_BUILDER = "setMediaNotificationBuilder";//设置富媒体通知的Builder
	 private static final String SET_NOTIFICATION_FLAGS = "setNotificationFlags";//设置通知flags
	 private static final String SET_NOTIFICATION_DEFAULTS = "setNotificationDefaults";//设置通知defaults
	 private static final String SET_STATUSBAR_ICON = "setStatusbarIcon";//设置通知状态栏icon
	 private static final String SET_LAYOUT_DRAWABLE = "setLayoutDrawable";//设置通知样式图片
	 private static final String SET_NOTIFICATION_SOUND = "setNotificationSound";//设置通知样式声音
	 private static final String ENABLE_DEBUG_MODE = "enableDebugMode";//开启调试模式
	 private static final String ENABLE_LBS = "enableLbs";//开启精确LBS推送模式
	 private static final String DISABLE_LBS = "disableLbs";//关闭精确LBS推送模式
	 public static CallbackContext cbContext;
	 public static final String TAG = PushMan.class.getSimpleName();
	 private PushNotificationBuilder pnBuilder = null;
	 private CustomPushNotificationBuilder cpnBuilder = null;
	 private static Activity cordovaActivity;
	 private static PushMan instance; //保存实力本身
	 //private static PushSettings pushSettings = null;
	 
	 public PushMan() {
		 instance = this;
	 }
	 
	 @Override
     public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        cordovaActivity = cordova.getActivity();
     }
	 
	 @Override
     public void onDestroy() {
        super.onDestroy();
        cordovaActivity = null;
        instance = null;
     }
	 
	 @Override
	 public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		System.out.println("Plugin execute called with action: " + action);
		cbContext = callbackContext;  
		// Determine which action of the plugin needs to be invoked
		
		if (action.equalsIgnoreCase(INIT_WORK)) {//初始化推送服务
			/*try {
				String packageName = cordova.getActivity().getClass().getPackage().getName();
				PackageManager pkgMgt = cordova.getActivity().getPackageManager();
				Intent mainintent = pkgMgt.getLaunchIntentForPackage(packageName);
				String clazzName = mainintent.getComponent().getClassName();
				Log.d(TAG, "clazzName=" + clazzName);
				final String username= args.getString(0);
				String serverurl = args.getString(1);
				System.out.println("serverurl = "+serverurl);
				Intent intent = new Intent(); 
		        intent.setAction("com.baidu.android.pushservice.action.USERNAME");//发出自定义广播
		        intent.putExtra("username", username);
		        intent.putExtra("serverurl", serverurl);
		        intent.putExtra("clazzName", clazzName);
		        intent.putExtra("packageName", packageName);
		        cordova.getActivity().sendBroadcast(intent);
				cordova.getThreadPool().execute(new Runnable() {
		            public void run() {
		            	initWork(); 	// Thread-safe.
		            }
		        });
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			initWork();
		} else if (action.equalsIgnoreCase(STOP_WORK)) {//停止推送服务
			cordova.getThreadPool().execute(new Runnable() {
	            public void run() {
	            	stopWork(); 	// Thread-safe.
	            }
	        });
		}else if (action.equalsIgnoreCase(RESUME_WORK)) {//重启推送服务
			cordova.getThreadPool().execute(new Runnable() {
	            public void run() {
	            	resumeWork(); 	// Thread-safe.
	            }
	        });
		}else if (action.equalsIgnoreCase(IS_PUSH_ENABLED)) {//测试推送服务是否可用
			Boolean isworkBoolean=isPushEnabled();
			if (isworkBoolean==true) {
				callbackContext.success("true");
			}else {
				callbackContext.success("false");
			}		
		}else if (action.equalsIgnoreCase(SET_TAGS)) {//设置标签
			try {
				final String parameters= args.getString(0);
				cordova.getThreadPool().execute(new Runnable() {
		            public void run() {
		            	setTags(parameters);	// Thread-safe.
		            }
		        });
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (action.equalsIgnoreCase(DEL_TAGS)) {//删除标签
			try {
				final String parameters= args.getString(0);
				cordova.getThreadPool().execute(new Runnable() {
		            public void run() {
		            	delTags(parameters); 	// Thread-safe.
		            }
		        });
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (action.equalsIgnoreCase(LIST_TAGS)) {//显示已有标签列表
			cordova.getThreadPool().execute(new Runnable() {
	            public void run() {
	            	listTags(); 	// Thread-safe.
	            }
	        });
		}else if (action.equalsIgnoreCase(TAGS_INFO)) {//获取标签信息				
			cordova.getThreadPool().execute(new Runnable() {
	            public void run() {
	            	getTagInfo(); 	// Thread-safe.
	            }
	        });
		}else if(action.equalsIgnoreCase(SET_NO_DISTURB_MODE)){//设置免打扰时段
			try {
				final Integer startHour = args.getInt(0);
				final Integer startMinute = args.getInt(1);
				final Integer endHour = args.getInt(2);
				final Integer endMinute = args.getInt(3);
				cordova.getThreadPool().execute(new Runnable() {
		            public void run() {
		            	setNoDisturbMode(startHour,startMinute,endHour,endMinute);
		            	System.out.println("action=>setNoDisturbMode,result=>成功设置免打扰时间段！");
		            	System.out.println(startHour+":"+startMinute+" - "+endHour+":"+endMinute);
		            }
		        });
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_NOTIFICATION_BUILDER)){//设置通知的builder
			try {
				Integer id = args.getInt(0);
				PushNotificationBuilder notificationBuilder = null;
				setNotificationBuilder(id,notificationBuilder);
			}catch (JSONException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_DEFAULT_NOTIFICATION_BUILDER)){//设置通知默认的builder
			PushNotificationBuilder notificationBuilder = null;
			setDefaultNotificationBuilder(notificationBuilder);
		}else if(action.equalsIgnoreCase(SET_MEDIA_NOTIFICATION_BUILDER)){//设置富媒体通知的builder
			PushNotificationBuilder notificationBuilder = null;
			setMediaNotificationBuilder(notificationBuilder);
		}else if(action.equalsIgnoreCase(SET_NOTIFICATION_FLAGS)){//设置通知的flags
			try {
				Integer flags;
				flags = args.getInt(0);
				setNotificationFlags(flags);
				System.out.println("action=>setNotificationFlags,result=>成功设置通知的flags！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_NOTIFICATION_DEFAULTS)){//设置通知的defaults
			try {
				Integer defaults;
				defaults = args.getInt(0);
				setNotificationDefaults(defaults);
				System.out.println("action=>setNotificationDefaults,result=>成功设置通知的defaults！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_STATUSBAR_ICON)){//设置通知状态栏的icon
			try {
				Integer icon = args.getInt(0);
				setStatusbarIcon(icon);
				System.out.println("action=>setStatusbarIcon,result=>成功设置通知的icon！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_LAYOUT_DRAWABLE)){//设置通知样式图片
			try {
				Integer drawableId = args.getInt(0);
				setLayoutDrawable(drawableId);
				System.out.println("action=>setLayoutDrawable,result=>成功设置通知的图片！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(SET_NOTIFICATION_SOUND)){//设置通知样式声音
			try {
				String soundId = args.getString(0);
				setNotificationSound(soundId);
				System.out.println("action=>setNotificationSound,result=>成功设置通知样式声音！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(ENABLE_DEBUG_MODE)){//开启调试模式
			try {
				Boolean debugEnabled = args.getString(0).equalsIgnoreCase("true")?true:false;
				enableDebugMode(debugEnabled);
				System.out.println("action=>enableDebugMode,result=>成功开启调试模式！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equalsIgnoreCase(ENABLE_LBS)){//开启精确LBS模式
			/*Context context;
			enableLbs(context);*/
		}else if(action.equalsIgnoreCase(DISABLE_LBS)){//关闭精确LBS模式
			/*Context context;
			disableLbs(context);*/
		}
		return true;
	}
	 //调用JS方法
	 static void executeJS(final String js) {
        if (instance == null) {
            return;
        }
        cordovaActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                instance.webView.loadUrl("javascript:" + js);
            }
        });
    }
	//初始化推送服务
	public void initWork(){
        cordova.getThreadPool().execute(new Runnable() {
            @SuppressWarnings("unused")
			public void run() {
            	PushManager.startWork(cordova.getActivity(),PushConstants.LOGIN_TYPE_API_KEY,
                    		BAIDU_PUSH_API_KEY==""?BAIDU_PUSH_API_KEY:Utils.getMetaValue(cordova.getActivity(), "api_key"));
            }
        });

	}
	//停止推送服务
	public void stopWork(){
		PushManager.stopWork(cordova.getActivity());
	}
	//重启推送服务
	public void resumeWork(){
		PushManager.resumeWork(cordova.getActivity());
	}
	//测试推送服务是否可用
	public Boolean isPushEnabled(){
		return PushManager.isPushEnabled(cordova.getActivity());
	}
	//设置推送标签
	public void setTags(String tag){
		List<String> tagsList= splitTags(tag);
		PushManager.setTags(cordova.getActivity(), tagsList);
	}
	//删除推送标签
	public void delTags(String tag){
		List<String> tagsList= splitTags(tag);
		PushManager.delTags(cordova.getActivity(), tagsList);
	}
	//将标签字符串转化为标签列表
	private List<String> splitTags(String originalText) {
		List<String> tags = new ArrayList<String>();
		int indexOfComma = originalText.indexOf(',');
		String tag;
		while (indexOfComma != -1) {
			tag = originalText.substring(0, indexOfComma);
			tags.add(tag);

			originalText = originalText.substring(indexOfComma + 1);
			indexOfComma = originalText.indexOf(',');
		}
		tags.add(originalText);
		return tags;
	}
	//获取标签列表
	public void listTags(){
		PushManager.listTags(cordova.getActivity());
	}
	//获取标签信息
	public void getTagInfo(){
		Intent intent = new Intent(); 
		intent.setAction("com.baidu.android.pushservice.action.TAGS");//发出自定义广播
		cordova.getActivity().sendBroadcast(intent);
	}
	//设置免打扰模式
	public void setNoDisturbMode(int startHour, int startMinute, int endHour, int endMinute){
		PushManager.setNoDisturbMode(cordova.getActivity(), startHour, startMinute, endHour, endMinute);
	}
	//设置通知栏的样式，并为样式指定编号
	public void setNotificationBuilder(int id, PushNotificationBuilder notificationBuilder){
		PushManager.setNotificationBuilder(cordova.getActivity(), id, notificationBuilder);
	}
	//设置默认通知栏的样式
	public void setDefaultNotificationBuilder(PushNotificationBuilder notificationBuilder){
		PushManager.setDefaultNotificationBuilder(cordova.getActivity(), notificationBuilder);
	}
	//设置富媒体通知的样式
	public void setMediaNotificationBuilder(PushNotificationBuilder notificationBuilder){
		PushManager.setDefaultNotificationBuilder(cordova.getActivity(), notificationBuilder);
	}
	//设置通知的flag
	public void setNotificationFlags(int flags){
		pnBuilder.setNotificationFlags(flags);
	}
	//设置通知的defaults
	public void setNotificationDefaults(int defaults){
		pnBuilder.setNotificationDefaults(defaults);
	}
	//设置通知状态栏icon
	public void setStatusbarIcon(int icon){
		pnBuilder.setStatusbarIcon(icon);
	}
	//cpnBuilder
	//设置通知样式图片
	public void setLayoutDrawable(int drawableId){
		cpnBuilder.setLayoutDrawable(drawableId);
	}	
	//设置通知样式声音
	public void setNotificationSound(String soundId){
		cpnBuilder.setNotificationSound(soundId);
	}
	//--------- PushSettings -----------
	//开启调试模式
	public static void enableDebugMode(boolean debugEnabled){
		//PushSettings.enableDebugMode(,debugEnabled);
	}
	//开启精确LBS推送模式
	public static void enableLbs(){
		//PushSettings.enableLbs();
	}
	//关闭精确LBS推送模式
	public static void disableLbs(){
		//PushSettings.disableLbs();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // 接收被调方 Activity 返回的数据
		/*switch (resultCode) {
			case RESULT_OK:
			String returnData = data.getExtras().getString("data");
			//返回json数据
			this.cbContext.success(returnData);
			break;
		}*/
	}
}
