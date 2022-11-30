<#include "base.ftl">
<#macro title>Users</#macro>

<#macro content>
    <table border='1'>
        <tr>
            <th>ID</th>
            <th>Picture</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <#if role??><#if role=='admin'>
                <th>Edit</th>
                <th>Delete</th>
            </#if></#if>
        </tr>
        <#if userList??>
            <#list userList as user>
                <tr>
                    <td>${user.id}</td>
                    <td>
                        <center>
                            <img width="35"
                                 <#if user.pictureURL??>src="${user.pictureURL}"</#if>
                                 alt="/politics/picture/default.bmp" />
                        </center>
                    </td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.getRole().getName()}</td>
                    <#if role??><#if role=='admin'>
                        <td>
                            <button onclick="location.href='/politics/users/edit/?id=${user.id}'">edit</button>
                        </td>
                        <td>
                            <form method="post" action="/politics/users/delete/?id=${user.id}">
                                <button type="submit">delete</button>
                            </form>
                        </td>
                    </#if></#if>
                </tr>
            </#list>
        </#if>
    </table>
</#macro>