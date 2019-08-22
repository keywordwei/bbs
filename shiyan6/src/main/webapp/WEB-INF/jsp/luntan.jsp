<%@ page import="com.wei.shiyan6.model.Luntan" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wei.shiyan6.model.Nav" %>
<%@ page import="com.wei.shiyan6.model.User" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>论坛</title>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="/css/layout.css">
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/layout.js"></script>
</head>
<body>
<%
    List<Luntan> luntanMsgs = (List<Luntan>) session.getAttribute("allMsg");
    List<Nav> navMsgs = (List<Nav>) session.getAttribute("navMsg");
    List<Map<String,Object>> reviewMsg = (List<Map<String, Object>>) session.getAttribute("reviewMsg");
//    System.out.println(navMsgs.get(2).getName());
//    System.out.println(luntanMsgs.get(0).getReviewnum());
%>
<div id="header">
    <div class="header-content clearfix">
        <div class="header-searchbar v-center">
            <h2>TechLand</h2>
            <input type="text" name="search" id="search">
        </div>
        <div class="header-login v-center">
            <%
//                System.out.println(session.getAttribute("name"));
                String name = (String) session.getAttribute("name");
                if (name == null || name.equals("")) {
            %>
                <a href="#" id="user-register">注册</a>
                <a href="#" id="user-login">登录</a>
            <%
            } else {
            %>
                <a class='login-out'><span class="welcome">welcome to the procedure!</span>&nbsp;&nbsp;<%=name%></a>
                <button id='edit-message' class='login-out loginout-btn'>发帖</button>
                <button id='login-outbtn' class='login-out loginout-btn'>退出登录</button>
            <%
                }
            %>

        </div>
    </div>
</div>
<div id="register-dialog" hidden>
    <div id="errorRegister">

    </div>
    <form action="#" id="registerForm">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="name" id="registerName"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email" id="registerEmail"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="pwd" id="registerPwd"></td>
            </tr>
            <tr>
                <td>重复密码</td>
                <td><input type="password" name="configPwd" id="registerConfigPwd"></td>
            </tr>
        </table>
    </form>
</div>
<div id="login-dialog" hidden>
    <div id="error">
    </div>
    <form id="loginForm">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="name" id="loginName"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="pwd" id="loginPwd"></td>
            </tr>
        </table>
    </form>
</div>
<div id="content" class=" clear-margin">
    <div class="content-body  clearfix">
        <div class="content-left">
            <div class="nav">
                <ul>
                    <%
                        for (Nav item : navMsgs) {
                    %>
                            <li><a href="#"><%=item.getMsg()%></a></li>
                    <%
                        }
                    %>
                </ul>
            </div>

            <div class="newlist">
                <%
                    for (Luntan item : luntanMsgs) {
                %>
                <div class="newlist-item" id="newslistitem<%=item.getId()%>">
                    <div class="big-img">
                        <img src="/img/<%=item.getImgname()%>" alt="">
                        <%--                        <%System.out.println("/img/"+item.getImgname());%>--%>
                    </div>
                    <div>
                        <p><a class="msg" title="<%=item.getTitle()%>"><%=item.getTitle()%></a></p>
                        <div class="clearfix">
                            <div class="detail"><%=item.getDetailmsg()%>&nbsp;&nbsp;发表于<%=item.getPosttime()%>&nbsp;&nbsp;<span class="review-num"><%=item.getReviewnum()%></span></div>
                            <div class="operation">
                                <%
                                    if (name != null && !name.equals("")) {
                                        if (item.getDetailmsg().equals(name)) {
                                %>
                                            <a href="/editmessage/<%=item.getId()%>" title="修改帖子" class="edit"><img src="/img/edit.png" alt=""></a>
                                            <a href="javascript:void(0)" title="评论帖子" class="review" onclick="showReviewView(<%=item.getId()%>)"><img src="/img/review.png" alt=""></a>
                                            <a href="javascript:void(0)" title="删除帖子" class="delete" onclick="deleteReviewMsg(<%=item.getId()%>)"><img src="/img/delete.png" alt=""></a>
                                <%
                                } else {
                                %>
                                            <a href="javascript:void(0)" title="评论帖子" class="review" onclick="showReviewView(<%=item.getId()%>);"><img src="/img/review.png" alt=""></a>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <%
                        if (name != null && !name.equals("")) {
                            if(reviewMsg != null){
                                %>
                                  <div class="show-msg">
                    <%
                                for (Map<String,Object> reviewItem : reviewMsg){
                                    if(item.getId().toString().equals(reviewItem.get("id").toString())){
                                        %>
                                                <div class="review-item"><span class="review-name"><%=reviewItem.get("reviewUserName")%></span>: <span class="review-msg"><%=reviewItem.get("reviewMsg")%></span></div>
                    <%
                                    }
                                }
                                %>
                                      </div>
                                <%
                            }
                        }
                    %>
                    <div class="send-msg" hidden id="sendmsg<%=item.getId()%>">
                        <div class="review-form">
                            <textarea name="reviewmsg" cols="63" rows="1" class="review-box"></textarea>
                            <button class="review-btn" onclick="sendBtnAction(<%=item.getId()%>,'<%=item.getDetailmsg()%>','<%=name%>')">发送</button>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>

        <div class="content-right">
            <div class="content-right-top">
                <div class="item1">
                    <span>今日热议主题</span>
                </div>
                <div class="item2">
                    <div><img src="/img/avatar1.png" alt=""></div>
                    <p>大家如何看待爬虫和爬虫教程近日被推到风口浪尖这种现象？
                    </p>
                </div>
                <div class="item2">
                    <div><img src="/img/avatar1.png" alt=""></div>
                    <p>你们的服务器的 PHP 环境都是自己一个个配置的吗？</p>
                </div>
                <div class="item2">
                    <div><img src="/img/avatar1.png" alt=""></div>
                    <p>问个 nginx 配置二级域名的问题</p>
                </div>
            </div>
            <div class="content-right-bottom">
                <div class="content-right-bottom-header">
                    <span>社区运行状况</span>
                </div>
                <div class="content-right-bottom-center">
                    <div><span>注册会员</span>&nbsp;&nbsp;<span>456666666</span></div>
                    <div><span>主题</span>&nbsp;&nbsp;<span>456666666</span></div>
                    <div><span>回复</span>&nbsp;&nbsp;<span>456666666</span></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <div class="foo">
        <div class="footer-content clearfix">
            <ul>
                <li><a href="">关于</a></li>
                <li><a href="">FAQ</a></li>
                <li><a href="">API</a></li>
                <li><a href="">我们的愿景</a></li>
                <li><a href="">广告投放</a></li>
                <li><a href="">鸣谢</a></li>
            </ul>
        </div>
        <div class="footer-copyright">
            四川师范大学计算机科学学院 魏琴 2016090441
        </div>
        <div class="footer-time">
            更新于2019-6-12 00:00:00:00
        </div>
    </div>
</div>
</body>
</html>