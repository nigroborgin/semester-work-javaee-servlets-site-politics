<#include "base.ftl">
<#macro title>Set User info</#macro>

<#macro content>
    <h2>Set User info</h2><br>
    <form method="post" enctype="multipart/form-data" >

        <img width="100" src="${user.pictureURL}" alt="/politics/picture/default.bmp"/>
        <input type="file" name="picture" <#if user??>value="${user.pictureURL}"</#if> />
        <br>

        ID: ${user.id}
        <br>

        <label>Username:
            <input type="text" name="username" <#if user??>value="${user.username}"</#if> />
        </label>
        <br>
        <#if user??><#if user.id == id>
            <label>Password:
                <input name="password1" type="password" />
            </label>
            <br>

            <label>Password:
                <input name="password2" type="password" />
            </label>
            <br>
        </#if></#if>

        <label>Email:
            <input name="email" type="text" <#if user??>value="${user.email}"</#if> />
        </label>
        <br>
        <#if role??><#if role=='admin'>
            Role:
            <input name="role"
                   type="radio" id="role1"
                   value="admin"
                   <#if user??><#if user.getRole().getName() == 'admin'>checked="checked"</#if></#if> />
            <label for="role1">Admin</label>
            <input name="role"
                   type="radio" id="role2"
                   value="user"
                   <#if user??><#if user.getRole().getName() == 'user'>checked="checked"</#if></#if> />
            <label for="role2">User</label>
        </#if></#if>
        <br>
        <input type="submit" value="save"/>
    </form>
<#--                <img width="24" src="/politics/picture/download-file.png"><#if user.picture??>${user.picture}</#if>-->
</#macro>