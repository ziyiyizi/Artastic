/**
 * 
 */

    var websocket = null;

    //此处添加用户名
    var userno="jack";

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket/"+userno);
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
    	console.log("WebSocket连接成功");
        //此处获取未读消息数目 fetch/+1
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        //setMessageInnerHTML(event.data);
    	console.log("接收到一条消息")
    	console.log(event.data)
        //此处未读消息加一 fetch/+1
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
    	console.log("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(sendMessage) {
        document.getElementById('message').innerHTML += sendMessage + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息 此处添加发送消息的逻辑 评论 关注时 具体发送内容还是在后台添加 此处只是提示未读消息要+1
    function send() {
    	var ToSendUserno = document.getElementById('receive').value;
        var message = document.getElementById('text').value;//要发送的消息内容
        var now=getNowFormatDate();//获取当前时间
        document.getElementById('message').innerHTML += (now+"发送人："+userno+'<br/>'+"---"+message) + '<br/>';
        document.getElementById('message').style.color="red";
        message=message+"|"+ToSendUserno//将要发送的信息和内容拼起来，以便于服务端知道消息要发给谁
        websocket.send(message);
    }
    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
} 