<#include "base.ftl">

<#macro title><#if title1??>${title1}</#if></#macro>

<#macro content>
    <h2><#if title1??>${title1}</#if></h2>
    <#if message??>
        <br>
        <strong>${message}</strong>
        <br>
    </#if>

    <#if role??>
        <button onclick="location.href='/politics/posts/add/'">Add New post</button>
        <button onclick="location.href='/politics/posts/my/'">Show My posts</button>
    </#if>
    <button onclick="location.href='/politics/posts/'">Show All posts</button>
    <#if postList??>
        <br><br>
        <#list postList as post>
            <div>
                <strong>${post.title}</strong><br>
                Author: ${post.viewAuthor}<br>
                ${post.date}<br>
                <button onclick="location.href='/politics/${type}/?id=${post.id}'">read</button>
                <#if showEditButtons>
                    <button onclick="location.href='/politics/${type}/edit/?id=${post.id}'">edit</button>
                    <form method="post" action="/politics/${type}/delete/?id=${post.id}">
                        <input type="submit" value="delete">
                    </form>
                </#if>
            </div>
        </#list>
    <#else>
        <br><strong>No Posts</strong>
    </#if>
</#macro>