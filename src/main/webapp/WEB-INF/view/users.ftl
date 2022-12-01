<#include "base.ftl">
<#macro title>Users</#macro>

<#macro content>
    <table border='1'>
        <tr>
            <th>ID</th>
            <th>Picture</th>
            <th>Username</th>
            <th>Role</th>
            <th>Posts</th>
            <#if role??><#if role=='admin'>
                <th>Email</th>
                <th>Edit</th>
                <th>Delete</th>
            </#if></#if>
        </tr>
        <#if userList??>
            <#list userList as user>
                <tr>
                    <td>
                        <center>${user.id}</center>
                    </td>
                    <td>
                        <center>
                            <img height="40" <#if user.pictureURL??>src="${user.pictureURL}"</#if>
                                 alt="/politics/picture/default.bmp"/>
                        </center>
                    </td>
                    <td>
                        <center>${user.username}</center>
                    </td>
                    <td>
                        <center>${user.getRole().getName()}</center>
                    </td>
                    <td>
                        <center><a href="/politics/posts/fromuser/?iduser=${user.id}">show posts</a></center>
                    </td>

                    <#if role??><#if role=='admin'>
                        <td>
                            <center>${user.email}</center>
                        </td>
                        <td>
                            <center>
                                <button onclick="location.href='/politics/users/edit/?id=${user.id}'">edit</button>
                            </center>
                        </td>
                        <td>
                            <center>
                                <form method="post" action="/politics/users/delete/?id=${user.id}">
                                    <button type="submit">delete</button>
                                </form>
                            </center>
                        </td>
                    </#if></#if>
                </tr>
            </#list>
        </#if>
    </table>
</#macro>