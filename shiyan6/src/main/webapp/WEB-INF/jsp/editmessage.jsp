<%@ page import="com.wei.shiyan6.model.Luntan" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>编辑帖子</title>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css">
    <link rel="stylesheet" href="/css/editmessage.css">
    <script src="/js/jquery-3.4.1.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/editmessage.js"></script>
</head>
<body>
<%
    String name = (String) session.getAttribute("name");
    if(name == null || name.equals("")){
%>
    <a href="/luntan" class="post-fail">暂未登录，不可发布帖子。点击此处跳转登录页面</a>
<%
    }else{
%>
        <div id="edit-message">
            <form action="/postMessage" id="forum-message" method="post">
                <div class="post-error">
                    ${postError}
                </div>
<%
        Optional<Luntan> luntan = (Optional<Luntan>) request.getAttribute("updateMsg");
        if(luntan != null){
            String[] updateMsg = luntan.get().getTitle().split(" ",2);
%>
                标题：<input type="text" name="title" id="title-msg" value=<%=updateMsg[0]%>><br>
                详细信息:<br>
                <textarea name="msg" id="detail-msg" cols="50" rows="15" ><%=updateMsg[1]%></textarea>
                <button type="submit" name="updateId" value=<%=luntan.get().getId()%> class="release-btn">修改帖子</button>
<%
        }else{
%>
                标题：<input type="text" name="title" id="title-msg"><br>
                详细信息:<br>
                <textarea name="msg" id="detail-msg" cols="50" rows="15" ></textarea>
                <button type="submit" name="username" value=<%=session.getAttribute("name")%> class="release-btn">发布帖子</button>
                <%
        }
%>
        </form>
    </div>
<%
    }
%>

</body>
</html>