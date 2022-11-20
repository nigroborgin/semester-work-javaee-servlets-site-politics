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

    </div>

    <#if username??>
        <h3>USER: ${username}</h3>
        <a href="/politics/logout">Logout</a>
    <#else>
        <a href="/politics/login">Login</a>
    </#if>

    <div class="content1">
        <div class="content"><@content></@content></div>
    </div>

</body>

</html>