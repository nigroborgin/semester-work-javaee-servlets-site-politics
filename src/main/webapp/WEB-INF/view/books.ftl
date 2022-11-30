<#include "base.ftl">
<#macro title>Books</#macro>

<#macro content>
    <h2>Books</h2><br>
    <#if message??>
        <strong>${message}</strong>
        <br>
    </#if>
    <#if role?? && role=='admin'>
        <button onclick="location.href='/politics/books/add/'">Add new book</button>
    </#if>
    <#if bookList??>
        <table border='1'>
            <tr>
                <th>Number</th>
                <th>Title</th>
                <th>Author</th>
                <th>More Info</th>
                <#if role?? && role=='admin'>
                    <th>Edit</th>
                    <th>Delete</th>
                </#if>
                <th>Download</th>
            </tr>
            <#list bookList as book>

                <tr>
                    <td><center>${book?counter}</center></td>
                    <td><#if book.title??> ${book.title}</#if></td>
                    <td><#if book.author??>${book.author}</#if></td>
                    <th>
                        <center>
                            <button onclick="location.href='/politics/books/?id=${book.id}'">more</button>
                        </center>
                    </th>
                    <#if role?? && role=='admin'>
                        <td>
                            <center>
                                <button onclick="location.href='/politics/books/edit/?id=${book.id}'">edit</button>
                            </center>
                        </td>
                        <td>
                            <center>
                                <form method="post" action="/politics/books/delete/?id=${book.id}">
                                    <button type="submit">delete</button>
                                </form>
                            </center>
                        </td>
                    </#if>
                    <th><a href="${book.fileURL}" download="">
                            <button>
                                    <img width="24" src="/politics/picture/download-file.png">
                            </button>
                        </a>
                    </th>
                </tr>
            </#list>
        </table>
    <#elseif book??>
        <h2>${book.title}</h2>
        ${book.author}<br>
        <#if role??>
            <#if (role == 'admin')>
                <button onclick="location.href='/politics/books/edit/?id=${book.id}'">edit</button>
                <form method="post" action="/politics/books/delete/?id=${book.id}">
                    <button type="submit">delete</button>
                </form>
            </#if>
        </#if>
        <h3>description:</h3>
        ${book.description}
        <br>
        <a href="${book.fileURL}">OPEN</a>
        <br><br>
        <a href="${book.fileURL}" download="">DOWNLOAD</a>



    <#else>
        <br><strong>no books</strong>
    </#if>
</#macro>