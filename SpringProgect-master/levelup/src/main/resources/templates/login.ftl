<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    Login page
    <@l.logreg "/login" />
    <a href="/registration">Add new user</a>
</@c.page>
