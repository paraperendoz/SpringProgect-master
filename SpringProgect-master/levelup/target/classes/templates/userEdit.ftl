<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    <@l.logout/>
    <div>user edit</div>
    <form action="/user" method="post" enctype="multipart/form-data">
        <div><label> Username : <input type="text" name="username" value="${user.username}"/> </label></div>
        <div><label> Name : <input type="text" name="name" value="${user.name}"/> </label></div>
        <div><label> Surname: <input type="text" name="surname" value="${user.surname}"/> </label></div>
        <div><label> Fhoto: <input type="file" name="file"/></label></div>
        <#list roles as role>
            <div>
                <label>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                    ${role}
                </label>

            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}" />
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Save"/></div>
    </form>
</@c.page>