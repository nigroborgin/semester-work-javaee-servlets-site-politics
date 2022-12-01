<#include "base.ftl">
<#macro title>Set ${type} info</#macro>

<#macro content>
    <h2><#if type??><#if type == 'posts'>Set Post info<#elseif type == 'articles'>Set Post info</#if></#if></h2>
    <form method="post">
        <label>Title:
            <br><input type="text" name="title" <#if post??>value="${post.title}"</#if> />
        </label>
        <br>

        <#if type == 'articles'>
            <label>View Author:
                <br><input type="text" name="viewAuthor" <#if post??>value="${post.viewAuthor}"</#if> />
            </label>
            <br>

            <label>Date and time:
                <br><input type="datetime-local" name="date" <#if post??>value="${post.date}"</#if> />
            </label>
            <br>
        </#if>

        <label>Text of post:
            <br><textarea name="text" cols="30" rows="10"><#if post??>${post.text}</#if></textarea>
        </label>
        <br>
        <input type="submit" value="save"/>
    </form>
</#macro>