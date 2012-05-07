<%@ page import="assignment.Opportunities" %>



<div class="fieldcontain ${hasErrors(bean: opportunitiesInstance, field: 'applications', 'error')} ">
	<label for="applications">
		<g:message code="opportunities.applications.label" default="Applications" />
		
	</label>
	<g:textField name="applications" value="${opportunitiesInstance?.applications}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opportunitiesInstance, field: 'companyname', 'error')} ">
	<label for="companyname">
		<g:message code="opportunities.companyname.label" default="Companyname" />
		
	</label>
	<g:textField name="companyname" value="${opportunitiesInstance?.companyname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opportunitiesInstance, field: 'jobtitle', 'error')} ">
	<label for="jobtitle">
		<g:message code="opportunities.jobtitle.label" default="Jobtitle" />
		
	</label>
	<g:textField name="jobtitle" value="${opportunitiesInstance?.jobtitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: opportunitiesInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="opportunities.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${opportunitiesInstance?.status}"/>
</div>

