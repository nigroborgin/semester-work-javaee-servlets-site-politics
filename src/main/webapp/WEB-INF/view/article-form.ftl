<#include "base.ftl">
<#macro title>New article</#macro>

<#macro content>
    <form action="addArticle" method="post">
        Title:
        <input type="text" name="title"/>
        <br>
        View Author:
        <input type="text" name = "viewAuthor">
        <br>
        Text of post:
        <input type="text" name="text"/>
        <br>
        Date:
        <input type="text" name="date"/>
        <br>
        <input type="submit" value="Publish">
    </form>
</#macro>