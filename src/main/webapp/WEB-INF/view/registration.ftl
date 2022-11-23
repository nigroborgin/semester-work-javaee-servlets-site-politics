<#include "base.ftl">
<#macro title>Registration</#macro>

<#macro content>
    <h3>Registration page!</h3>
    <form action="reg" method="post">
        Login:
        <input type="text" name="login"/>
        <br>
        <#if message??><strong>${message}</strong><br></#if>
        Password:
        <input type="password" name="password1"/>
        <br>
        Password:
        <input type="password" name="password2"/>
        <br>
        <input type="submit" value="Register">
    </form>
</#macro>