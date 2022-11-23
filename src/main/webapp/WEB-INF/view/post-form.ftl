<#include "base.ftl">
<#macro title>New post</#macro>

<#macro content>
    <form method="post">
        Title:
        <input type="text" name="title"/>
        <br>
        Text of post:
        <input type="text" name="text"/>
        <br>
        <input type="submit" value="Publish">
    </form>
</#macro>