<#include "base.ftl">
<#macro title>Set Book info</#macro>

<#macro content>
    <h2>Set Book info</h2><br>
    <form method="post" enctype="multipart/form-data" >
        <label>Title:
            <input type="text" name="title" <#if book??>value="${book.title}"</#if> />
        </label>
        <br>

        <label>Author:
            <input type="text" name="author" <#if book??>value="${book.author}"</#if> />
        </label>
        <br>

        <label>Description:
            <textarea name="description" id="" cols="30" rows="10"><#if book??>${book.description}</#if></textarea>
        </label>
        <br>

        File:
        <input type="file" name="file" <#if book??>value="${book.fileURL}"</#if> />
        <br>
        <input type="submit" value="save"/>
    </form>
</#macro>
