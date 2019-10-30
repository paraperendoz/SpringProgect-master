<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    <@l.logout/>
<div>
    <form method="post">
        <input type="text" name="name" placeholder="name" />
        <input type="text" name="surname" placeholder="surname">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Add user</button>
    </form>
</div>
</@c.page>