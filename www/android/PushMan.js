var exec = require("cordova/exec");
var PushMan = function () {};
//初始化推送服务
PushMan.prototype.initWork = function(successCallback,errorCallback,username,severurl) {
	exec(successCallback, errorCallback, "PushMan", "initWork",  [username,severurl]);
};
//停止服务
PushMan.prototype.stopWork = function(successCallback,errorCallback) {
	exec(successCallback, errorCallback, "PushMan", "stopWork",  []);
};
//重启服务
PushMan.prototype.resumeWork = function(successCallback,errorCallback) {
	exec(successCallback,errorCallback, "PushMan", "resumeWork",  []);
};
//测试服务是否可用
PushMan.prototype.isPushEnabled = function(callback) {
	exec(callback, null, "PushMan", "isPushEnabled",  []);
};
//设置标签
PushMan.prototype.setTags=function(succallback,failcallback,tags) {
	exec(succallback, failcallback, 'PushMan', 'setTags', [tags]);
};
//删除标签
PushMan.prototype.delTags = function(SuccCallBack,Failcallback,tags) {
	exec(SuccCallBack, Failcallback, "PushMan", "delTags",  [tags]);
};
//显示已有标签列表
PushMan.prototype.listTags = function(callback) {
	exec(callback, null, "PushMan", "listTags",  []);
};
//获取标签信息
PushMan.prototype.getTagInfo = function(callback) {
	exec(callback, null, "PushMan", "getTagInfo",  []);
};
//设置免打扰时间段
PushMan.prototype.setNoDisturbMode = function(startHour,startMinute,endHour,endMinute) {
	exec(null, null, "PushMan", "setNoDisturbMode", [startHour,startMinute,endHour,endMinute]);
};
//设置通知的builder - 尚不可用
PushMan.prototype.setNotificationBuilder = function(id) {
	exec(null, null, "PushMan", "setNotificationBuilder", [id]);
};
//设置通知默认的builder - 尚不可用
PushMan.prototype.setDefaultNotificationBuilder = function() {
	exec(null, null, "PushMan", "setDefaultNotificationBuilder", []);
};
//设置富媒体通知的builder - 尚不可用
PushMan.prototype.setMediaNotificationBuilder = function() {
	exec(null, null, "PushMan", "setMediaNotificationBuilder", []);
};
//设置通知的flags - 尚不可用
PushMan.prototype.setNotificationFlags = function(flags) {
	exec(null, null, "PushMan", "setNotificationFlags", [flags]);
};
//设置通知的defaults - 尚不可用
PushMan.prototype.setNotificationDefaults = function(defaults) {
	exec(null, null, "PushMan", "setNotificationDefaults", [defaults]);
};
//设置通知状态栏的icon - 尚不可用
PushMan.prototype.setStatusbarIcon = function(icon) {
	exec(null, null, "PushMan", "setStatusbarIcon", [icon]);
};
//设置通知样式图片 - 尚不可用
PushMan.prototype.setLayoutDrawable = function(drawableId) {
	exec(null, null, "PushMan", "setLayoutDrawable", [drawableId]);
};
//设置通知样式声音 - 尚不可用
PushMan.prototype.setNotificationSound = function(soundId) {
	exec(null, null, "PushMan", "setNotificationSound", [soundId]);
};
//开启调试模式
PushMan.prototype.enableDebugMode = function(debugEnabled) {
	exec(null, null, "PushMan", "enableDebugMode", [debugEnabled]);
};
//开启精确LBS模式 - 尚不可用
PushMan.prototype.enableLbs = function() {
	exec(null, null, "PushMan", "enableLbs", []);
};
//关闭精确LBS模式 - 尚不可用
PushMan.prototype.disableLbs = function() {
	exec(null, null, "PushMan", "disableLbs", []);
};
//接收到穿透消息时的回调函数
PushMan.prototype.onMessageReceive = function(data) {
	console.log('接收到了穿透消息=>\n')
	console.log(data)
};
//当通知栏消息到达时的回调函数
PushMan.prototype.onNotificationArrived = function(data) {
	console.log('通知栏消息到达时=>\n')
	console.log(data)
};
//当通知栏消息被点击时的回调函数
PushMan.prototype.onNotificationClicked = function(data) {
	console.log('通知栏消息被点击=>\n')
	console.log(data)
};

module.exports = new PushMan();
