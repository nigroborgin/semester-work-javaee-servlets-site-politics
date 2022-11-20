<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    ALL Users:
    <br>
    <br>
    <#list users as u>
        username: ${u.username}
        email: ${u.email}
        <br>
    </#list>
</#macro>

</html>