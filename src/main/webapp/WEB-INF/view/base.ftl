<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/WEB-INF/style/bootstrap.min.css">
    <link rel="script" href="/WEB-INF/js/bootstrap.min.js">
    <title><@title></@title></title>
</head>

<body>

<div id="header">
    <center>
        <h1>POLITICS SITE</h1>
        <nav>
            <a href="/politics/main/">Main page</a>
            <a href="/politics/users/">Users</a>
            <a href="/politics/articles/">Articles</a>
            <a href="/politics/posts/">Posts</a>
            <a href="/politics/books/">Books</a>
            <#if username??>
                <strong>USER: ${username}</strong>
                <a href="/politics/profile/">Profile</a>
                <a href="/politics/logout/">Logout</a>
            <#else>
                <a href="/politics/login/">Login</a>
                <a href="/politics/reg/">Registration</a>
            </#if>
        </nav>
    </center>
</div>


<div class="content">
    <center>
        <@content></@content>
    </center>
</div>

</body>

</html>