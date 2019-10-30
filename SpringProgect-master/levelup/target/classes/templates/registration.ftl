<#import "parts/common.ftl" as c>
<#import "parts/logreg.ftl" as l>

<@c.page>
    Registration
    ${message!}
    <@l.logreg "/registration" />
</@c.page>
