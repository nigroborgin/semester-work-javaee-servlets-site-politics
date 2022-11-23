<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
</head>

<body>

<div id="header">
    <h1>POLITICS SITE</h1>
    <a href="/politics/main">Main page</a>
    <a href="/politics/users">Users</a>
    <a href="/politics/articles">Articles</a>
    <a href="/politics/posts">Posts</a>
    <a href="/politics/books">Books</a>
    <#if username??>
    <strong>USER: ${username}</strong>
    <a href="/politics/profile">Profile</a>
    <a href="/politics/logout">Logout</a>
    <#else>
    <a href="/politics/login">Login</a>
    <a href="/politics/reg">Registration</a>
    </#if>
</div>


<div class="content">
    <br>
    <@content></@content>
</div>

</body>

</html>