<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    <@l.logout/>
    <div>user edit</div>
    <form action="/team" method="post">
        <div><label> Name : <input type="text" name="name" value="${team.name}"/> </label></div>
        <div>
<#--            <form method="get" action="/groupEdit">-->
                <label> Mentor: <input type="text" name="mentorname" value="${team.mentorName}"/> </label>
<#--                <button type="submit">find</button>-->
<#--            </form>-->
        </div>
<#--        <#list mentors as mentor>-->
<#--            <div>-->
<#--                <label>-->
<#--                    <input type="checkbox" name="${mentor.getName()}+${mentor.getSurname()}" }>-->
<#--                    ${mentor}-->
<#--                </label>-->

<#--            </div>-->
<#--        </#list>-->
        <input type="hidden" name="teamId" value="${team.id}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Save"/></div>
    </form>
</@c.page>