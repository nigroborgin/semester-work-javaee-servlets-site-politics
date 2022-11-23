<#include "base.ftl">
<#if post??>
    <#macro title>${article.title}</#macro>
<#else>
    <#macro title>Articles</#macro>
</#if>

<#macro content>
    <#if message??>
        <strong>${message}</strong>
        <br>
    </#if>
    <button onclick="location.href='/politics/addArticle'">Add new your article</button>
    <#if postList??>
        <#list postList as p>
            <div>
                Title: ${p.title}<br>
                Author: ${p.viewAuthor}<br>
                Date: ${p.date}<br>
                <a href="/politics/articles?id=${p.id}">read</a>
            </div>
            <br>
        </#list>
    <#elseif post??>
        <div>
            Title: ${post.title}<br>
            Author: ${post.viewAuthor}<br>
            Date: ${post.date}<br>
            Text: <br>${post.text}<br>
        </div>
    <#else>
        <strong>NO POSTS</strong>
    </#if>
</#macro>