<#import "parts/common.ftl" as c>

<@c.page>
    <h3>User editor</h3>

    <div class="form-group">

        <form action="/user" method="post">
            <input class="form-control" type="text" name="username"
                   value="${user.username}" placeholder="User Name">

            <#list roles as role>
                <div>
                    <label class="form-check-label">
                        <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>${role}
                    </label>
                </div>
            </#list>

            <input type="hidden" value="${user.id}" name="userId"/>
            <input type="hidden" value="${_csrf.token}" name="_csrf"/>

            <br/>
            <button class="btn btn-primary" type="submit">Save</button>
        </form>
    </div>
</@c.page>