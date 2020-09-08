<#assign
known = Session.SPRING_SECUTIRY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECUTIRY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>