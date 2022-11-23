<#include "base.ftl">
<#macro title>Users</#macro>

<#macro content>
    ALL Users:
    <br>
    <br>
<#--    <#list users as u>-->
<#--        username: ${u.username}-->
<#--        email: ${u.email}-->
<#--        <br>-->
<#--    </#list>-->

    servletPath: ${servletPath}<br>
    requestURL: ${requestURL}<br>
    requestURI: ${requestURI}<br>
    users: ${users}<br>
</#macro>