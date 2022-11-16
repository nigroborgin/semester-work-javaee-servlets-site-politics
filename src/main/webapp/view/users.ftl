<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    ALL Users:
    <br>
    <br>
    <#list users as u>
        ${u.username} ${u.email}
        <br>
    </#list>
</#macro>

</html>