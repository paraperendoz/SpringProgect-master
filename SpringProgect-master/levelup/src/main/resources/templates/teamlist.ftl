<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    <@l.logout/>
    <div>
    <a href="/addteam">Add new team</a>
    </div>
    <form method="get" action="/teamlist">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">find</button>
    </form>

    List of teams

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Mentor</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list teams as team>
            <tr>
                <td>${team.name}</td>
                <td>${team.mentorName}</td>
                <td><a href="/team/${team.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>