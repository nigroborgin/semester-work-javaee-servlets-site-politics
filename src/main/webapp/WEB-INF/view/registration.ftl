<#include "base.ftl">
<#macro title>Registration</#macro>

<#macro content>
    <h2>Registration</h2>
    <form action="/politics/reg/" method="post">
        <#if message??><strong>${message}</strong><br></#if>

        <label>Username:
            <br><input type="text" name="username"/>
        </label>
        <br>

        <label>Email:
            <br><input type="text" name="email"/>
        </label>
        <br>

        <label>Password:
            <br><input type="password" name="password1"/>
        </label>
        <br>

        <label>Password:
            <br><input type="password" name="password2"/>
        </label>
        <br>
        <input type="submit" value="Register">
    </form>
</#macro>