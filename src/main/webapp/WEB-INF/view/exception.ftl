<#include "base.ftl">
<#macro title>Exception details</#macro>

<#macro content>
    <h2>Exception details:</h2><br>
    <#if statusCode??><strong>Status code: </strong>${statusCode}<br></#if>
    <#if uri??><strong>URI: </strong>${uri}<br></#if>
    <#if message??><strong>Message: </strong>${message}<br></#if>
</#macro>