<#include "base.ftl">
<#macro title>Profile</#macro>

<#macro content>
    <h2>Profile</h2>

    <div>
        <#if user??><img width="200" src="${user.pictureURL}" alt="/politics/picture/default.bmp"/></#if>
        <br>
        Username:
        <#if user??>${user.username}</#if>
        <br>
        Email:
        <#if user??>${user.email}</#if>
        <br>
        Role:
        <#if user??>${user.getRole().getName()}</#if>
        <br>
        <button onclick="location.href='/politics/users/edit/?id=${id}'">edit profile</button>
        <button onclick="location.href='/politics/posts/my/'">Show My posts</button>
    </div>
</#macro>