<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    <@l.logout/>
    <div>
        <a href="/adduser">Add new student</a>
    </div>
    <form method="get" action="/userlist">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Find</button>
    </form>
    <form method="get" action="/userlist">
        <input type="text" name="role" value="${role!}">
        <button type="submit">Find role</button>
    </form>
    List of students

<#--    <table>-->
<#--        <thead>-->
<#--        <tr>-->
<#--            <th>Fhoto</th>-->
<#--            <th>Name</th>-->
<#--            <th>Surname</th>-->
<#--            <th></th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--        <tbody>-->
        <#list users as user>
            <div>
                <b>${user.name}</b>
                <b>${user.surname}</b>
                <a href="/user/${user.id}">edit</a>
                <div>
                    <#if user.filename??>
                        <img src="/img/${user.filename}">
                    </#if>
                </div>
            </div>
<#--            <tr>-->
<#--                <td>-->
<#--                    <#if user.photo??>-->
<#--                        <img src="/img/${user.photo}">-->
<#--                    </#if>-->
<#--                </td>-->
<#--                <td>${user.name}</td>-->
<#--                <td>${user.surname}</td>-->
<#--                <td><a href="/user/${user.id}">edit</a></td>-->
<#--            </tr>-->
        </#list>
<#--        </tbody>-->
<#--    </table>-->
</@c.page>