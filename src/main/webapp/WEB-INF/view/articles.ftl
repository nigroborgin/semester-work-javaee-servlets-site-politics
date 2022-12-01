<#include "base.ftl">
<#if post??>
    <#macro title>${post.title}</#macro>
<#else>
    <#macro title><#if type??><#if type == 'posts'>Posts<#elseif type == 'articles'>Articles</#if></#if></#macro>
</#if>

<#macro content>
    <h2>
        <#if post??>
            ${post.title}
        <#else>
            <#if type??>
                <#if type == 'posts'>
                    All Posts
                <#elseif type == 'articles'>
                    All Articles
                </#if>
            </#if>
        </#if>
    </h2>
    <#if message??>
        <strong>${message}</strong>
        <br>
    </#if>
    <#if role??>
        <#if type == 'articles'>
            <#if (role == 'admin')>
                <button onclick="location.href='/politics/articles/add/'">Add New article</button>
            </#if>
        <#elseif type == 'posts'>
            <button onclick="location.href='/politics/posts/add/'">Add New post</button>
            <button onclick="location.href='/politics/posts/my/'">Show My posts</button>
            <button onclick="location.href='/politics/posts/'">Show All posts</button>
            <br><br>
        </#if>
    </#if>

    <#if postList??>
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
            <br>
        </#list>
    <#elseif post??>
<#--        Title: ${post.title}<br>-->
        Author: <strong>${post.viewAuthor}</strong><br>
        ${post.date}<br>
        <#if type == 'posts'>
            <button onclick="location.href='/politics/posts/fromuser/?iduser=${post.userId}'">
                More posts from <strong>${post.viewAuthor}</strong>
            </button>
            <br>
        </#if>
        <#if showEditButtons>
            <button onclick="location.href='/politics/${type}/edit/?id=${post.id}'">edit</button>
            <form method="post" action="/politics/${type}/delete/?id=${post.id}">
                <input type="submit" value="delete">
            </form>
        </#if>
        <p>
        <br>${post.text}<br>
        </p>
    <#else>
        <br><strong>no ${type}</strong>
    </#if>
</#macro>