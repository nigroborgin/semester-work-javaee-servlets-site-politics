<#include "base.ftl">
<#if post??>
    <#macro title>${post.title}</#macro>
<#else>
    <#macro title><#if type??><#if type == 'posts'>Posts<#elseif type == 'articles'>Articles</#if></#if></#macro>
</#if>

<#macro content>
    <h2><#if type??><#if type == 'posts'>Posts<#elseif type == 'articles'>Articles</#if></#if></h2><br>
    <#if message??>
        <strong>${message}</strong>
        <br>
    </#if>
    <#if role??>
        <#if type == 'posts'>
            <button onclick="location.href='/politics/posts/add/'">Add new post</button>
        <#elseif type == 'articles'>
            <#if (role == 'admin')>
                <button onclick="location.href='/politics/articles/add/'">Add new article</button>
            </#if>
        </#if>
    </#if>
    <#if postList??>
        <#list postList as post>
            <div>
                Title: ${post.title}<br>
                Author: ${post.viewAuthor}<br>
                Date and time: ${post.date}<br>
                <button onclick="location.href='/politics/${type}/?id=${post.id}'">read</button>
                <#if role??>
                    <#if (role == 'admin' || id == post.userId)>
                        <button onclick="location.href='/politics/${type}/edit/?id=${post.id}'">edit</button>
                        <form method="post" action="/politics/${type}/delete/?id=${post.id}">
                            <input type="submit" value="delete">
                        </form>
                    </#if>
                </#if>
            </div>
        </#list>
    <#elseif post??>
        <div>
            Title: ${post.title}<br>
            Author: ${post.viewAuthor}<br>
            Date and time: ${post.date}<br>
            <#if role??>
                <#if (role == 'admin' || id == post.userId)>
                    <button onclick="location.href='/politics/${type}/edit/?id=${post.id}'">edit</button>
                    <form method="post" action="/politics/${type}/delete/?id=${post.id}">
                        <input type="submit" value="delete">
                    </form>
                </#if>
            </#if>
            Text: <br>${post.text}<br>
        </div>
    <#else>
        <br><strong>no ${type}</strong>
    </#if>
</#macro>