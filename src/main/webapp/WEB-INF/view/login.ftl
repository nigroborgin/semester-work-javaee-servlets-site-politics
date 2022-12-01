<#include "base.ftl">
<#macro title>Login</#macro>

<#macro content>
    <h2>Login</h2>
    <form action="/politics/login/" method="post">
        <#if message??><strong>${message}</strong><br></#if>
        <label>Username:
            <br><input type="text" placeholder="Enter your username" name="username"/>
        </label>
        <br>
        <label>Password:
            <br><input type="password" placeholder="Enter your password" name="password"/>
        </label>
        <br>
        <label>Save user <input type="checkbox" name="saveuser"/></label>
        <br>
        <input type="submit" value="Login">
    </form>
</#macro>