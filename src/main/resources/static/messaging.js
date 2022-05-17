let stompClient = null;

function init() {
    console.log('Trying to connect');
    let socket = new SockJS("message-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
            setConnected();
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/message', function (message) {
                    showMessage(message.body)
                }
            )
        }
    )
}

function setConnected() {
    $("conversation").show();
    $("messages").html("");
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'content': $("#message").val(), 'name': $("#name").val()}))
}

function showMessage(message) {
    $("#messages").prepend("<tr><td>" + message + "</td></tr>")
}

$(function () {
    init();

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#send").click(function () {
        sendMessage();
    });
})