<#include "base.ftl">
<#macro title>Login</#macro>

<#macro content>
    <h3>Login page!</h3>
    <form action="login" method="post">
        <#if message??><strong>${message}</strong><br></#if>
        Login:
        <input type="text" name="login"/>
        <br>
        Password:
        <input type="password" name="password"/>
        <br>
        <input type="submit" value="Login">
    </form>
</#macro>