package com.heysoo.cordova.baidu.push;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.baidu.android.pushservice.PushMessageReceiver;
import android.content.Context;
import android.util.Log;

public class PushReceiver extends PushMessageReceiver {
	private static final String PUSH_MSG_TAG = "push_msg_tag";
	
	@Override
	//当设备绑定时触发该方法，该方法也是PushManager.startWork()的回调
	/*
		context: BroadcastReceiver的执行Context
		errorCode: 绑定接口返回值，0 - 成功
		appid: 应用id，errorCode非0时为null
		userId: 应用user id，errorCode非0时为null
		channelId: 应用channel id，errorCode非0时为null
		requestId: 向服务端发起的请求id，在追查问题时有用
	*/
	public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onBind");
		if(errorCode == 0){
			try {
				JSONObject args = new JSONObject("{appid:"+appid+",userId:"+userId+",channelId:"+channelId+",requestId:"+requestId+"}");
				PushMan.cbContext.success(args);//回调给JS
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else PushMan.cbContext.error(errorCode);//回调给JS
	}

	@Override
	//当接收到穿透消息时触发该方法
	/*
	 * context 上下文
	 * message 推送的消息
	 * customContentString 自定义内容，为空或者json字符串
	 * */
	public void onMessage(Context context, String message, String customContentString) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onMessage");
		
		try {
			JSONObject data = new JSONObject("{message:"+message+",customContentString:"+customContentString+"}");
			//在这里需要主动调用JS的方法
			String format = "window.plugin.pushMan.onMessageReceive(%s);";
			final String js = String.format(format, data.toString());
			PushMan.executeJS(js);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	//当通知栏的消息被用户点击时触发该方法
	/*
	 * context 上下文
	 * title 推送的通知的标题
	 * description 推送的通知的描述
	 * customContentString 自定义内容，为空或者json字符串
	 * */
	public void onNotificationClicked(Context context, String title, String description, String customContentString) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onNotificationClicked");
		
		try {
			JSONObject data = new JSONObject("{title:"+title+",description:"+description+",customContentString:"+customContentString+"}");
			String format = "window.plugin.pushMan.onNotificationClicked(%s);";
			final String js = String.format(format, data.toString());
			PushMan.executeJS(js);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	//当接收到通知时触发该方法
	/*
	 * context 上下文
	 * title 推送的通知的标题
	 * description 推送的通知的描述
	 * customContentString 自定义内容，为空或者json字符串
	 * */
	public void onNotificationArrived(Context context, String title, String description, String customContentString) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onNotificationArrived");
		
		try {
			JSONObject data = new JSONObject("{title:"+title+",description:"+description+",customContentString:"+customContentString+"}");
			String format = "window.plugin.pushMan.onNotificationArrived(%s);";
			final String js = String.format(format, data.toString());
			PushMan.executeJS(js);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	//当设置标签时触发该方法
	/*
	 * context 上下文
	 * errorCode 错误码，0表示某些tag已经设置成功，非0表示所有tag的设置均失败
	 * successTags 设置成功的tag
	 * failTags 设置失败的tag
	 * requestId 分配给对云推送的请求的id
	 * */
	public void onSetTags(Context context, int errorCode, List sucessTags, List failTags, String requestId) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onSetTags");
		
		if(errorCode == 0){
			try {
				JSONObject args = new JSONObject("{sucessTags:"+sucessTags+",failTags:"+failTags+",requestId:"+requestId+"}");
				PushMan.cbContext.success(args);//回调给JS
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else PushMan.cbContext.error(errorCode);//回调给JS
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	//当删除标签时触发该方法
	/*
	 * context 上下文
	 * errorCode 错误码，0表示某些tag已经设置成功，非0表示所有tag的设置均失败
	 * successTags 设置成功的tag
	 * failTags 设置失败的tag
	 * requestId 分配给对云推送的请求的id
	 * */
	public void onDelTags(Context context, int errorCode, List sucessTags, List failTags, String requestId) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onDelTags");
		
		if(errorCode == 0){
			try {
				JSONObject args = new JSONObject("{sucessTags:"+sucessTags+",failTags:"+failTags+",requestId:"+requestId+"}");
				PushMan.cbContext.success(args);//回调给JS
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else PushMan.cbContext.error(errorCode);//回调给JS
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	//当显示标签时触发该方法
	/*
	 * context 上下文
	 * errorCode 错误码，0表示某些tag已经设置成功，非0表示所有tag的设置均失败
	 * Tags 当前应用设置的所有tag
	 * requestId 分配给对云推送的请求的id
	 * */
	public void onListTags(Context context, int errorCode, List tags, String requestId) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onListTags");
		
		if(errorCode == 0){
			try {
				JSONObject args = new JSONObject("{tags:"+tags+",requestId:"+requestId+"}");
				PushMan.cbContext.success(args);//回调给JS
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else PushMan.cbContext.error(errorCode);//回调给JS
	}
	
	@Override
	//当设备解绑时触发该方法
	/*
	 * context 上下文
	 * errorCode 错误码，0表示某些tag已经设置成功，非0表示所有tag的设置均失败
	 * requestId 分配给对云推送的请求的id
	 * */
	public void onUnbind(Context context, int errorCode, String requestId) {
		// TODO Auto-generated method stub
		Log.d(PUSH_MSG_TAG, "=> onUnbind");
		
		if(errorCode == 0){
			try {
				JSONObject args = new JSONObject("{errorCode:"+errorCode+",requestId:"+requestId+"}");
				PushMan.cbContext.success(args);//回调给JS
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else PushMan.cbContext.error(errorCode);//回调给JS
	}

}
