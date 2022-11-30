<#include "base.ftl">
<#macro title>Profile</#macro>

<#macro content>
    <h2>Profile</h2><br>

    <div>
        <#if user??><img width="100" src="${user.pictureURL}" alt="/politics/picture/default.bmp"/></#if>
        <br>
        ID:
        <#if id??>${id}</#if>
        <br>
        Username:
        <#if user??>${user.username}</#if>
        <br>
        Password:
        <br>
        Email:
        <#if user??>${user.email}</#if>
        <br>
        Role:
        <#if user??>${user.getRole().getName()}</#if>
    </div>
    <button onclick="location.href='/politics/users/edit/?id=${id}'">edit</button>

</#macro>