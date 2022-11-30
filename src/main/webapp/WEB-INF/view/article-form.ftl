<#include "base.ftl">
<#macro title>Set ${type} info</#macro>

<#macro content>
    <h2><#if type??><#if type == 'posts'>Posts<#elseif type == 'articles'>Articles</#if></#if></h2><br>
    <form method="post">
        <label>Title:
            <input type="text" name="title" <#if post??>value="${post.title}"</#if> />
        </label>
        <br>

        <#if type == 'articles'>
            <label>View Author:
                <input type="text" name="viewAuthor" <#if post??>value="${post.viewAuthor}"</#if> />
            </label>
            <br>

            <label>Date and time:
                <input type="datetime-local" name="date" <#if post??>value="${post.date}"</#if> />
            </label>
            <br>
        </#if>

        <label>Text of post:
            <textarea name="text"><#if post??>${post.text}</#if></textarea>
        </label>
        <br>
        <input type="submit" value="save"/>
    </form>
</#macro>