$(function () {
    //设置导航栏颜色
    // $(".nav li:first>a").css({
    //     "background-color": "rgb(51, 51, 68)",
    //     "color": "white",
    //     "border-radius": "5px",
    // });
    // $(".nav li>a").each(function () {
    //     $(this).mouseover(function () {
    //         $(".nav li>a").each(function () {
    //             $(this).css({
    //                 "background-color": "white",
    //                 "color": "black",
    //                 "border-radius": "0px",
    //             });
    //         });
    //         $(this).css({
    //             "background-color": "rgb(51, 51, 68)",
    //             "color": "white",
    //             "border-radius": "5px",
    //         });
    //     });
    // });

    $("#register-dialog").dialog({
        title: "用户注册",
        modal: true,
        autoOpen: false,
        position: {my: "center center", of: "body", at: "center center"},
        resizable: false,
        draggable: true,
        buttons: {
            注册: function () {
                if ($("#registerName").val() === "" || $("#reigsterPwd").val() === "" || $("#registerEmail") === "" ||
                    $("#registerConfigPwd").val() === "") {
                    $("#errorRegister").html("用户名或密码或邮箱不能为空");
                } else if ($("#registerConfigPwd").val() !== $("#registerPwd").val()) {
                    $("#errorRegister").html("两次密码输入不一致，请重新输入");
                } else {
                    $.ajax({
                        type: 'post',
                        url: "/register/doRegister",
                        data: JSON.stringify({
                            name: $("#registerName").val(),
                            pwd: $("#registerPwd").val(),
                            email: $("#registerEmail").val()
                        }),
                        dataType: "html",
                        cache: false,
                        processData: false,
                        contentType: "application/json;charset=UTF-8",
                        success: function (data) {
                            // alert("ajaxSuccess");
                        },
                        error: function (data) {
                            alert("ajaxFail");
                        }
                    });
                    $("#register-dialog").dialog("close");
                    $("#errorReigster").html("");
                    $("#login-dialog").dialog("open");
                }
            },
            取消: function () {
                $("#register-dialog").dialog("close");
            }
        }

    });
    var username;
    $("#login-dialog").dialog({
        title: "用户登录",
        modal: true,
        autoOpen: false,
        position: {my: "right top", of: ".header-login", at: "right bottom"},
        resizable: false,
        draggable: false,
        show: {effect: "slideDown", duration: "fast"},
        hide: {effect: "slideUp", duration: "normal"},
        buttons: {
            登录: function () {
                $.ajax({
                    type: 'post',
                    url: "/login/doLogin",
                    data: JSON.stringify({
                        name: $("#loginName").val(),
                        pwd: $("#loginPwd").val()
                    }),
                    dataType: "html",
                    cache: false,
                    processData: false,
                    contentType: "application/json;charset=UTF-8",
                    success: function (data) {
                        // console.log("successAjax");
                        // alert(data.substring(0,7));
                        if (data.substring(0, 7) === "success") {
                            username = data.substring(7);
                            $("#user-register").css("display", "none");
                            $("#user-login").css("display", "none");
                            $(".header-login").append("<a class='login-out'><span class=\"welcome\">welcome to the procedure!</span>&nbsp;&nbsp;"+username+"</a>");
                            $(".header-login").append("<button id='edit-message' class='login-out'>发帖</button>")
                            $(".header-login").append("<button id='login-outbtn' class='login-out'>退出登录</button>");
                            // console.log(username);
                            $("#error").html("");
                            window.location.href = "/luntan";
                            // $("#login-dialog").dialog("close");
                        } else {
                            $("#error").html(data);
                            $("#login-dialog").dialog("open");
                        }
                    },
                    error: function () {
                        alert("failajax");
                    }
                });
            },
            取消: function () {
                $("#login-dialog").dialog("close");
            }
        }
    });
    $(document).on("click", "#login-outbtn", function () {
        $(".login-out").remove();
        $("#user-register").css("display", "inline-block");
        $("#user-login").css("display", "inline-block");
        $.ajax({
            type: "get",
            url: "/login/doLoginout",
            dataType: "html",
            data: "",
            success: function (data) {
                window.location.href = "/luntan";
                console.log(data);
            },
            error: function (data) {
                console.log("ajaxerror");
            }
        });
    });
    $(document).on("click", "#edit-message", function () {
        console.log("editmessage");
        window.location.href = "/editmessage";
    });
    $("#user-register").click(function () {
        $("#register-dialog").dialog("open");
    });

    $("#user-login").click(function () {
        $("#login-dialog").dialog("open");
    });
    $(".big-img").tooltip({
        items: "img",
        position: {my: "left+40 center", at: "right center"},
        content: function () {
            return "<img class='avator-img'src='" + $(this).attr('src') + "' alt='" + $(this).attr("alt") + "'/>";
        }
    });

});

function showReviewView(id) {
    $("#sendmsg" + id).toggle({
        function() {
            $(this).show();
        },
        function() {
            $(this).hide();
        }
    });
}
function sendBtnAction(id,owerUserName,reviewUserName){
    var reviewMsg = $("#sendmsg" + id).children(".review-form").children(".review-box").val();

    $.ajax({
        type:"get",
        url:"/doReview",
        dataType:"json",
        data:{
            id:id,
            owerUserName:owerUserName,
            reviewUserName:reviewUserName,
            reviewMsg:reviewMsg
        },
        success: function (data) {
            console.log(data)
            $("#newslistitem"+id+" .review-num").text(data);
            $("#sendmsg" + id).hide();
            $("#sendmsg"+id).before("<div class=\"show-msg\">\n" +
                "                                <div class=\"review-item\"><span class=\"review-name\">"+reviewUserName+"</span>: <span class=\"review-msg\">"+reviewMsg+"</span></div>\n" +
                "                            </div>" );
        },
        error: function () {
            alert("sendmessageAjaxError");
        }
    });
}

function deleteReviewMsg(id) {
    $.ajax({
        type: "get",
        url: "/deletemessage/" + id,
        success: function () {
            // console.log("deleteAjaxSuccess");
            $("#newslistitem"+id).remove();
        },
        error: function () {
            // console.log("deleteAjaxError");
        }
    });
}